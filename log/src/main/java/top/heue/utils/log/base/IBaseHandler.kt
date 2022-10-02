package top.heue.utils.log.base

import top.heue.utils.log.LogLevel

interface IBaseHandler {
    //暴露的调用方法
    fun execute(logLevel: LogLevel, tag: String, any: Any?): String?

    //设置下一个
    fun next(nextHandler: IBaseHandler)

    //获取下一个
    fun next(): IBaseHandler?

    //继承的处理方法
    fun handle(logLevel: LogLevel, tag: String, any: Any?): String?
}