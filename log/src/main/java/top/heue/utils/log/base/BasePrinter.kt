package top.heue.utils.log.base

import top.heue.utils.log.LogLevel

abstract class BasePrinter : IBasePrinter {
    private var next: IBasePrinter? = null

    override fun execute(logLevel: LogLevel, tag: String, string: String) {
        println(logLevel, tag, string)
        next()?.println(logLevel, tag, string)
    }

    override fun next(nextPrinter: IBasePrinter) {
        this.next = nextPrinter
    }

    override fun next() = next
}