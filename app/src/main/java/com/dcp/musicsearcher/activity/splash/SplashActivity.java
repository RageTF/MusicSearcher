package com.dcp.musicsearcher.activity.splash;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.dcp.musicsearcher.R;
import com.dcp.musicsearcher.activity.search.MainActivity;

public class SplashActivity extends AppCompatActivity implements SplashCallback{

    private final static String KEY_CONTINUED_ANIMATION="key_continued_animation";

    private long mTimeStartAnimation;
    private int mDuration=350;
    private ImageView mLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mLogo = (ImageView) findViewById(R.id.logo);

        AnimationDrawable logoAnimation=getSplashAnimationDrawable();

        int continuedAnimation=savedInstanceState!=null?savedInstanceState.getInt(KEY_CONTINUED_ANIMATION,-1):-1;

        if(continuedAnimation<0){
            mLogo.setImageDrawable(logoAnimation);
            mTimeStartAnimation=System.currentTimeMillis();
            getSplashAsyncFragment().startSplashAnimation(logoAnimation.getNumberOfFrames(),mDuration);
            logoAnimation.start();
        }else{
            int numberStartFrame=continuedAnimation/mDuration;

            AnimationDrawable savedAnimation=new AnimationDrawable();
            savedAnimation.setOneShot(true);
            for(int i = numberStartFrame; i< logoAnimation.getNumberOfFrames(); i++){
                savedAnimation.addFrame(logoAnimation.getFrame(i),mDuration);
            }
            mLogo.setImageDrawable(savedAnimation);
            if(numberStartFrame<logoAnimation.getNumberOfFrames()){
                mTimeStartAnimation=System.currentTimeMillis()-mDuration*numberStartFrame;
            }else{
                mTimeStartAnimation=System.currentTimeMillis();
            }
            savedAnimation.start();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CONTINUED_ANIMATION,(int)(System.currentTimeMillis()-mTimeStartAnimation));
    }

    private SplashAsyncFragment getSplashAsyncFragment(){

        FragmentManager fragmentManager = getSupportFragmentManager();
        SplashAsyncFragment fragment =
                (SplashAsyncFragment) fragmentManager.
                        findFragmentByTag(SplashAsyncFragment.class.getName());

        if (fragment == null) {
            fragment = new SplashAsyncFragment();
            fragmentManager.
                    beginTransaction().
                    add(fragment, SplashAsyncFragment.class.getName()).
                    commit();
        }
        return fragment;
    }

    private AnimationDrawable getSplashAnimationDrawable(){
        Drawable splashImage1 =getResources().getDrawable(R.drawable.splash1);
        Drawable splashImage2 =getResources().getDrawable(R.drawable.splash2);
        Drawable splashImage3 =getResources().getDrawable(R.drawable.splash3);
        Drawable splashImage4 =getResources().getDrawable(R.drawable.splash4);
        Drawable splashImage5 =getResources().getDrawable(R.drawable.splash5);
        Drawable splashImage6 =getResources().getDrawable(R.drawable.splash6);
        Drawable splashImage7 =getResources().getDrawable(R.drawable.splash7);

        AnimationDrawable logoAnimation =new AnimationDrawable();
        logoAnimation.setOneShot(true);
        logoAnimation.addFrame(splashImage1,mDuration);
        logoAnimation.addFrame(splashImage2,mDuration);
        logoAnimation.addFrame(splashImage3,mDuration);
        logoAnimation.addFrame(splashImage4,mDuration);
        logoAnimation.addFrame(splashImage5,mDuration);
        logoAnimation.addFrame(splashImage6,mDuration);
        logoAnimation.addFrame(splashImage7,mDuration);

        return  logoAnimation;
    }

    @Override
    public void onEndAnimation() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
