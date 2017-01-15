package com.dcp.musicsearcher.activity.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dcp.musicsearcher.R;
import com.dcp.musicsearcher.api.ApiRequester;
import com.dcp.musicsearcher.api.pojo.lyrics.LyricSearch;
import com.dcp.musicsearcher.api.pojo.track.TrackSearch;
import com.dcp.musicsearcher.data.FavoritesController;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by Rage on 21.12.2016.
 */

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
