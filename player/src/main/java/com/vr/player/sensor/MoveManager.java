package com.vr.player.sensor;

import android.content.Context;
import android.view.MotionEvent;

import com.vr.player.L;
import com.vr.player.settings.ISettings;
import com.vr.player.settings.SettingsManager.CtrlStyle;

public class MoveManager implements OnMoveStateChangeListener {
    private static final String TAG = "VR_MoveManager";
    private ISettings mSettings;
    private SensorCtrl mSensorCtrl;
    private TouchCtrl mTouchCtrl;
    private float mDeltaDegreeX, mDeltaDegreeY, mDeltaDegreeZ, mDeltaFov;

    public MoveManager(ISettings settings) {
        mSettings = settings;
    }

    public void initSensor(Context context) {
        if (mSensorCtrl != null) {
            mSensorCtrl.setOnMoveStateChangeListener(null);
            mSensorCtrl = null;
        }
        if (mTouchCtrl != null) {
            mTouchCtrl.setOnMoveStateChangeListener(null);
            mTouchCtrl = null;
        }
        if (mSettings.getSettingsManager().getCtrlStyle() == CtrlStyle.CS_DRAG ||
                mSettings.getSettingsManager().getCtrlStyle() == CtrlStyle.CS_DRAG_ZOOM) {
            L.d(TAG, "initSensor:DragManager");
            mTouchCtrl = new TouchCtrl(mSettings);
            mTouchCtrl.setOnMoveStateChangeListener(this);
        } else if (mSettings.getSettingsManager().getCtrlStyle() == CtrlStyle.CS_SENSOR) {
            L.d(TAG, "initSensor:SensorCtrlManager");
            mSensorCtrl = new SensorCtrl(context, mSettings);
            mSensorCtrl.setOnMoveStateChangeListener(this);
        } else {
            mTouchCtrl = new TouchCtrl(mSettings);
            mTouchCtrl.setOnMoveStateChangeListener(null);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (mTouchCtrl != null) return mTouchCtrl.onTouchEvent(event);
        return true;
    }

    public void registerSensor() {
        if (mSensorCtrl != null) {
            mSensorCtrl.registerListener();
        }
    }

    public void unRegisterSensor() {
        if (mSensorCtrl != null) {
            mSensorCtrl.unRegisterListener();
        }
    }

    public void resetSensor() {
        onClear();
    }

    public float getDeltaFov() {
        return mDeltaFov;
    }

    public float getDeltaDegreeZ() {
        return mDeltaDegreeZ;
    }

    public float getDeltaDegreeY() {
        return mDeltaDegreeY;
    }

    public float getDeltaDegreeX() {
        return mDeltaDegreeX;
    }

    @Override
    public void onMoveStateChanged(float deltaDegreeX, float deltaDegreeY, float deltaDegreeZ, float deltaFov) {
        mDeltaDegreeX = deltaDegreeX;
        mDeltaDegreeY = deltaDegreeY;
        mDeltaDegreeZ = deltaDegreeZ;
        mDeltaFov = deltaFov;
    }

    @Override
    public void onClear(){
        mDeltaDegreeX = 0;
        mDeltaDegreeY = 0;
        mDeltaDegreeZ = 0;
        mDeltaFov = 0;
        //L.d(TAG, "onClear");
    }
}
