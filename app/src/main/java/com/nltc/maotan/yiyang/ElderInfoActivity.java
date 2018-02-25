package com.nltc.maotan.yiyang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Joker on 2/25/2018.
 */

public class ElderInfoActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elder_info);
        getIntent();
    }

}
