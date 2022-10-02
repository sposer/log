package top.heue.utils.log.handler

import top.heue.utils.log.LogLevel
import top.heue.utils.log.base.BaseHandler

class AnyHandler : BaseHandler() {
    override fun handle(logLevel: LogLevel, tag: String, any: Any?): String =
        "$any ${any?.javaClass?.name}"
}