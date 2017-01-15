
package com.dcp.musicsearcher.api.pojo.track;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MusicGenre {

    @SerializedName("music_genre_id")
    @Expose
    private Long musicGenreId;
    @SerializedName("music_genre_parent_id")
    @Expose
    private Long musicGenreParentId;
    @SerializedName("music_genre_name")
    @Expose
    private String musicGenreName;
    @SerializedName("music_genre_name_extended")
    @Expose
    private String musicGenreNameExtended;

    public Long getMusicGenreId() {
        return musicGenreId;
    }

    public void setMusicGenreId(Long musicGenreId) {
        this.musicGenreId = musicGenreId;
    }

    public Long getMusicGenreParentId() {
        return musicGenreParentId;
    }

    public void setMusicGenreParentId(Long musicGenreParentId) {
        this.musicGenreParentId = musicGenreParentId;
    }

    public String getMusicGenreName() {
        return musicGenreName;
    }

    public void setMusicGenreName(String musicGenreName) {
        this.musicGenreName = musicGenreName;
    }

    public String getMusicGenreNameExtended() {
        return musicGenreNameExtended;
    }

    public void setMusicGenreNameExtended(String musicGenreNameExtended) {
        this.musicGenreNameExtended = musicGenreNameExtended;
    }

}
