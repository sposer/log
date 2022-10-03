# log

自用安卓开发辅助打印日志类，快捷的日志输出，可自定义数据类型的处理和输出的逻辑

## 使用

1. 确保repositories中有`mavenCentral()`，如：

    ``` java
    repositories {
        ...
        mavenCentral()
        ...
    }
    
    ```

2. 在子目录的build.gradle中添加：

    ``` java
    dependencies {
        //添加的
        implementation "top.heue.utils:log:1.3.0"
    }
    ```

3. 点击`Sync Now`等待完成
4. 使用：

    ``` kotlin
    /**
     * 默认处理和输出类
     */
    init {
        TaskPool.apply {
            add(StringHandler())
            add(ThrowableHandler())
            add(JSONHandler())
            add(BundleHandler())
            add(NullHandler())
            add(AnyHandler())

            //只输出到控制台
            add(DefaultPrinter())
        }
    }

    fun addHandler(handler: IBaseHandler)
    fun addPrinter(printer: IBasePrinter)

    fun v(any: Any?)
    fun v(tag: String, any: Any?)
    fun d(any: Any?)
    fun d(tag: String, any: Any?)
    fun i(any: Any?)
    fun i(tag: String, any: Any?)
    fun w(any: Any?)
    fun w(tag: String, any: Any?)
    fun e(any: Any?)
    fun e(tag: String, any: Any?)

    /**
     * Throwable To String
     */
    val Throwable.string: String

    /**
     * 执行时间计算，调用第二次得到距离前一次时间
     */
    val apart: Long

    /**
     * 当前执行方法，类名 to 方法名
     */
    val current: Pair<String, String>

    /**
     * 抓取应用崩溃信息，适用于Application中使用，适时处理和重启
     * thread为null表示主线程，否则为出错线程
     */
    fun catchError(threadHandler: (thread: Thread?, throwable: Throwable) -> Unit): L
    ```
