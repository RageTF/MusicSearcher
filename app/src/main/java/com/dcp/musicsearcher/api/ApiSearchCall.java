package com.dcp.musicsearcher.api;

import com.dcp.musicsearcher.api.pojo.songs.SongSearch;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Rage on 21.12.2016.
 */

public interface ApiSearchCall {

    @GET("track.search")
    Call<SongSearch> searchSongs(@QueryMap(encoded = true) Map<String,String> parameters,@Query("apikey") String apiKey);

}
