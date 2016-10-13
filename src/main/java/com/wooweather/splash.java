package com.wooweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by mostafa on 1/7/2016.
 */
public class splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splas);
        Thread timer = new Thread() {


            public void run() {
                try {
                    sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();

                } finally {
                    startActivity(new Intent("android.intent.action.WEATHERACTIVITY"));

                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
