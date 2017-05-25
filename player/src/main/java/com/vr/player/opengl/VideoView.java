package com.vr.player.opengl;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.vr.player.L;
import com.vr.player.Utils;
import com.vr.player.ndk.NativeApi;
import com.vr.player.sensor.MoveManager;
import com.vr.player.settings.ISettings;
import com.vr.player.settings.RotateLimitManager;
import com.vr.player.settings.SettingsManager;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class VideoView extends GLSurfaceView implements ISettings {
    private static final String TAG = "VR_VideoView";
    protected FullViewRender mRender;
    protected MoveManager mMoveManager;
    protected RotateLimitManager mRotateLimitManager;
    private SettingsManager mSettingsManager;
    private int mTextureID = -1;
    private NativeApi mNativeApi;

    public VideoView(Context context) {
        super(context);
        init();
    }

    public VideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mNativeApi = new NativeApi();
        mRotateLimitManager = new RotateLimitManager();
        mSettingsManager = new SettingsManager();
        mMoveManager = new MoveManager(this);
    }

    public void initRender(boolean isSingle, Bitmap bmp) {
        mRender = new FullViewRender(isSingle, bmp);
        setRenderer(mRender);
        setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

    public void updateFrame(Bitmap bmp) {
        mRender.updateFrame(bmp);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMoveManager != null) {
            mMoveManager.initSensor(getContext());
            mMoveManager.registerSensor();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mMoveManager != null) {
            mMoveManager.unRegisterSensor();
        }
    }

    public void onDestroy() {
    }

    @Override
    public SettingsManager getSettingsManager() {
        return mSettingsManager;
    }

    @Override
    public MoveManager getMoveManager() {
        return mMoveManager;
    }

    @Override
    public RotateLimitManager getRotateLimitManager() {
        return mRotateLimitManager;
    }

    public class FullViewRender implements Renderer {
        private boolean isSinglePicture = false;
        private boolean bUpdateImg = false;
        private Bitmap mBmpFrame = null;
        private Object mSyncObj = new Object();

        public FullViewRender(boolean isSingle, Bitmap bmp) {
            isSinglePicture = isSingle;
            mBmpFrame = bmp;
            L.d(TAG, "FullViewRender FullViewRender=" + isSinglePicture);
        }

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            mTextureID = mNativeApi.onSurfaceCreated(isSinglePicture);
            if (isSinglePicture) {
                TextureUtil.bindTexture(mBmpFrame, mTextureID);
            }
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            Utils.RATIO_FOR_OPENGL_VIEW = (float) width / (float) height;
            Utils.WIDTH_FOR_OPENGL_VIEW = width;
            Utils.HEIGHT_FOR_OPENGL_VIEW = height;
            Utils.DEGREE_X_FOR_OPENGL = Utils.DEGREE_Y_FOR_OPENGL * Utils.RATIO_FOR_OPENGL_VIEW;

            mNativeApi.onSurfaceChanged(width, height);
        }

        public void updateFrame(Bitmap bmp) {
            synchronized (mSyncObj) {
                bUpdateImg = true;
                mBmpFrame = bmp;
            }
        }

        long lastTime = 0;
        int fpsCount = 0;

        @Override
        public void onDrawFrame(GL10 gl) {
            if (mMoveManager == null) return;

            boolean isLeft = mSettingsManager.isLeftOrTop();
            mNativeApi.onDrawFrame(
                    mNativeApi.getDegreeX(isLeft) + mMoveManager.getDeltaDegreeX(),
                    mNativeApi.getDegreeY(isLeft) + mMoveManager.getDeltaDegreeY(),
                    mNativeApi.getDegreeZ(isLeft) + mMoveManager.getDeltaDegreeZ(),
                    mNativeApi.getFov(isLeft) + mMoveManager.getDeltaFov(),
                    isLeft);
            if (isSinglePicture) {
                synchronized (mSyncObj) {
                    if (bUpdateImg && mBmpFrame != null && !mBmpFrame.isRecycled()) {
                        bUpdateImg = false;
                        try {
                            gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureID);
                            GLUtils.texSubImage2D(GL10.GL_TEXTURE_2D, 0, 0, 0, mBmpFrame);
                        } finally {
//                            if (mBmpFrame != null) {
//                                mBmpFrame.recycle();
//                                mBmpFrame = null;
//                            }
                        }
                    }
                }
                mNativeApi.draw(mTextureID);
            } else {
                TextureUtil.draw(mNativeApi, mTextureID);
            }

            fpsCount++;
            long currTime = System.currentTimeMillis();
            if (currTime - lastTime >= 5000) {
                lastTime = currTime;
                float fps = (fpsCount * 0.2f);
                L.d(TAG, "fps=" + fps);
                fpsCount = 0;
                mMoveManager.setFps(fps);
            }
        }
    }

    //@Override
    public boolean touchEvent(MotionEvent event) {
        return mMoveManager != null ? mMoveManager.onTouchEvent(event) : false;
    }

    public void resetRotate() {
        mNativeApi.resetDegree();
        if (mMoveManager != null) mMoveManager.resetSensor();
    }

    private void setShowMode(SettingsManager.ShowMode mode, SettingsManager.ResolutionRatio ratio, boolean isLeftOrTop) {
        mSettingsManager.setLeftOrTop(isLeftOrTop);
        mSettingsManager.setShowMode(mode);
        mSettingsManager.setResolutionRatio(ratio);
        mNativeApi.setShowMode(mode, ratio, isLeftOrTop);
        resetRotate();
    }

    public void setShowMode(SettingsManager.ShowMode mode, boolean isLeftOrTop) {
        setShowMode(mode, mSettingsManager.getResolutionRatio(), isLeftOrTop);
    }

    public void setResolutionRatio(SettingsManager.ResolutionRatio ratio) {
        setShowMode(mSettingsManager.getShowMode(), ratio, mSettingsManager.isLeftOrTop());
    }

    public void setCtrlStyle(SettingsManager.CtrlStyle style) {
        mSettingsManager.setCtrlStyle(style);
        mMoveManager.initSensor(getContext());
        resetRotate();
    }
}
