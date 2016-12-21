
package com.dcp.musicsearcher.api.pojo.lyrics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LyricSearch {

    @SerializedName("message")
    @Expose
    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

}
