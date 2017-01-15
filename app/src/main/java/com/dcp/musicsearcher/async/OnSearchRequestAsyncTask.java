package com.dcp.musicsearcher.async;

import android.os.AsyncTask;
import android.util.Log;

import com.dcp.musicsearcher.api.ApiRequester;
import com.dcp.musicsearcher.api.pojo.songs.SongSearch;
import com.dcp.musicsearcher.async.AsyncSearchRequestCallback;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by Ruslan on 14.01.2017.
 */

public class OnSearchRequestAsyncTask extends AsyncTask<String,Void,Boolean> {

    private AsyncSearchRequestCallback requestCallback;
    public void setRequestCallback(AsyncSearchRequestCallback requestCallback) {
        this.requestCallback = requestCallback;
    }

    private Response<SongSearch> response;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        requestCallback.showProgressBar();
    }

    @Override
    protected Boolean doInBackground(String... params) {
        ApiRequester apiRequester = ApiRequester.getInstance();
        ApiRequester.SongSearchBuilder searchBuilder = new ApiRequester.SongSearchBuilder();
        boolean isSuccessful;

        try {
            searchBuilder.setNameArtist(params[0])
                    .setNameSong(params[1])
                    .setAnyWordInLyrics(params[2]);
            response = apiRequester.getResultSongSearch(searchBuilder);
            isSuccessful = true;
            Log.v("test","response is " + response.isSuccessful());
        } catch (IOException e) {
            isSuccessful = false;
            e.printStackTrace();
        }

        return isSuccessful;
    }


    @Override
    protected void onPostExecute(Boolean isSuccessful) {
        super.onPostExecute(isSuccessful);
        requestCallback.onSearchRequestReturn(isSuccessful,response);
        requestCallback.hideProgressBar();
    }
}
