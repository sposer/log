package top.heue.utils.log.base

import top.heue.utils.log.LogLevel

abstract class BaseHandler : IBaseHandler {
    private var next: IBaseHandler? = null

    override fun execute(logLevel: LogLevel, tag: String, any: Any?): String? =
        handle(logLevel, tag, any) ?: next()?.execute(logLevel, tag, any)

    override fun next(nextHandler: IBaseHandler) {
        this.next = nextHandler
    }

    override fun next() = next
}