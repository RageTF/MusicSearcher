package com.dcp.musicsearcher.search;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dcp.musicsearcher.trackList.TrackListActivity;
import com.dcp.musicsearcher.R;

/**
 * Created by Ruslan on 15.01.2017.
 */

public class SearchFragment extends Fragment implements View.OnClickListener {

    private EditText etArtist;
    private EditText etSongName;
    private EditText etWords;
    private Button btnSearch;
    private Button btnFavorite;

    @Override
    public void onSaveInstanceState(Bundle outState) { //to save EditTexts instance
        super.onSaveInstanceState(outState);
        Log.v("test", "onSavedInstanceState");

        outState.putString("artist", etArtist.getText().toString());
        outState.putString("song name", etSongName.getText().toString());
        outState.putString("words", etWords.getText().toString());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpTheElements(view);

        Log.v("test", "onViewCreated");

        if (savedInstanceState != null) {
            etArtist.setText(savedInstanceState.getString("artist"));
            etSongName.setText(savedInstanceState.getString("song name"));
            etWords.setText(savedInstanceState.getString("words"));
        }
    }


    private void setUpTheElements(View view) {
        etArtist = (EditText) view.findViewById(R.id.et_artist);
        etSongName = (EditText) view.findViewById(R.id.et_song_name);
        etWords = (EditText) view.findViewById(R.id.et_words);

        btnSearch = (Button) view.findViewById(R.id.btn_search);
        btnFavorite = (Button) view.findViewById(R.id.btn_favorite);
        btnSearch.setOnClickListener(this);
        btnFavorite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String[] params = new String[3];
        for (int i = 0; i < params.length; i++) { //to avoid NullPointerException
            params[i] = "";
        }
        int isAnythingWroteCount = 0;//if nothing was typed - then make Toast



        if (!etArtist.getText().toString().equals("")) {
            params[0] = String.valueOf(etArtist.getText());
            isAnythingWroteCount++;
        }
        if (!etSongName.getText().toString().equals("")) {
            params[1] = String.valueOf(etSongName.getText());
            isAnythingWroteCount++;
        }
        if (!etWords.getText().toString().equals("")) {
            params[2] = String.valueOf(etWords.getText());
            isAnythingWroteCount++;
        }

        if (isAnythingWroteCount != 0) {
            Intent intent = new Intent(getActivity(), TrackListActivity.class);
            intent.putExtra("artist", params[0]);
            intent.putExtra("song name", params[1]);
            intent.putExtra("words", params[2]);

            startActivity(intent);
        } else {
            Toast.makeText(getActivity(), "Type something to continue", Toast.LENGTH_SHORT).show();
        }

    }

}
