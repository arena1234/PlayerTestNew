package com.vr.player.sensor;

import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.VelocityTracker;

import com.vr.player.L;
import com.vr.player.Utils;
import com.vr.player.ndk.NativeApi;
import com.vr.player.settings.ISettings;
import com.vr.player.settings.SettingsManager.CtrlStyle;

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
    private NativeApi mNativeApi;
    private float mMoveRateWithZoom = 1.0f;
    private float mDefaultFov = 90;
    private float mDeltaX, mDeltaY;
    private VelocityTracker mVelocityTracker;
    private float mFps = 30;

    public TouchCtrl(ISettings settings) {
        mSettings = settings;
        mNativeApi = new NativeApi();
        mDefaultFov = mNativeApi.getFov(true);
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                removeMsg();
                // 仅支持单指拖拽
                if (event.getPointerCount() == 1 &&
                        (mSettings.getSettingsManager().getCtrlStyle() == CtrlStyle.CS_DRAG ||
                                mSettings.getSettingsManager().getCtrlStyle() == CtrlStyle.CS_DRAG_ZOOM)) {
                    mMode = MODE_DRAG;
                    mStartPoint.set(event.getX(), event.getY());
                    mLastTime = System.currentTimeMillis();
                    if(mVelocityTracker == null){
                        mVelocityTracker = VelocityTracker.obtain();
                        mVelocityTracker.addMovement(event);
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                removeMsg();
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
                if(mVelocityTracker != null){
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if(mVelocityTracker != null && mMode == MODE_DRAG){
                    mVelocityTracker.computeCurrentVelocity(1000);
                    fling(event.getX(), event.getY(),
                            mVelocityTracker.getXVelocity(), mVelocityTracker.getYVelocity());
                }
                mMode = MODE_NORMAL;
                if(mMoveStateChangeListener != null){
                    mMoveStateChangeListener.onClear();
                }
                float rate = -0.1f;
                float currFov = mNativeApi.getFov(true);
                mMoveRateWithZoom = rate + (currFov / mDefaultFov * (1 - rate));
                break;
            case MotionEvent.ACTION_MOVE:
                if (mMode == MODE_DRAG) {
                    long time = System.currentTimeMillis();
                    setDrag(event, time - mLastTime);
                    mLastTime = time;
                    if(mVelocityTracker != null) mVelocityTracker.addMovement(event);
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

    private void setZoom(float distance) {
        if (distance == mDistance) return;
        float deltaFov = (distance - mDistance > 0 ? 1 : -1) * distance / mDistance; // 获取将要缩放到比例,一般是1左右
        mDistance = distance;
        deltaFov = -deltaFov * 2;
        if (mMoveStateChangeListener != null) {
            mMoveStateChangeListener.onMoveStateChanged(0, 0, 0, deltaFov);
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

        if (mSettings.getSettingsManager().getCtrlStyle() == CtrlStyle.CS_DRAG) {
            float deltaZ;
            if (Math.abs(dx) > Math.abs(dy)) {
                deltaZ = (float) Math.toDegrees(dx);
            } else {
                deltaZ = (float) Math.toDegrees(dy);
            }

            if (mMoveStateChangeListener != null) {
                mMoveStateChangeListener.onMoveStateChanged(0, 0, deltaZ, 0);
            }
        } else {
            if (Math.abs(dx) > Math.abs(dy)) {
                float deltaX = -(float) Math.toDegrees(dx);

                deltaX *= mMoveRateWithZoom;
                if (mMoveStateChangeListener != null) {
                    mMoveStateChangeListener.onMoveStateChanged(deltaX, 0, 0, 0);
                }
            } else {
                float deltaY = (float) Math.toDegrees(dy);

                deltaY *= mMoveRateWithZoom;
                if (mMoveStateChangeListener != null) {
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

    public void setOnMoveStateChangeListener(OnMoveStateChangeListener listener) {
        mMoveStateChangeListener = listener;
    }

    public void setFps(float fps){
        mFps = fps;
    }

    private static final float DEGREE_PER_1000PX = 1.2f;
    private void fling(float endX, float endY, float vx, float vy){
        float deltaX = endX - mStartPoint.x;
        float deltaY = endY - mStartPoint.y;
//        if(Math.abs(deltaX) < vx) vx = deltaX;
//        if(Math.abs(deltaY) < vy) vy = deltaY;
        float absVx = Math.abs(vx);
        float absVy = Math.abs(vy);
        if(absVx > absVy){
            mDeltaX = -vx * DEGREE_PER_1000PX / 1000;
            mDeltaY = 0;
        } else {
            mDeltaX = 0;
            mDeltaY = vy * DEGREE_PER_1000PX / 1000;
        }

        if(mDeltaX != 0 || mDeltaY != 0){
            step = MIN;
            animOver();
        }
    }

    private static final float MIN = 0.2f;
    private float step = MIN;
    private Handler mAnimOver = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(Math.abs(mDeltaX) > MIN) {
                mDeltaX -= mDeltaX > 0 ? step : -step;
                mDeltaY = 0;
                animOver();
            } else if(Math.abs(mDeltaY) > MIN){
                mDeltaX = 0;
                mDeltaY -= mDeltaY > 0? step : -step;
                animOver();
            } else {
                removeMsg();
            }
        }
    };

    private void animOver(){
        step -= 0.002;
        if(step < MIN / 10) {
            step += 0.002;
        }
        if(mMoveStateChangeListener != null){
            mMoveStateChangeListener.onMoveStateChanged(mDeltaX, mDeltaY, 0, 0);
        }
        mAnimOver.sendEmptyMessageDelayed(0, (long) (1000f / mFps));
    }

    private void removeMsg(){
        step = 0f;
        mAnimOver.removeMessages(0);
        if(mMoveStateChangeListener != null) mMoveStateChangeListener.onClear();
    }
}
