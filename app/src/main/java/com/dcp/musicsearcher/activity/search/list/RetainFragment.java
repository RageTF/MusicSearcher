package com.dcp.musicsearcher.activity.search.list;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Ruslan on 14.01.2017.
 */

public class RetainFragment extends Fragment {
    private AsyncSearchRequestCallback requestCallback;
    private OnSearchRequestAsyncTask asyncTask;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        requestCallback = (AsyncSearchRequestCallback) context;
        setCallback();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        requestCallback = (AsyncSearchRequestCallback) activity;
        setCallback();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void startAsync(String... params){
            asyncTask = new OnSearchRequestAsyncTask();
            asyncTask.setRequestCallback(requestCallback);
            asyncTask.execute(params);
    }

    private void setCallback(){
        if(asyncTask != null){
            asyncTask.setRequestCallback(requestCallback);
        }
    }
}
