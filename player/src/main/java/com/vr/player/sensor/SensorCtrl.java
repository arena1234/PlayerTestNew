package com.vr.player.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.vr.player.settings.ISettings;
import com.vr.player.settings.SettingsManager.CtrlStyle;

public class SensorCtrl implements SensorEventListener {
    private static String TAG = "VR_SensorCtrl";
    private ISettings mSettings;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private long lastTime = 0;

    public SensorCtrl(Context context, ISettings settings){
        mSettings = settings;
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    public void registerListener() {
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    public void unRegisterListener() {
        mSensorManager.unregisterListener(this, mSensor);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(mSettings.getSettingsManager().getCtrlStyle() != CtrlStyle.CS_SENSOR) return;
        switch (event.sensor.getType()) {
            case Sensor.TYPE_GYROSCOPE:
                long curTime = event.timestamp;
                if (lastTime == 0) {
                    lastTime = curTime;
                    return;
                }
                float timeSpan = (curTime - lastTime) * 1.0f / 1000000000f;
                ctrl(event.values[0], event.values[1], event.values[2], timeSpan);
                lastTime = curTime;
                break;
        }
    }

    private void ctrl(float r1, float r2, float r3, float timeSpan){
        float deltaDegreeX = (float) Math.toDegrees(r1 * timeSpan);
        float deltaDegreeY = (float) Math.toDegrees(r2 * timeSpan);
        float deltaDegreeZ = (float) Math.toDegrees(r3 * timeSpan);

        if(mMoveStateChangeListener != null){
            mMoveStateChangeListener.onMoveStateChanged(-deltaDegreeX, -deltaDegreeY, deltaDegreeZ, 0);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }


    private OnMoveStateChangeListener mMoveStateChangeListener;

    public void setOnMoveStateChangeListener(OnMoveStateChangeListener listener){
        mMoveStateChangeListener = listener;
        if(listener == null){
            unRegisterListener();
        } else {
            registerListener();
        }
    }
}
