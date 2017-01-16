package com.dcp.musicsearcher.activity.search.fragments.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dcp.musicsearcher.R;
import com.dcp.musicsearcher.activity.search.MainActivity;
import com.dcp.musicsearcher.activity.search.list.TrackListActivity;

/**
 * Created by Ruslan on 15.01.2017.
 */

public class SearchFragment extends Fragment implements View.OnClickListener {

    private EditText etArtist;
    private EditText etSongName;
    private EditText etWords;
    private Button btnSearch;

    @Override
    public void onSaveInstanceState(Bundle outState) { //to save EditTexts instance
        super.onSaveInstanceState(outState);
        outState.putString("KEY_NAME_ARTIST", etArtist.getText().toString());
        outState.putString("KEY_NAME_SONG", etSongName.getText().toString());
        outState.putString("KEY_WORDS", etWords.getText().toString());
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
        if (savedInstanceState != null) {
            etArtist.setText(savedInstanceState.getString("KEY_NAME_ARTIST"));
            etSongName.setText(savedInstanceState.getString("KEY_NAME_SONG"));
            etWords.setText(savedInstanceState.getString("KEY_WORDS"));
        }
    }


    private void setUpTheElements(View view) {
        etArtist = (EditText) view.findViewById(R.id.et_artist);
        etArtist.setTypeface(MainActivity.typeface);
        etSongName = (EditText) view.findViewById(R.id.et_song_name);
        etSongName.setTypeface(MainActivity.typeface);
        etWords = (EditText) view.findViewById(R.id.et_words);
        etWords.setTypeface(MainActivity.typeface);

        btnSearch = (Button) view.findViewById(R.id.btn_search);
        btnSearch.setTypeface(MainActivity.typeface);
        btnSearch.setOnClickListener(this);
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
            intent.putExtra("KEY_NAME_ARTIST", params[0]);
            intent.putExtra("KEY_NAME_SONG", params[1]);
            intent.putExtra("KEY_WORDS", params[2]);

            startActivity(intent);
        } else {
            Toast.makeText(getActivity(), "Type something to continue", Toast.LENGTH_SHORT).show();
        }

    }

}
