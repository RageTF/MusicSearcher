package com.dcp.musicsearcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dcp.musicsearcher.api.ApiRequester;
import com.dcp.musicsearcher.search.SearchActivity;

/**
 * Created by Rage on 21.12.2016.
 */

public class MainActivity extends Activity {

    private ApiRequester apiRequester;
    private Button kostil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kostil = (Button) findViewById(R.id.btn_kostil);
        kostil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

//        apiRequester = ApiRequester.getInstance();
//
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    ApiRequester.SongSearchBuilder songSearchBuilder = new ApiRequester.SongSearchBuilder();
//                    songSearchBuilder.setNameArtist("taylor");
//                    Response<SongSearch> songSearchResponse = apiRequester.getResultSongSearch(songSearchBuilder);
//                    if (!songSearchResponse.isSuccessful()) {
//                        Log.v("retrofit", songSearchResponse.message());
//                        Log.v("retrofit", String.valueOf(songSearchResponse.errorBody()));
//                        Log.v("retrofit", String.valueOf(songSearchResponse.code()));
//                        Log.v("retrofit", "Error");
//                        return;
//                    }
//                    Log.v("retrofit", String.valueOf(songSearchResponse.code()));
//                    List<TrackList> tracks = songSearchResponse.body().getMessage().getBody().getTrackList();
//                    for (TrackList trackList : tracks) {
//                        Log.v("retrofit", trackList.getTrack().getTrackName());
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Log.v("retrofit", "NotInternet");
//                }
//            }
//        }.start();
    }
}
