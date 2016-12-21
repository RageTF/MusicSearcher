
package com.dcp.musicsearcher.api.pojo.lyrics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lyrics {

    @SerializedName("lyrics_id")
    @Expose
    private Long lyricsId;
    @SerializedName("restricted")
    @Expose
    private Long restricted;
    @SerializedName("instrumental")
    @Expose
    private Long instrumental;
    @SerializedName("lyrics_body")
    @Expose
    private String lyricsBody;
    @SerializedName("lyrics_language")
    @Expose
    private String lyricsLanguage;
    @SerializedName("script_tracking_url")
    @Expose
    private String scriptTrackingUrl;
    @SerializedName("pixel_tracking_url")
    @Expose
    private String pixelTrackingUrl;
    @SerializedName("html_tracking_url")
    @Expose
    private String htmlTrackingUrl;
    @SerializedName("lyrics_copyright")
    @Expose
    private String lyricsCopyright;
    @SerializedName("updated_time")
    @Expose
    private String updatedTime;

    public Long getLyricsId() {
        return lyricsId;
    }

    public void setLyricsId(Long lyricsId) {
        this.lyricsId = lyricsId;
    }

    public Long getRestricted() {
        return restricted;
    }

    public void setRestricted(Long restricted) {
        this.restricted = restricted;
    }

    public Long getInstrumental() {
        return instrumental;
    }

    public void setInstrumental(Long instrumental) {
        this.instrumental = instrumental;
    }

    public String getLyricsBody() {
        return lyricsBody;
    }

    public void setLyricsBody(String lyricsBody) {
        this.lyricsBody = lyricsBody;
    }

    public String getLyricsLanguage() {
        return lyricsLanguage;
    }

    public void setLyricsLanguage(String lyricsLanguage) {
        this.lyricsLanguage = lyricsLanguage;
    }

    public String getScriptTrackingUrl() {
        return scriptTrackingUrl;
    }

    public void setScriptTrackingUrl(String scriptTrackingUrl) {
        this.scriptTrackingUrl = scriptTrackingUrl;
    }

    public String getPixelTrackingUrl() {
        return pixelTrackingUrl;
    }

    public void setPixelTrackingUrl(String pixelTrackingUrl) {
        this.pixelTrackingUrl = pixelTrackingUrl;
    }

    public String getHtmlTrackingUrl() {
        return htmlTrackingUrl;
    }

    public void setHtmlTrackingUrl(String htmlTrackingUrl) {
        this.htmlTrackingUrl = htmlTrackingUrl;
    }

    public String getLyricsCopyright() {
        return lyricsCopyright;
    }

    public void setLyricsCopyright(String lyricsCopyright) {
        this.lyricsCopyright = lyricsCopyright;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

}
