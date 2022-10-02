package top.heue.utils.log.printer

import android.util.Log
import top.heue.utils.log.LogLevel
import top.heue.utils.log.base.BasePrinter

/**
 * 默认打印内容到Logcat
 */
class DefaultPrinter : BasePrinter() {

    override fun println(logLevel: LogLevel, tag: String, string: String) {
        when (logLevel) {
            LogLevel.VERBOSE -> Log.v(tag, string)
            LogLevel.DEBUG -> Log.d(tag, string)
            LogLevel.INFO -> Log.i(tag, string)
            LogLevel.WARN -> Log.w(tag, string)
            LogLevel.ERROR -> Log.e(tag, string)
        }
    }
}