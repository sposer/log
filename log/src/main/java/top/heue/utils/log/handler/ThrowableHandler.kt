package top.heue.utils.log.handler

import top.heue.utils.log.L.string
import top.heue.utils.log.LogLevel
import top.heue.utils.log.base.BaseHandler

class ThrowableHandler : BaseHandler() {
    override fun handle(logLevel: LogLevel, tag: String, any: Any?): String? =
        if (any is Throwable) {
            any.string
        } else null
}