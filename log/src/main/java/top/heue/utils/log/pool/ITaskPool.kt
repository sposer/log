package top.heue.utils.log.pool

import top.heue.utils.log.LogLevel
import top.heue.utils.log.base.IBaseHandler
import top.heue.utils.log.base.IBasePrinter

interface ITaskPool {
    fun add(item: IBaseHandler)
    fun add(item: IBasePrinter)

    fun execute(logLevel: LogLevel, tag: String, any: Any?)
    fun execute(logLevel: LogLevel, tag: String, string: String)
}