package com.dcp.musicsearcher.trackList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dcp.musicsearcher.R;
import com.dcp.musicsearcher.SongActivity;
import com.dcp.musicsearcher.api.pojo.songs.SongSearch;
import com.dcp.musicsearcher.api.pojo.songs.Track;
import com.dcp.musicsearcher.async.AsyncSearchRequestCallback;
import com.dcp.musicsearcher.async.RetainFragment;

import java.util.List;

import retrofit2.Response;

public class TrackListActivity extends AppCompatActivity implements AsyncSearchRequestCallback , OnTrackItemClickListener {

    private RecyclerView trackListRecyclerView;
    private ProgressBar pbTLUpdate;
    private String[] params = new String[3];

    private TrackListAdapter adapter;

    private RetainFragment retainFragment;
    private static final String RETAIN_FRAGMENT_TAG = RetainFragment.class.getName();

    private List<Track> trackList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_list);

        pbTLUpdate= (ProgressBar) findViewById(R.id.pb_track_list_update);

        params[0] = getIntent().getStringExtra("artist");
        params[1] = getIntent().getStringExtra("song name");
        params[2] = getIntent().getStringExtra("words");

        retainFragment = getFragment();
        retainFragment.startAsync(params[0],params[1],params[2]);

    }

    private RetainFragment getFragment(){
        if(getFragmentManager().findFragmentByTag(RETAIN_FRAGMENT_TAG) == null){
            getFragmentManager()
                    .beginTransaction()
                    .add(new RetainFragment(), RETAIN_FRAGMENT_TAG)
                    .commit();
            getFragmentManager().executePendingTransactions(); // chtobi manager uspel dobavit' fragment
        }
        return (RetainFragment) getFragmentManager().findFragmentByTag(RETAIN_FRAGMENT_TAG);
    }

    @Override
    public void onSearchRequestReturn(boolean isSuccessful, Response<SongSearch> response) {
        if (isSuccessful){
            trackListRecyclerView = (RecyclerView) findViewById(R.id.rv_track_list);
            trackListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            adapter = new TrackListAdapter(response);

            trackListRecyclerView.setAdapter(adapter);

        }else {
            Toast.makeText(this,"No Internet connection",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgressBar() {
        pbTLUpdate.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        pbTLUpdate.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClickListener(String artist,String songName, long id) {
        
        Intent intent = new Intent(TrackListActivity.this, SongActivity.class);
        intent.putExtra("artist", artist);
        intent.putExtra("song name", songName);
        intent.putExtra("id",id);

        startActivity(intent);

    }
}
