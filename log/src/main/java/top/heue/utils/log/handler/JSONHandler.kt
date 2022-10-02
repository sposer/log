package top.heue.utils.log.handler

import org.json.JSONArray
import org.json.JSONObject
import top.heue.utils.log.LogLevel
import top.heue.utils.log.base.BaseHandler

class JSONHandler : BaseHandler() {
    override fun handle(logLevel: LogLevel, tag: String, any: Any?): String? =
        when (any) {
            is JSONObject -> {
                any.toString(4)
            }
            is JSONArray -> {
                any.toString(4)
            }
            else -> null
        }
}