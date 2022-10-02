package top.heue.utils.log.handler

import top.heue.utils.log.LogLevel
import top.heue.utils.log.base.BaseHandler

class NullHandler : BaseHandler() {
    override fun handle(logLevel: LogLevel, tag: String, any: Any?): String? =
        if (any == null) {
            "日志消息为空"
        } else null
}