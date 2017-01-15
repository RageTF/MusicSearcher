package com.dcp.musicsearcher.activity.search.list;


import com.dcp.musicsearcher.api.pojo.songs.SongSearch;

import retrofit2.Response;

public interface AsyncSearchRequestCallback {
    public void onSearchRequestReturn(boolean isSuccessful, Response<SongSearch> response);
    public void showProgressBar();
    public void hideProgressBar();
}
