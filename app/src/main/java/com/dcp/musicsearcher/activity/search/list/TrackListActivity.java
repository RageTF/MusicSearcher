package com.dcp.musicsearcher.activity.search.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dcp.musicsearcher.R;
import com.dcp.musicsearcher.activity.search.list.item.ItemActivity;
import com.dcp.musicsearcher.api.pojo.songs.SongSearch;

import retrofit2.Response;

public class TrackListActivity extends AppCompatActivity implements AsyncSearchRequestCallback, OnTrackItemClickListener {

    private RecyclerView trackListRecyclerView;
    private ProgressBar pbTLUpdate;
    private Button btnRefresh;
    private String[] params = new String[3];

    private TrackListAdapter adapter;

    private RetainFragment retainFragment;
    private static final String RETAIN_FRAGMENT_TAG = RetainFragment.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_list);

        pbTLUpdate = (ProgressBar) findViewById(R.id.pb_track_list_update);

        params[0] = getIntent().getStringExtra("KEY_NAME_ARTIST");
        params[1] = getIntent().getStringExtra("KEY_NAME_SONG");
        params[2] = getIntent().getStringExtra("KEY_WORDS");

        retainFragment = getFragment();
        retainFragment.startAsync(params[0], params[1], params[2]);

        btnRefresh=(Button) findViewById(R.id.refresh_button);
        btnRefresh.setVisibility(View.GONE);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retainFragment.startAsync(params[0], params[1], params[2]);
                btnRefresh.setVisibility(View.GONE);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("KEY_SAVE", "SAVE");
        super.onSaveInstanceState(outState);
    }

    private RetainFragment getFragment() {

        RetainFragment retainFragment = (RetainFragment) getFragmentManager().findFragmentByTag(RETAIN_FRAGMENT_TAG);

        if (retainFragment == null) {
            retainFragment = new RetainFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(retainFragment, RETAIN_FRAGMENT_TAG)
                    .commit();
            getFragmentManager().executePendingTransactions(); // chtobi manager uspel dobavit' fragment
        }
        return retainFragment;
    }

    @Override
    public void onSearchRequestReturn(boolean isSuccessful, Response<SongSearch> response) {
        if (isSuccessful) {
            trackListRecyclerView = (RecyclerView) findViewById(R.id.rv_track_list);
            trackListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            adapter = new TrackListAdapter(response);
            adapter.setOnTrackItemClickListener(this);

            trackListRecyclerView.setAdapter(adapter);
        } else {

            Toast.makeText(this, "No Internet connection", Toast.LENGTH_SHORT).show();
            btnRefresh.setVisibility(View.VISIBLE);
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
    public void onClickListener(String artist, String songName, long id) {

        Intent intent = new Intent(TrackListActivity.this, ItemActivity.class);
        intent.putExtra("KEY_NAME_ARTIST", artist);
        intent.putExtra("KEY_NAME_SONG", songName);
        intent.putExtra("KEY_ID", id);

        startActivity(intent);

    }
}
