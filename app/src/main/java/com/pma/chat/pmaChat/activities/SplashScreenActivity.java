package com.pma.chat.pmaChat.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pma.chat.pmaChat.R;
import com.pma.chat.pmaChat.auth.LoginActivity;


public class SplashScreenActivity extends Activity {

    ProgressBar progressBar;
    int progressStatus = 0;
    TextView textView;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        textView = (TextView)findViewById(R.id.load_per);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView.setText(progressStatus + "%");
                        }
                    });
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (progressStatus == 100) {
                    Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        }).start();
    }
}