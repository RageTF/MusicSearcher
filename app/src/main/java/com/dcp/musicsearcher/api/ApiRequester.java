package com.dcp.musicsearcher.api;

import com.dcp.musicsearcher.api.pojo.songs.SongSearch;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rage on 21.12.2016.
 */

public class ApiRequester {

    final static String API_URL="http://api.musixmatch.com/ws/1.1/";
    final static String API_KEY="a33978b2a40acaed8eedde3f1986d8a4";

    private static ApiRequester apiRequester;

    private ApiSearchCall apiSearchCall;

    private ApiRequester(){

    }

    public static synchronized ApiRequester getInstance(){
        if(apiRequester==null){
            apiRequester=new ApiRequester();
            apiRequester.init();
        }
        return apiRequester;
    }

    private void init(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        apiSearchCall = retrofit.create(ApiSearchCall.class);
    }

    public Response<SongSearch> getResultSongSearch(SongSearchBuilder parameters) throws IOException {
        Call<SongSearch> songSearchCall=apiSearchCall.searchSongs(parameters.build(),API_KEY);
        Response<SongSearch> songSearchResponse=songSearchCall.execute();
        return songSearchResponse;
    };

    public static class SongSearchBuilder{

        private HashMap<String,String> parameters;

        public SongSearchBuilder(){
            parameters=new HashMap<String, String>();
        }

        public SongSearchBuilder setNameArtist(String nameArtist){
            parameters.put("q_artist",nameArtist);
            return this;
        }

        public SongSearchBuilder setNameSong(String nameSong){
            parameters.put("q_track",nameSong);
            return this;
        }

        public SongSearchBuilder setAnyWordInLyrics(String anyWordInLyrics){
            parameters.put("q_lyrics",anyWordInLyrics);
            return this;
        }

        HashMap<String,String> build(){
            return parameters;
        }
    }

}
