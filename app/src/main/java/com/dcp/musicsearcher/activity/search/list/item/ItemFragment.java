package com.dcp.musicsearcher.activity.search.list.item;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.dcp.musicsearcher.api.ApiRequester;
import com.dcp.musicsearcher.api.pojo.lyrics.LyricSearch;
import com.dcp.musicsearcher.api.pojo.lyrics.Message;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by Dmitry on 15.01.2017.
 */

public class ItemFragment extends Fragment {

    private TaskInterface mTaskInterface;
    private MyAsyncTask mMyAsyncTask;
    private long mItemId;

    public static ItemFragment newInstance(long itemId) {
        ItemFragment itemFragment = new ItemFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("itemId", itemId);
        Log.v("IDDDD", String.valueOf(itemId));
        itemFragment.setArguments(bundle);
        return itemFragment;
    }

    public void start() {
        if (mMyAsyncTask == null) {
            mItemId = getArguments().getLong("itemId");
            mMyAsyncTask = new MyAsyncTask(mItemId);
            mMyAsyncTask.execute();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mTaskInterface = null;
    }

    @Override
    public void onAttach(Context context) {
        if (context instanceof TaskInterface) {
            mTaskInterface = (TaskInterface) context;
        }
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        if (activity instanceof TaskInterface) {
            mTaskInterface = (TaskInterface) activity;
        }
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mItemId = getArguments().getLong("itemId");
        MyAsyncTask myAsyncTask = new MyAsyncTask(mItemId);
        myAsyncTask.execute();
    }

    private class MyAsyncTask extends AsyncTask<Void, Integer, String> {

        private long mItemId;

        MyAsyncTask(long ItemId) {
            this.mItemId = ItemId;
        }

        @Override
        protected void onPreExecute() {
            if (mTaskInterface != null) {
                mTaskInterface.onTaskStart();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            mMyAsyncTask = null;
            if (mTaskInterface != null) {
                if (s.equals("Error")) {
                    mTaskInterface.onError();
                } else if (s.equals("")) {
                    mTaskInterface.onFinish("Sorry, text not found");
                } else mTaskInterface.onFinish(s);
            }
        }

        @Override
        protected String doInBackground(Void... params) {
            ApiRequester apiRequester = ApiRequester.getInstance();
            String lyrics = "";
            try {
                Response<LyricSearch> response = apiRequester.getResultLyricsSearch(mItemId);
                lyrics = response.body().getMessage().getBody().getLyrics().getLyricsBody();
            }catch (JsonSyntaxException e){
                return "";
            } catch (IOException e) {
                lyrics = "Error";
            }
            return lyrics;
        }
    }
}