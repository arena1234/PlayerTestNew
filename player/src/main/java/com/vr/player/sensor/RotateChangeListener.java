package com.vr.player.sensor;

/**
 * Created by arena on 2017/1/5.
 */

public interface RotateChangeListener {
    void onRotateChanged(float rotatez);
    void onRotateChanged(float eyex, float eyey, float eyez);
    void onRotateChanged(float eyex, float eyey, float eyez, float rotatey);
}
