package top.heue.utils.log.base

import top.heue.utils.log.LogLevel

interface IBasePrinter {
    //暴露的调用方法
    fun execute(logLevel: LogLevel, tag: String, string: String)

    //设置下一个
    fun next(nextPrinter: IBasePrinter)

    //获取下一个
    fun next(): IBasePrinter?

    //继承的处理方法
    fun println(logLevel: LogLevel, tag: String, string: String)
}