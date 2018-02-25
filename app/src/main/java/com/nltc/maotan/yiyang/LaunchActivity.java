package com.nltc.maotan.yiyang;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.nltc.maotan.yiyang.global.AppConstants;
import com.nltc.maotan.yiyang.utils.SpUtils;

/**
 * Created by Joker on 2/25/2018.
 */

public class LaunchActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Determine whether to start the application for the first time
        boolean isFirstOpen = SpUtils.getBoolean(this, AppConstants.FIRST_OPEN);

        if(!isFirstOpen){
            Intent intent = new Intent(LaunchActivity.this,WelcomeGuideActivity.class);
            startActivity(intent);
            LaunchActivity.this.finish();
            return;
        }
        setContentView(R.layout.app_launch);
        //new a handler
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LaunchActivity.this,MainActivity.class);
                startActivity(intent);
                LaunchActivity.this.finish();
            }
        },3000);
    }
}
