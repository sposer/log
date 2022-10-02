package top.heue.utils.log

import android.os.Handler
import android.os.Looper
import top.heue.utils.log.base.IBaseHandler
import top.heue.utils.log.base.IBasePrinter
import top.heue.utils.log.handler.*
import top.heue.utils.log.pool.TaskPool
import top.heue.utils.log.printer.DefaultPrinter
import java.io.PrintWriter
import java.io.StringWriter
import java.util.concurrent.ConcurrentHashMap

object L : IL {
    private val TAG get() = L.current.second

    /**
     * 是否输出日志
     */
    override var loggable = true

    init {
        TaskPool.apply {
            add(StringHandler())
            add(ThrowableHandler())
            add(JSONHandler())
            add(BundleHandler())
            add(NullHandler())
            add(AnyHandler())

            add(DefaultPrinter())
        }
    }

    override fun handle(logLevel: LogLevel, tag: String, any: Any?) {
        if (loggable) {
            TaskPool.execute(logLevel, tag, any)
        }
    }

    override fun addHandler(handler: IBaseHandler) =
        TaskPool.add(handler)

    override fun addPrinter(printer: IBasePrinter) =
        TaskPool.add(printer)

    @JvmStatic
    fun v(any: Any?) = handle(LogLevel.VERBOSE, TAG, any)

    @JvmStatic
    fun v(tag: String, any: Any?) = handle(LogLevel.VERBOSE, tag, any)

    @JvmStatic
    fun d(any: Any?) = handle(LogLevel.DEBUG, TAG, any)

    @JvmStatic
    fun d(tag: String, any: Any?) = handle(LogLevel.DEBUG, tag, any)

    @JvmStatic
    fun i(any: Any?) = handle(LogLevel.INFO, TAG, any)

    @JvmStatic
    fun i(tag: String, any: Any?) = handle(LogLevel.INFO, tag, any)

    @JvmStatic
    fun w(any: Any?) = handle(LogLevel.WARN, TAG, any)

    @JvmStatic
    fun w(tag: String, any: Any?) = handle(LogLevel.WARN, tag, any)

    @JvmStatic
    fun e(any: Any?) = handle(LogLevel.ERROR, TAG, any)

    @JvmStatic
    fun e(tag: String, any: Any?) = handle(LogLevel.ERROR, tag, any)

    fun throwToString(e: Throwable): String {
        val writer = StringWriter()
        val printWriter = PrintWriter(writer)
        e.printStackTrace(printWriter)
        var cause: Throwable? = e.cause
        while (cause != null) {
            cause.printStackTrace(printWriter)
            cause = cause.cause
        }
        printWriter.close()
        return writer.toString()
    }

    override val Throwable.string: String
        get() = run {
            val writer = StringWriter()
            val printWriter = PrintWriter(writer)
            printStackTrace(printWriter)
            var cause: Throwable? = cause
            while (cause != null) {
                cause.printStackTrace(printWriter)
                cause = cause.cause
            }
            printWriter.close()
            writer.toString()
        }

    private var apartMap: ConcurrentHashMap<String, Long> = ConcurrentHashMap()

    override val apart
        get() = run {
            var result: Long = 0
            val tag = L.current.first
            if (apartMap[tag] == null) {
                apartMap[tag] = System.currentTimeMillis()
            } else {
                result = System.currentTimeMillis() - apartMap[tag]!!
                //L.d("$tag 执行间隔时长", System.currentTimeMillis() - apartMap[tag]!!)
                apartMap.remove(tag)
            }
            result
        }

    override val current
        get() = run {
            val forResult = StringBuffer("")
            val throwable = Throwable()
            val throwableString = throwable.stackTraceToString()
            val arr = throwableString.split('\n')
            var nearCurrent = false
            var forMethodName = ""
            var offset = 0//链接数组偏移
            for (pos in arr.indices) {
                if (nearCurrent) {
                    //判断是不是直接调用，如果是通过日志调用
                    if (arr[pos + 1].contains(javaClass.name)) offset = 2
                    //判断是不是日志调用
                    if (arr[pos + offset].contains(javaClass.name)) offset = 1
                    forMethodName = throwable.stackTrace[pos - 1 + offset].methodName
                    forResult.append(arr[pos + offset])
                    break
                }
                if (arr[pos].contains(javaClass.name))
                    nearCurrent = true
            }
            val s = forResult.indexOf("at ") + 3
            val e = forResult.indexOf('(')
            val res = forResult.substring(s, e)
            //L.d("$result -> $forMethodName()", "$result:$forMethodName()")
            res to forMethodName
        }

    override fun catchError(threadHandler: (thread: Thread?, throwable: Throwable) -> Unit) =
        apply {
            Handler(Looper.getMainLooper()).post {
                while (true) {
                    try {
                        Looper.loop()
                    } catch (e: Throwable) {
                        threadHandler(null, e)
                    }
                }
            }
            Thread.setDefaultUncaughtExceptionHandler { thread: Thread, throwable: Throwable ->
                threadHandler(thread, throwable)
            }
        }
}