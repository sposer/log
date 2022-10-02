package top.heue.utils.log.pool

import android.os.Handler
import android.os.Looper
import top.heue.utils.log.LogLevel
import top.heue.utils.log.base.IBaseHandler
import top.heue.utils.log.base.IBasePrinter
import java.util.concurrent.ConcurrentLinkedDeque

object TaskPool : ITaskPool {
    private val pool = Handler(Looper.myLooper() ?: Looper.getMainLooper())

    private val handlers = ConcurrentLinkedDeque<IBaseHandler>()
    private val printers = ConcurrentLinkedDeque<IBasePrinter>()

    override fun add(item: IBaseHandler) {
        handlers.peekLast()?.next(item)
        handlers.add(item)
    }

    override fun add(item: IBasePrinter) {
        printers.peekLast()?.next(item)
        printers.add(item)
    }

    override fun execute(logLevel: LogLevel, tag: String, any: Any?) {
        pool.post {
            val str = handlers.peekFirst()?.execute(logLevel, tag, any) ?: return@post
            execute(logLevel, tag, str)
        }
    }

    override fun execute(logLevel: LogLevel, tag: String, string: String) {
        pool.post {
            printers.peekFirst()?.execute(logLevel, tag, string)
        }
    }
}