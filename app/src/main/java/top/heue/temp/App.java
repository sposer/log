package top.heue.temp;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import top.heue.temp.activity.ErrorActivity;
import top.heue.temp.util.CatchExceptionUtil;

public class App extends Application {

    //程序错误处理接口实现
    private final CatchExceptionUtil.ErrorHandler handler = (thread, throwable) -> {
        String tag = (thread == null) ? "子线程" : "主线程";
        tag += "遇到错误";
        String message = CatchExceptionUtil.throwableToString(throwable);
        Log.e(tag, message);
        onError(tag, message);
    };

    //错误结果处理与显示
    private void onError(String tag, String message) {
        Intent intent = new Intent(this, ErrorActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(ErrorActivity.TAG_TITLE, tag);
        intent.putExtra(ErrorActivity.TAG_MESSAGE, message);
        new Handler(Looper.getMainLooper()).post(() -> startActivity(intent));
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //程序错误抓取
        CatchExceptionUtil.catchError(handler);
    }
}
