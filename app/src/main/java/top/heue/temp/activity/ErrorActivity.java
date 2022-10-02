package top.heue.temp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import top.heue.temp.R;

public class ErrorActivity extends Activity {
    public static String TAG_TITLE = "title";
    public static String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        TextView textView = findViewById(R.id.error_txt_msg);
        Intent intent = getIntent();
        if (intent.hasExtra(TAG_TITLE) || intent.hasExtra(TAG_MESSAGE)) {
            setTitle(intent.getStringExtra(TAG_TITLE));
            textView.setText(intent.getStringExtra(TAG_MESSAGE));
        } else {
            setTitle("非法进入界面！！！");
            textView.setText("非法进入界面！！！");
        }
    }
}
