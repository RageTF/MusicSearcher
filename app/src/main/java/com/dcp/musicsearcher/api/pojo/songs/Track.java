
package com.dcp.musicsearcher.api.pojo.songs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Track {

    @SerializedName("track_id")
    @Expose
    private Long trackId;
    @SerializedName("track_mbid")
    @Expose
    private String trackMbid;
    @SerializedName("track_name")
    @Expose
    private String trackName;
    @SerializedName("track_rating")
    @Expose
    private Long trackRating;
    @SerializedName("track_length")
    @Expose
    private Long trackLength;
    @SerializedName("commontrack_id")
    @Expose
    private Long commontrackId;
    @SerializedName("instrumental")
    @Expose
    private Long instrumental;
    @SerializedName("explicit")
    @Expose
    private Long explicit;
    @SerializedName("has_lyrics")
    @Expose
    private Long hasLyrics;
    @SerializedName("has_subtitles")
    @Expose
    private Long hasSubtitles;
    @SerializedName("num_favourite")
    @Expose
    private Long numFavourite;
    @SerializedName("lyrics_id")
    @Expose
    private Long lyricsId;
    @SerializedName("subtitle_id")
    @Expose
    private Long subtitleId;
    @SerializedName("album_id")
    @Expose
    private Long albumId;
    @SerializedName("album_name")
    @Expose
    private String albumName;
    @SerializedName("artist_id")
    @Expose
    private Long artistId;
    @SerializedName("artist_mbid")
    @Expose
    private String artistMbid;
    @SerializedName("artist_name")
    @Expose
    private String artistName;
    @SerializedName("album_coverart_100x100")
    @Expose
    private String albumCoverart100x100;
    @SerializedName("album_coverart_350x350")
    @Expose
    private String albumCoverart350x350;
    @SerializedName("album_coverart_500x500")
    @Expose
    private String albumCoverart500x500;
    @SerializedName("album_coverart_800x800")
    @Expose
    private String albumCoverart800x800;
    @SerializedName("track_share_url")
    @Expose
    private String trackShareUrl;
    @SerializedName("track_edit_url")
    @Expose
    private String trackEditUrl;
    @SerializedName("updated_time")
    @Expose
    private String updatedTime;
    @SerializedName("primary_genres")
    @Expose
    private PrimaryGenres primaryGenres;
    @SerializedName("secondary_genres")
    @Expose
    private SecondaryGenres secondaryGenres;

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    public String getTrackMbid() {
        return trackMbid;
    }

    public void setTrackMbid(String trackMbid) {
        this.trackMbid = trackMbid;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public Long getTrackRating() {
        return trackRating;
    }

    public void setTrackRating(Long trackRating) {
        this.trackRating = trackRating;
    }

    public Long getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(Long trackLength) {
        this.trackLength = trackLength;
    }

    public Long getCommontrackId() {
        return commontrackId;
    }

    public void setCommontrackId(Long commontrackId) {
        this.commontrackId = commontrackId;
    }

    public Long getInstrumental() {
        return instrumental;
    }

    public void setInstrumental(Long instrumental) {
        this.instrumental = instrumental;
    }

    public Long getExplicit() {
        return explicit;
    }

    public void setExplicit(Long explicit) {
        this.explicit = explicit;
    }

    public Long getHasLyrics() {
        return hasLyrics;
    }

    public void setHasLyrics(Long hasLyrics) {
        this.hasLyrics = hasLyrics;
    }

    public Long getHasSubtitles() {
        return hasSubtitles;
    }

    public void setHasSubtitles(Long hasSubtitles) {
        this.hasSubtitles = hasSubtitles;
    }

    public Long getNumFavourite() {
        return numFavourite;
    }

    public void setNumFavourite(Long numFavourite) {
        this.numFavourite = numFavourite;
    }

    public Long getLyricsId() {
        return lyricsId;
    }

    public void setLyricsId(Long lyricsId) {
        this.lyricsId = lyricsId;
    }

    public Long getSubtitleId() {
        return subtitleId;
    }

    public void setSubtitleId(Long subtitleId) {
        this.subtitleId = subtitleId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getArtistMbid() {
        return artistMbid;
    }

    public void setArtistMbid(String artistMbid) {
        this.artistMbid = artistMbid;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumCoverart100x100() {
        return albumCoverart100x100;
    }

    public void setAlbumCoverart100x100(String albumCoverart100x100) {
        this.albumCoverart100x100 = albumCoverart100x100;
    }

    public String getAlbumCoverart350x350() {
        return albumCoverart350x350;
    }

    public void setAlbumCoverart350x350(String albumCoverart350x350) {
        this.albumCoverart350x350 = albumCoverart350x350;
    }

    public String getAlbumCoverart500x500() {
        return albumCoverart500x500;
    }

    public void setAlbumCoverart500x500(String albumCoverart500x500) {
        this.albumCoverart500x500 = albumCoverart500x500;
    }

    public String getAlbumCoverart800x800() {
        return albumCoverart800x800;
    }

    public void setAlbumCoverart800x800(String albumCoverart800x800) {
        this.albumCoverart800x800 = albumCoverart800x800;
    }

    public String getTrackShareUrl() {
        return trackShareUrl;
    }

    public void setTrackShareUrl(String trackShareUrl) {
        this.trackShareUrl = trackShareUrl;
    }

    public String getTrackEditUrl() {
        return trackEditUrl;
    }

    public void setTrackEditUrl(String trackEditUrl) {
        this.trackEditUrl = trackEditUrl;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public PrimaryGenres getPrimaryGenres() {
        return primaryGenres;
    }

    public void setPrimaryGenres(PrimaryGenres primaryGenres) {
        this.primaryGenres = primaryGenres;
    }

    public SecondaryGenres getSecondaryGenres() {
        return secondaryGenres;
    }

    public void setSecondaryGenres(SecondaryGenres secondaryGenres) {
        this.secondaryGenres = secondaryGenres;
    }

}
