package com.dcp.musicsearcher;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.dcp.musicsearcher.api.ApiRequester;
import com.dcp.musicsearcher.api.pojo.songs.SongSearch;
import com.dcp.musicsearcher.api.pojo.songs.TrackList;
import com.dcp.musicsearcher.data.ApplicationDataBase;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

/**
 * Created by Rage on 21.12.2016.
 */

public class MainActivity extends Activity {

    private ApiRequester apiRequester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiRequester = ApiRequester.getInstance();

        new Thread() {
            @Override
            public void run() {
                try {
                    ApiRequester.SongSearchBuilder songSearchBuilder = new ApiRequester.SongSearchBuilder();
                    songSearchBuilder.setNameArtist("taylor");
                    Response<SongSearch> songSearchResponse = apiRequester.getResultSongSearch(songSearchBuilder);
                    if (!songSearchResponse.isSuccessful()) {
                        Log.v("retrofit", songSearchResponse.message());
                        Log.v("retrofit", String.valueOf(songSearchResponse.errorBody()));
                        Log.v("retrofit", String.valueOf(songSearchResponse.code()));
                        Log.v("retrofit", "Error");
                        return;
                    }
                    Log.v("retrofit", String.valueOf(songSearchResponse.code()));
                    List<TrackList> tracks = songSearchResponse.body().getMessage().getBody().getTrackList();
                    for (TrackList trackList : tracks) {
                        Log.v("retrofit", trackList.getTrack().getTrackName());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.v("retrofit", "NotInternet");
                }
            }
        }.start();
    }
}
