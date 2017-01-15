package com.dcp.musicsearcher.async;

import com.dcp.musicsearcher.api.pojo.songs.SongSearch;

import retrofit2.Response;

/**
 * Created by Ruslan on 14.01.2017.
 */

public interface AsyncSearchRequestCallback {
    public void onSearchRequestReturn(boolean isSuccessful, Response<SongSearch> response);
    public void showProgressBar();
    public void hideProgressBar();
}
