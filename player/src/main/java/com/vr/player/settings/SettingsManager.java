package com.vr.player.settings;

/**
 * Created by arena on 2017/1/6.
 */

public class SettingsManager {
    private static final String TAG = "VR_SettingsManager";
    private ShowMode mShowMode = ShowMode.SM_ORIGINAL;
    private CtrlStyle mCtrlStyle = CtrlStyle.CS_DRAG;
    private ResolutionRatio mResolutionRatio = ResolutionRatio.RR_4K;
    private boolean isLeftOrTop = false;

    public boolean isLeftOrTop() {
        return isLeftOrTop;
    }

    public void setLeftOrTop(boolean mLeftOrTop) {
        isLeftOrTop = mLeftOrTop;
    }

    public ShowMode getShowMode() {
        return mShowMode;
    }

    public void setShowMode(ShowMode showMode) {
        this.mShowMode = showMode;
    }

    public CtrlStyle getCtrlStyle() {
        return mCtrlStyle;
    }

    public void setCtrlStyle(CtrlStyle ctrlStyle) {
        this.mCtrlStyle = ctrlStyle;
    }

    public ResolutionRatio getResolutionRatio() {
        return mResolutionRatio;
    }

    public void setResolutionRatio(ResolutionRatio resolutionRatio) {
        this.mResolutionRatio = resolutionRatio;
    }

    public static int showModeToInt(ShowMode mode){
        return mode.ordinal();
    }
    public static int resolutionRatioToInt(ResolutionRatio ratio){
        return ratio.ordinal();
    }

    public enum ShowMode{
        SM_ORIGINAL,
        SM_SPHERE_FRONT,
        SM_SPHERE_FRONT_BACK,
        SM_SPHERE_UP,
        SM_SPHERE_DOWN,
        SM_SPHERE_VR,
        SM_CYLINDER_UP_DOWN,
        SM_PLANE_UP_DOWN,
    }

    public enum CtrlStyle {
        CS_NULL,
        CS_DRAG,
        CS_DRAG_ZOOM,
        CS_SENSOR
    }

    public enum ResolutionRatio {
        RR_4K,
        RR_1080P,
        RR_720P,
    }
}
