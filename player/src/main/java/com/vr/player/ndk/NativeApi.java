package com.vr.player.ndk;

import com.vr.player.L;
import com.vr.player.settings.SettingsManager;

/**
 * Created by qiangwang on 1/13/17.
 */

public class NativeApi implements INative{
    private static final String TAG = "NativeApi";

    @Override
    public boolean setShowMode(SettingsManager.ShowMode mode, SettingsManager.ResolutionRatio resolution, boolean isLeft) {
        return nativeSetShowMode(SettingsManager.showModeToInt(mode), SettingsManager.resolutionRatioToInt(resolution), isLeft);
    }

    @Override
    public int onSurfaceCreated(boolean isSingle) {
        return nativeOnSurfaceCreated(isSingle);
    }

    @Override
    public void onSurfaceChanged(int w, int h) {
        nativeOnSurfaceChanged(w, h);
    }

    @Override
    public void onDrawFrame(float degreeX, float degreeY, float degreeZ, float fov, boolean isLeft) {
        nativeOnDrawFrame(degreeX, degreeY, degreeZ, fov, isLeft);
    }

    @Override
    public void draw(int textureId) {
        nativeDraw(textureId);
    }

    @Override
    public float getDegreeX(boolean isLeft) {
        return nativeGetDegreeX(isLeft);
    }

    @Override
    public float getDegreeY(boolean isLeft) {
        return nativeGetDegreeY(isLeft);
    }

    @Override
    public float getDegreeZ(boolean isLeft) {
        return nativeGetDegreeZ(isLeft);
    }

    @Override
    public float getFov(boolean isLeft) {
        return nativeGetFov(isLeft);
    }

    @Override
    public void resetDegree() {
        nativeResetDegree();
    }

    @Override
    public String aesEncode(String id) {
        return nativeAesEncode(id);
    }

    @Override
    public boolean aesDecode(String hardId, String androidId, String result1, String result2) {
        return nativeAesDecode(hardId, androidId, result1, result2);
    }

    @Override
    public boolean hasLicence() {
        return nativeHasLicence();
    }

    private native boolean nativeSetShowMode(int mode, int resolution, boolean isLeft);
    private native int nativeOnSurfaceCreated(boolean isSingle);
    private native void nativeOnSurfaceChanged(int w, int h);
    private native void nativeOnDrawFrame(float degreeX, float degreeY, float degreeZ, float fov, boolean isLeft);
    private native void nativeDraw(int textureId);
    private native String nativeAesEncode(String id);
    private native boolean nativeAesDecode(String hardId, String androidId, String result1, String result2);
    private native boolean nativeHasLicence();
    private native float nativeGetDegreeX(boolean isLeft);
    private native float nativeGetDegreeY(boolean isLeft);
    private native float nativeGetDegreeZ(boolean isLeft);
    private native float nativeGetFov(boolean isLeft);
    private native void nativeResetDegree();

    static{
        try {
            System.loadLibrary("player");
        } catch (Exception e){
            L.e(TAG, "jniplayer.so loading failed.", e);
        }
    }
}
