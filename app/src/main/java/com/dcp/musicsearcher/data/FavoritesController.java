package com.dcp.musicsearcher.data;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Rage on 28.12.2016.
 */

public class FavoritesController {

    private final String FAVORITES_PREFERENCES="mFavorites";
    private SharedPreferences mFavorites;

    public FavoritesController(Context context){
        mFavorites =context.getSharedPreferences(FAVORITES_PREFERENCES,Context.MODE_PRIVATE);
    }

    public void addToFavorites(long trackId,String nameSong,String nameArtist){
        SharedPreferences.Editor editor= mFavorites.edit();
        editor.putString(String.valueOf(trackId),
                "{ \"info\":{"
                        +"\"nameSong\":\""+nameSong+"\","
                        +"\"nameArtist\":\""+nameArtist+"\"}"
                +"}"
        );
        editor.commit();

    }

    public boolean contains(long trackId){
        return mFavorites.contains(String.valueOf(trackId));
    }

    public void removeFromFavorites(long trackId){
        SharedPreferences.Editor editor= mFavorites.edit();
        editor.remove(String.valueOf(trackId));
        editor.commit();
    }

    public ArrayList<SongInfo> getFavoritesSong(){
        ArrayList<SongInfo> favoritesSongs=new ArrayList<>();
        Map<String,?> allSongs=mFavorites.getAll();
        for(Map.Entry<String,?> entry: allSongs.entrySet()){
            long trackId=Long.parseLong(entry.getKey());
            try {
                JSONObject infoSong=new JSONObject((String) entry.getValue());
                JSONObject infoAttr=infoSong.getJSONObject("info");
                SongInfo songInfo=new SongInfo(trackId,infoAttr.getString("nameSong"),infoAttr.getString("nameArtist"));
                favoritesSongs.add(songInfo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(favoritesSongs);
        return favoritesSongs;
    }

    public class SongInfo implements Comparable<SongInfo>{

        private long mTrackId;
        private String mNameSong;
        private String mNameArtist;

        SongInfo(long trackId,String nameSong,String nameArtist){
            mTrackId=trackId;
            mNameSong=nameSong;
            mNameArtist=nameArtist;

        }

        public long getTrackId() {
            return mTrackId;
        }

        public String getNameSong() {
            return mNameSong;
        }

        public String getNameArtist() {
            return mNameArtist;
        }

        @Override
        public int compareTo(SongInfo o) {
            return mNameArtist.compareTo(o.getNameArtist());
        }
    }

}
