package top.heue.utils.log.handler

import android.os.Bundle
import org.json.JSONObject
import top.heue.utils.log.LogLevel
import top.heue.utils.log.base.BaseHandler

class BundleHandler : BaseHandler() {
    private val jsonHandler by lazy { JSONHandler() }
    override fun handle(logLevel: LogLevel, tag: String, any: Any?): String? =
        if (any is Bundle) {
            val json = JSONObject()
            val keys = any.keySet()
            keys.forEach {
                json.put(it, any[it])
            }
            jsonHandler.handle(logLevel, tag, json)
        } else null

}