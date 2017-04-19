package com.vr.player.settings;

/**
 * Created by arena on 2017/1/6.
 */

public class RotateLimitManager {
    private static final String TAG = "VR_RotateLimitManager";

    private float mRotateXMin = (float) (Math.PI * 1.8f);
    private float mRotateXMax = (float) (Math.PI * 1.8f);
    private float mRotateX = (float) (Math.PI * 1.5f);
    private float mRotateFinalXMin = (float) (Math.PI * 1.8f);
    private float mRotateFinalXMax = (float) (Math.PI * 1.8f);
    private float mRotateFinalYMin = (float) (Math.PI * 1.8f);
    private float mRotateFinalYMax = (float) (Math.PI * 1.8f);

    public void setRotateFinalXMin(float rotateFinalXMin) {
        this.mRotateFinalXMin = rotateFinalXMin;
        setRotateXMin((float)Math.PI/3);
    }

    public void setRotateFinalXMax(float rotateFinalXMax) {
        this.mRotateFinalXMax = rotateFinalXMax;
        setRotateXMax((float)Math.PI/3);
    }

    public void setRotateFinalX(float rotateFinalX) {
        setRotateX(rotateFinalX);
    }

    public void setRotateFinalYMin(float rotateFinalYMin) {
        this.mRotateFinalYMin = rotateFinalYMin;
        setRotateYMin((float)Math.PI/3);
    }

    public void setRotateFinalYMax(float rotateFinalYMax) {
        this.mRotateFinalYMax = rotateFinalYMax;
        setRotateYMax((float)Math.PI/3);
    }

    public void setRotateFinalY(float rotateFinalY) {
        setRotateY(rotateFinalY);
    }

    public float getRotateX() {
        return mRotateX;
    }

    public void setRotateX(float mRotateX) {
        this.mRotateX = mRotateX;
    }

    public float getRotateXMin() {
        return mRotateXMin;
    }

    public void setRotateXMin(float fov) {
        this.mRotateXMin = mRotateFinalXMin + fov/2;
    }

    public float getRotateXMax() {
        return mRotateXMax;
    }

    public void setRotateXMax(float fov) {
        this.mRotateXMax = mRotateFinalXMax - fov/2;
    }

    public float checkRotateX(float rotate) {
        if (getRotateXMax() - getRotateXMin() < 0.02f) return rotate;
        if (rotate > getRotateXMax()) return getRotateXMax();
        if (rotate < getRotateXMin()) return getRotateXMin();
        return rotate;
    }

    private float mRotateYMin = (float) (Math.PI * 0f);
    private float mRotateYMax = (float) (Math.PI * 0f);
    private float mRotateY = (float) (Math.PI * 0f);

    public float getRotateY() {
        return mRotateY;
    }

    public void setRotateY(float mRotateY) {
        this.mRotateY = mRotateY;
    }

    public float getRotateYMin() {
        return mRotateYMin;
    }

    public void setRotateYMin(float fov) {
        this.mRotateYMin = mRotateFinalYMin + fov/2;
    }

    public float getRotateYMax() {
        return mRotateYMax;
    }

    public void setRotateYMax(float fov) {
        this.mRotateYMax = mRotateFinalYMax - fov/2;
    }

    public float checkRotateY(float rotate) {
        if (getRotateYMax() - getRotateYMin() < 0.02f) return rotate;
        if (rotate > getRotateYMax()) return getRotateYMax();
        if (rotate < getRotateYMin()) return getRotateYMin();
        return rotate;
    }
}
