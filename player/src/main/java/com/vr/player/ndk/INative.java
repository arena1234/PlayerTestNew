package com.vr.player.ndk;

import com.vr.player.settings.SettingsManager;

/**
 * Created by qiangwang on 1/13/17.
 */

public interface INative {
    boolean setShowMode(SettingsManager.ShowMode mode, SettingsManager.ResolutionRatio resolution, boolean isLeft);
    int onSurfaceCreated(boolean isSingle);
    void onSurfaceChanged(int w, int h);
    void onDrawFrame(float degreeX, float degreeY, float degreeZ, float fov, boolean isLeft);
    void draw(int textureId);
    float getDegreeX(boolean isLeft);
    float getDegreeY(boolean isLeft);
    float getDegreeZ(boolean isLeft);
    float getFov(boolean isLeft);
    void resetDegree();
    String aesEncode(String id);
    boolean aesDecode(String hardId, String androidId, String result1, String result2);
    boolean hasLicence();
}
