package com.dcp.musicsearcher.activity.splash;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Rage on 14.01.2017.
 */

public class SplashAsyncFragment extends Fragment {

    private SplashCallback splashCallback;
    private EndSplashAnimation endSplashAnimation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        splashCallback=(SplashCallback) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        splashCallback=(SplashCallback) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        splashCallback=null;
    }

    public void startSplashAnimation(int countFrames,int durationFrame){
        if(endSplashAnimation==null){
            endSplashAnimation=new EndSplashAnimation(countFrames,durationFrame);
            endSplashAnimation.execute();
        }
    }

    private class EndSplashAnimation extends AsyncTask<Void,Void,Void>{

        private int mCountFrames;
        private int mDurationFrame;

        EndSplashAnimation(int countFrames,int durationFrame){
            mCountFrames=countFrames;
            mDurationFrame=durationFrame;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(mCountFrames*mDurationFrame);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(splashCallback!=null)
            splashCallback.onEndAnimation();
            return null;
        }

    }
}
