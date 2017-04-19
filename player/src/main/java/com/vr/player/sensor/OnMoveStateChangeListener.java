package com.vr.player.sensor;

/**
 * Created by arena on 2017/1/21.
 */

public interface OnMoveStateChangeListener {
    void onMoveStateChanged(float deltaDegreeX, float deltaDegreeY, float deltaDegreeZ, float deltaFov);
    void onClear();
}
