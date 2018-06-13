package com.example.rajarshi.interviewprep.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.rajarshi.interviewprep.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread myThread = new Thread() {

            @Override
            public void run() {
                try {
                    //delay
                    //Todo make it 4500
                    sleep(3000);
                    startActivity(new Intent(getApplicationContext(), Greetings.class));
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
