
package com.dcp.musicsearcher.api.pojo.lyrics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Header {

    @SerializedName("status_code")
    @Expose
    private Long statusCode;
    @SerializedName("execute_time")
    @Expose
    private Float executeTime;

    public Long getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Long statusCode) {
        this.statusCode = statusCode;
    }

    public Float getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Float executeTime) {
        this.executeTime = executeTime;
    }

}
