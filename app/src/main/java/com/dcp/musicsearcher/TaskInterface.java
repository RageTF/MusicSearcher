package com.dcp.musicsearcher;

/**
 * Created by Dmitry on 15.01.2017.
 */

public interface TaskInterface {
    void onTaskStart();
    void onError();
    void onFinish(String s);
}
