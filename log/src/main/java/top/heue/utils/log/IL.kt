package top.heue.utils.log

import top.heue.utils.log.base.IBaseHandler
import top.heue.utils.log.base.IBasePrinter

interface IL {
    var loggable: Boolean

    fun handle(logLevel: LogLevel, tag: String, any: Any?)
    fun addHandler(handler: IBaseHandler)
    fun addPrinter(printer: IBasePrinter)

    /*fun v(any: Any?)
    fun v(tag: String, any: Any?)
    fun d(any: Any?)
    fun d(tag: String, any: Any?)
    fun i(any: Any?)
    fun i(tag: String, any: Any?)
    fun w(any: Any?)
    fun w(tag: String, any: Any?)
    fun e(any: Any?)
    fun e(tag: String, any: Any?)*/
    /**
     * Throwable To String
     */
    val Throwable.string: String

    /**
     * 执行时间计算
     */
    val apart: Long

    /**
     * 当前执行方法
     */
    val current: Pair<String, String>

    /**
     * 抓取应用崩溃信息
     */
    fun catchError(threadHandler: (thread: Thread?, throwable: Throwable) -> Unit): L
}