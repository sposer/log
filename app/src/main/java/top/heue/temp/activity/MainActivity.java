package top.heue.temp.activity;

import android.app.Activity;
import android.os.Bundle;

import top.heue.utils.log.L;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i = 0; i < 100; i++) {
            L.d("好好学习天天向上");
        }
    }
}
