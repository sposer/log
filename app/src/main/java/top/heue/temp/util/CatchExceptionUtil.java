package top.heue.temp.util;

import android.os.Handler;
import android.os.Looper;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CatchExceptionUtil {

    /**
     * 捕捉异常并处理
     */
    public static void catchError(ErrorHandler handler) {
        //主线程
        new Handler(Looper.getMainLooper()).post(() -> {
            //无限循环
            while (true) {
                try {
                    Looper.loop();
                } catch (Throwable throwable) {
                    handler.handler(null, throwable);
                }
            }
        });
        //子线程
        Thread.setDefaultUncaughtExceptionHandler(handler::handler);
    }

    /**
     * Throwable To String
     */
    public static String throwableToString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        Throwable cause = throwable.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        return stringWriter.toString();
    }

    /**
     * 捕获异常处理接口
     */
    public interface ErrorHandler {
        /**
         * 处理错误
         *
         * @param thread    在主线程时为null，子线程有值
         * @param throwable 错误发生的详细信息
         */
        void handler(Thread thread, Throwable throwable);
    }
}
