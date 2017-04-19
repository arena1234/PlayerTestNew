package com.vr.player.sensor;

import android.graphics.PointF;
import android.view.MotionEvent;

import com.vr.player.L;
import com.vr.player.Utils;
import com.vr.player.settings.ISettings;
import com.vr.player.settings.SettingsManager.CtrlStyle;

/**
 * Created by arena on 2017/1/5.
 */

public class TouchCtrl {
    private static final String TAG = "VR_TouchCtrl";
    private ISettings mSettings;
    private PointF mStartPoint = new PointF();
    private PointF mMidPoint = new PointF();    // 记录按下时两指的中点(缩放,以该点为中心点缩放)
    private float mDistance = 0;                // 记录两指间间距

    private long mLastTime = 0;

    private static final int MODE_NORMAL = 1;          // 正常模式
    private static final int MODE_DRAG = 2;            // 图片拖动模式
    private static final int MODE_ZOOM = 3;            // 图片缩放模式
    private int mMode = MODE_NORMAL;

    public TouchCtrl(ISettings settings) {
        mSettings = settings;
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                // 仅支持单指拖拽
                if (event.getPointerCount() == 1 &&
                        (mSettings.getSettingsManager().getCtrlStyle() == CtrlStyle.CS_DRAG ||
                         mSettings.getSettingsManager().getCtrlStyle() == CtrlStyle.CS_DRAG_ZOOM)) {
                    mMode = MODE_DRAG;
                    mStartPoint.set(event.getX(), event.getY());
                    mLastTime = System.currentTimeMillis();
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                // 仅支持两指缩放
                if (event.getPointerCount() == 2 &&
                        mSettings.getSettingsManager().getCtrlStyle() == CtrlStyle.CS_DRAG_ZOOM) {
                    mDistance = distance(event);
                    // 按下时两指间距大于10,执行手机动态缩放
                    if (mDistance > 10) {
                        mMode = MODE_ZOOM;
                        mMidPoint = midPoint(event);
                    }
                } else {
                    mMode = MODE_NORMAL;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mMode = MODE_NORMAL;
                if(mMoveStateChangeListener != null) mMoveStateChangeListener.onClear();
                break;
            case MotionEvent.ACTION_MOVE:
                if (mMode == MODE_DRAG) {
                    long time = System.currentTimeMillis();
                    setDrag(event, time - mLastTime);
                    mLastTime = time;
                } else if (mMode == MODE_ZOOM) {
                    setZoom(event);
                }
                break;
        }
        return true;
    }

    private void setZoom(MotionEvent event) {
        setZoom(distance(event));
    }

    private void setZoom(float distance){
        if (distance == mDistance) return;
        float deltaFov = (distance - mDistance > 0 ? 1 : -1) * distance / mDistance; // 获取将要缩放到比例,一般是1左右
        mDistance = distance;

        if(mMoveStateChangeListener != null) {
            mMoveStateChangeListener.onMoveStateChanged(0, 0, 0, -deltaFov * 2);
        }
    }

    private void setDrag(MotionEvent event, long time) {
        if (Utils.WIDTH_FOR_OPENGL_VIEW <= 0 && Utils.HEIGHT_FOR_OPENGL_VIEW <= 0) return;
        // 计算拖拽的距离
        float dx = event.getX() - mStartPoint.x;
        float dy = event.getY() - mStartPoint.y;
        float spanTime = time * 1.0f / 1000f;

        mStartPoint.set(event.getX(), event.getY());

        dx = Utils.DEGREE_X_FOR_OPENGL * dx / Utils.WIDTH_FOR_OPENGL_VIEW;
        dy = Utils.DEGREE_Y_FOR_OPENGL * dy / Utils.HEIGHT_FOR_OPENGL_VIEW;

        setDrag(dx, dy, spanTime);
    }

    private void setDrag(float dx, float dy, float spanTime) {
        dx = dx * spanTime * 2;
        dy = dy * spanTime * 2;

        if(mSettings.getSettingsManager().getCtrlStyle() == CtrlStyle.CS_DRAG){
            float deltaZ;
            if (Math.abs(dx) > Math.abs(dy)) {
                deltaZ = (float) Math.toDegrees(dx);
            } else{
                deltaZ = (float) Math.toDegrees(dy);
            }

            if(mMoveStateChangeListener != null) {
                mMoveStateChangeListener.onMoveStateChanged(0, 0, deltaZ, 0);
            }
        } else {
            if (Math.abs(dx) > Math.abs(dy)) {
                float deltaX = (float) Math.toDegrees(dx);

                if(mMoveStateChangeListener != null) {
                    mMoveStateChangeListener.onMoveStateChanged(-deltaX, 0, 0, 0);
                }
            } else {
                float deltaY = (float) Math.toDegrees(dy);

                if(mMoveStateChangeListener != null) {
                    mMoveStateChangeListener.onMoveStateChanged(0, deltaY, 0, 0);
                }
            }
        }
    }

    private float distance(MotionEvent event) {
        if (event.getPointerCount() == 2) {
            float dx = event.getX(1) - event.getX(0);
            float dy = event.getY(1) - event.getY(0);
            /** 使用勾股定理返回两点之间的距离 */
            return (float) Math.sqrt(dx * dx + dy * dy);
        } else {
            L.e(TAG, "distance:Count of pointer must be two!!!");
            return mDistance;
        }
    }

    /**
     * 两点的中点
     */
    private PointF midPoint(MotionEvent event) {
        if (event.getPointerCount() == 2) {
            PointF point = new PointF();
            float x = event.getX(0) + event.getX(1);
            float y = event.getY(0) + event.getY(1);
            point.set(x / 2, y / 2);
            return point;
        } else {
            L.e(TAG, "midPoint:Count of pointer must be two!!!");
            return mMidPoint;
        }
    }

    private OnMoveStateChangeListener mMoveStateChangeListener;

    public void setOnMoveStateChangeListener(OnMoveStateChangeListener listener){
        mMoveStateChangeListener = listener;
    }
}