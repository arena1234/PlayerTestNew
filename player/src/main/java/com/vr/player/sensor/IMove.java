package com.vr.player.sensor;

import android.content.Context;
import android.view.MotionEvent;

public interface IMove {
    float[] getEye();

    void setZoom(float zoom);

    float getZoom();

    void initSensor(Context context);

    boolean onTouchEvent(MotionEvent event);

    float getRotateY();

    void registerSensor();

    void unRegisterSensor();

    void resetSensor();

    void switchMode(Context context);
}
