package com.dcp.musicsearcher.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dcp.musicsearcher.async.AsyncSearchRequestCallback;
import com.dcp.musicsearcher.R;
import com.dcp.musicsearcher.api.pojo.songs.SongSearch;

import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if(savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_search_fragment_container, new SearchFragment())
                    .commit();
        } else {
            //do nothing, it's normal (it's for retain EditTexts data)
        }
    }
}
