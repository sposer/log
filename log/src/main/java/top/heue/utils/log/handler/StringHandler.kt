package top.heue.utils.log.handler

import top.heue.utils.log.LogLevel
import top.heue.utils.log.base.BaseHandler

class StringHandler : BaseHandler() {
    override fun handle(logLevel: LogLevel, tag: String, any: Any?): String? =
        if (any is String) {
            any.toString()
        } else null
}