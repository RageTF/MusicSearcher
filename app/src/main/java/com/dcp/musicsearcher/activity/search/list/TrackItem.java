package com.dcp.musicsearcher.activity.search.list;

public class TrackItem {
    private String artist;
    private String songName;
    private long id;

    public TrackItem(String artist, String songName, long id) {
        this.artist = artist;
        this.songName = songName;
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
