package com.nltc.maotan.yiyang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nltc.maotan.yiyang.global.AppConstants;
import com.nltc.maotan.yiyang.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Joker on 2/18/2018.
 */

public class WelcomeGuideActivity extends AppCompatActivity implements OnClickListener{
    private ViewPager viewPager;
    private GuideViewPagerAdapter adapter;
    private List<View> views;
    private Button startBtn;

    //Guide picture source
    private static final int[] pics = {R.layout.guide_view1,
            R.layout.guide_view2,R.layout.guide_view3,R.layout.guide_view4};

    //Bottom dot
    private ImageView[] dots;

    //Record the current selected location
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_guide);

        views  = new ArrayList<View>();

        //Initialize the boot page view
        for(int i = 0; i < pics.length; i++){

            View view = LayoutInflater.from(this).inflate(pics[i],null);

            if(i == pics.length - 1){
                startBtn = view.findViewById(R.id.btn_login);
                startBtn.setTag("enter");
                startBtn.setOnClickListener(this);
            }

            views.add(view);
        }

        viewPager = findViewById(R.id.vp_guide);
        //Initialize adapter
        adapter = new GuideViewPagerAdapter(views);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new PageChangeListener());

        initDots();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        SpUtils.putBoolean(WelcomeGuideActivity.this,
                AppConstants.FIRST_OPEN,true);
        WelcomeGuideActivity.this.finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void initDots(){
        LinearLayout ll = findViewById(R.id.ll);
        dots = new ImageView[pics.length];

        for(int i = 0; i < pics.length; i++){
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(false);
            dots[i].setOnClickListener(this);
            dots[i].setTag(i);
        }

        currentIndex = 0;
        //when chosen, set as white
        dots[currentIndex].setEnabled(true);
    }

    /*
    * setting current view
    * */
    private void setCurrentView(int position){
        if(position < 0 || position >= pics.length){
            return;
        }
        viewPager.setCurrentItem(position);
    }

    /*
    * setting current point dot
    */
    private void setCurrentDot(int position){
        if(position < 0 || position > pics.length || currentIndex == position){
            return;
        }
        dots[position].setEnabled(true);
        dots[currentIndex].setEnabled(false);
        currentIndex = position;
    }

    public void onClick(View view){
        if(view.getTag().equals("enter")){
            enterMainActivity();
            return;
        }

        int position = (Integer) view.getTag();
        setCurrentView(position);
        setCurrentDot(position);
    }

    private void enterMainActivity(){
        Intent intent = new Intent(WelcomeGuideActivity.this,
                LaunchActivity.class);
        startActivity(intent);
        SpUtils.putBoolean(WelcomeGuideActivity.this,
                AppConstants.FIRST_OPEN,true);
        WelcomeGuideActivity.this.finish();
    }

    private class PageChangeListener implements OnPageChangeListener{

        @Override
        public void onPageScrollStateChanged(int position) {

        }

        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            setCurrentDot(position);
        }
    }
}
