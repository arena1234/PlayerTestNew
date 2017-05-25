package com.vr.player;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vr.player.opengl.TextureUtil;
import com.vr.player.opengl.VideoView;
import com.vr.player.player.PlayManager;
import com.vr.player.security.Licence;
import com.vr.player.settings.SettingsManager;

/**
 * Created by qiangwang on 1/16/17.
 */

public class VideoLayout extends LinearLayout implements PlayManager.Listener {
    private static final String TAG = "VR_VideoLayout";
    private VideoView mLeftSurfaceView = null;
    private VideoView mRightSurfaceView = null;
    private TextView mNoLicenceTextView = null;
    private ShowState mShowState = ShowState.Single;
    private PlayState mPlayState = PlayState.UnPrepare;
    private PlayManager mPlayManager = null;
    private PlayManager.Listener mPlayerListener;

    public VideoLayout(Context context) {
        super(context);
        init();
    }

    public VideoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        initSurface();

        setShowState(ShowState.Single);
        setPlayState(PlayState.UnPrepare);

        L.d(TAG, "init VideoLayout");
    }

    private long mLastTime = 0;
    private PointF mStartPoint = new PointF();
    private boolean isLeft = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mLastTime = System.currentTimeMillis();
                mStartPoint.set(event.getX(), event.getY());
                if (event.getX() < mLeftSurfaceView.getRight() && event.getX() > mLeftSurfaceView.getLeft() &&
                        event.getY() < mLeftSurfaceView.getBottom() && event.getY() > mLeftSurfaceView.getTop()) {
                    isLeft = true;
                } else if (event.getX() < mRightSurfaceView.getRight() && event.getX() > mRightSurfaceView.getLeft() &&
                        event.getY() < mRightSurfaceView.getBottom() && event.getY() > mRightSurfaceView.getTop()) {
                    isLeft = false;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (System.currentTimeMillis() - mLastTime < 200
                        && event.getX() - mStartPoint.x < 10
                        && event.getY() - mStartPoint.y < 10) {
                    mLeftSurfaceView.getMoveManager().onClear();
                    mRightSurfaceView.getMoveManager().onClear();
                    return super.onTouchEvent(event);
                }
                break;
        }
        if (mLeftSurfaceView.getSettingsManager().getShowMode() == SettingsManager.ShowMode.SM_SPHERE_VR) {
            return mLeftSurfaceView.touchEvent(event) && mRightSurfaceView.touchEvent(event);
        } else if (isLeft) {
            return mLeftSurfaceView.touchEvent(event);
        } else {
            return mRightSurfaceView.touchEvent(event);
        }
    }

    private void initSurface() {
        LinearLayout.LayoutParams lp = new LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp.weight = 1;
        mLeftSurfaceView = new VideoView(getContext());
        mRightSurfaceView = new VideoView(getContext());
        mNoLicenceTextView = new TextView(getContext());
        mNoLicenceTextView.setTextSize(48);
        mNoLicenceTextView.setText("您没有被授权!");
        mNoLicenceTextView.setTextColor(Color.rgb(255, 255, 255));
        mNoLicenceTextView.setGravity(Gravity.CENTER);
        addView(mLeftSurfaceView, lp);
        addView(mRightSurfaceView, lp);
        addView(mNoLicenceTextView, lp);
    }

    private void addListener() {
        if (!isUsePlayer()) return;
        mPlayManager.setListener(this);
    }

    private void removeListener() {
        if (!isUsePlayer()) return;
        mPlayManager.setListener(null);
    }

    public void onResume() {
        L.d(TAG, "onResume");
        mLeftSurfaceView.onResume();
        mRightSurfaceView.onResume();
        addListener();
//        prepare();
        play();
    }

    public void onPause() {
        L.d(TAG, "onPause");
        removeListener();
        mLeftSurfaceView.onPause();
        mRightSurfaceView.onPause();
        pause();
        removeMSG();
    }

    public void onDestroy() {
        L.d(TAG, "onDestroy");
        removeView(mLeftSurfaceView);
        removeView(mRightSurfaceView);
        removeView(mNoLicenceTextView);
        mLeftSurfaceView.onDestroy();
        mRightSurfaceView.onDestroy();
        release();
    }

    public TextView getNoLicenceNoticeView(){
        return mNoLicenceTextView;
    }

    private void setShowState(ShowState state) {
        switch (state) {
            case Single:
                setOrientation(HORIZONTAL);
                mLeftSurfaceView.setVisibility(VISIBLE);
                mRightSurfaceView.setVisibility(GONE);
                mNoLicenceTextView.setVisibility(GONE);
                break;
            case LeftAndRight:
                setOrientation(HORIZONTAL);
                mLeftSurfaceView.setVisibility(VISIBLE);
                mRightSurfaceView.setVisibility(VISIBLE);
                mNoLicenceTextView.setVisibility(GONE);
                break;
            case UpAndDown:
                setOrientation(VERTICAL);
                mLeftSurfaceView.setVisibility(VISIBLE);
                mRightSurfaceView.setVisibility(VISIBLE);
                mNoLicenceTextView.setVisibility(GONE);
                break;
            case NoLicence:
                setOrientation(VERTICAL);
                mLeftSurfaceView.setVisibility(GONE);
                mRightSurfaceView.setVisibility(GONE);
                mNoLicenceTextView.setVisibility(VISIBLE);
                break;
            default:
                setOrientation(HORIZONTAL);
                mLeftSurfaceView.setVisibility(VISIBLE);
                mRightSurfaceView.setVisibility(GONE);
                mNoLicenceTextView.setVisibility(GONE);
                break;
        }
        mShowState = state;
        L.d(TAG, "setShowState : " + mShowState);
    }

    private ShowState getShowState() {
        return mShowState;
    }

    private void setPlayState(PlayState state) {
        mPlayState = state;
        L.d(TAG, "setPlayState : " + mPlayState);
    }

    private PlayState getPlayState() {
        return mPlayState;
    }

    @Override
    public void updatePlayingProgress(int time) {
        if (mPlayerListener != null) mPlayerListener.updatePlayingProgress(time);
    }

    @Override
    public void updateBufferProgress(int percent) {
        //L.d(TAG, "PlayManager updateBufferProgress percent="+percent);
        if (mPlayerListener != null) mPlayerListener.updateBufferProgress(percent);
    }

    @Override
    public void onBufferingStart() {
        if (mPlayerListener != null) mPlayerListener.onBufferingStart();
    }

    @Override
    public void onBufferingFinish() {
        if (mPlayerListener != null) mPlayerListener.onBufferingFinish();
    }

    @Override
    public void onCompletion() {
        L.d(TAG, "PlayManager onCompletion");
        if (mPlayerListener != null) mPlayerListener.onCompletion();
    }

    @Override
    public void onPrepareStart() {
        L.d(TAG, "PlayManager onPrepareStart");
        if (mPlayerListener != null) mPlayerListener.onPrepareStart();
    }

    @Override
    public void onPrepareFinish() {
        L.d(TAG, "PlayManager onPrepareFinish");
        setPlayState(PlayState.Prepared);
        readyShowMode();
        play();
        if (mPlayerListener != null) mPlayerListener.onPrepareFinish();
        Licence licence = new Licence(getContext());
        licence.setLicence(new Licence.Listener() {
            @Override
            public void onSuccess() {
                L.d(TAG, "setLicence onSuccess");
            }

            @Override
            public void onNetError() {
                L.e(TAG, "setLicence onNetError");
                setShowState(ShowState.NoLicence);
            }

            @Override
            public void onHardIDError() {
                L.e(TAG, "setLicence onHardIDError");
                setShowState(ShowState.NoLicence);
            }
        });
    }

    @Override
    public void onFrameUpdate() {
        //L.d(TAG, "onFrameUpdate");
        if (mPlayerListener != null) mPlayerListener.onFrameUpdate();
    }

    private boolean isInitSource = false;
    private boolean isSinglePicture = false;

    public void setVideoSource(final String path, PlayManager.Listener listener) {
        if (isInitSource) return;
        isInitSource = true;
        mPlayManager = new PlayManager();
        isSinglePicture = false;
        removeMSG();
        setFps(42);
        setPlayState(PlayState.UnPrepare);
        mLeftSurfaceView.initRender(isSinglePicture, null);
        mRightSurfaceView.initRender(isSinglePicture, null);
        mPlayerListener = listener;
        mPlayManager.initMedia(Uri.parse(path), getContext());
        prepare();
        sendMsg(MSG_DRAW_UPDATE_FRAME, 0);
        L.d(TAG, "setVideoSource path=" + path);
    }

    private int mSingleFrameTime = 33;

    public void setUseBitmap(Bitmap bitmap, int fps, PlayManager.Listener listener) {
        if (isInitSource) return;
        isInitSource = true;
        isSinglePicture = true;
        mPlayerListener = listener;
        release();
        setPlayState(PlayState.UnPrepare);
        setFps(fps);
        removeMSG();
        mLeftSurfaceView.initRender(isSinglePicture, bitmap);
        mRightSurfaceView.initRender(isSinglePicture, bitmap);
        //sendMsg(MSG_DRAW_UPDATE_FRAME, 0);
        L.d(TAG, "setUseBitmap");

        prepare();
        onPrepareStart();
        onPrepareFinish();
    }

    public void setUseBitmap(Bitmap bmp, PlayManager.Listener listener) {
        setUseBitmap(bmp, 24, listener);
    }

    public void setFps(int fps) {
        if (fps < 20) {
            fps = 20;
        }
        mSingleFrameTime = (int) (1000.0f / fps);
        L.d(TAG, "setFps fps=" + fps);
    }

    private boolean isUsePlayer() {
        return !isSinglePicture && mPlayManager != null;
    }

    private void readyShowMode() {
        setShowMode(SettingsManager.ShowMode.SM_ORIGINAL);
        setResolutionRatio(SettingsManager.ResolutionRatio.RR_4K);
        setCtrlStyle(SettingsManager.CtrlStyle.CS_DRAG);
    }

    private void prepare() {
        if (getPlayState() == PlayState.UnPrepare) {
            setPlayState(PlayState.Preparing);
            if (isUsePlayer()) {
                mPlayManager.prepareMediaSurface(TextureUtil.getInstance());
            }
        }
    }

    private void release() {
        if (getPlayState() != PlayState.UnPrepare) {
            setPlayState(PlayState.Release);
            removeMSG();
            if (isUsePlayer()) {
                mPlayManager.setListener(null);
                mPlayManager.stop();
                mPlayManager.release();
                mPlayManager = null;
            }
        }
    }

    public void play() {
        if (getPlayState() == PlayState.Prepared || getPlayState() == PlayState.Pause) {
            setPlayState(PlayState.Playing);
            if (isUsePlayer()) {
                mPlayManager.play();
            }
        }
    }

    public void pause() {
        if (getPlayState() == PlayState.Playing) {
            setPlayState(PlayState.Pause);
            removeMSG();
            sendMsg(MSG_DRAW_PLAYER_PAUSE, 0);
            if (isUsePlayer()) {
                mPlayManager.pause();
            }
        }
    }

    public boolean isPlaying() {
        return getPlayState() == PlayState.Playing;
    }

    public void seekTo(int ms) {
        if (!isUsePlayer()) return;
        mPlayManager.seekTo(ms);
    }

    public int getTotalTime() {
        if (!isUsePlayer()) return 0;
        return mPlayManager.getTotalTime();
    }

    public int getCurrentTime() {
        if (!isUsePlayer()) return 0;
        return mPlayManager.getCurrentTime();
    }

    public void resetRotate() {
        mLeftSurfaceView.resetRotate();
        mRightSurfaceView.resetRotate();
    }

    private void setShowState(SettingsManager.ShowMode mode) {
        switch (mode) {
            case SM_ORIGINAL:
            case SM_SPHERE_FRONT:
            case SM_SPHERE_UP:
            case SM_SPHERE_DOWN:
            case SM_PLANE_UP_DOWN:
            case SM_CYLINDER_UP_DOWN:
                setShowState(ShowState.Single);
                break;
            case SM_SPHERE_VR:
            case SM_SPHERE_FRONT_BACK:
                setShowState(ShowState.LeftAndRight);
                break;

        }
    }

    public void setShowMode(SettingsManager.ShowMode mode) {
        setShowState(mode);
        mLeftSurfaceView.setShowMode(mode, true);
        if (mode != SettingsManager.ShowMode.SM_SPHERE_VR) {
            mRightSurfaceView.setShowMode(mode, false);
        } else {
            mRightSurfaceView.setShowMode(mode, true);
        }
    }

    public void setResolutionRatio(SettingsManager.ResolutionRatio ratio) {
        mLeftSurfaceView.setResolutionRatio(ratio);
        mRightSurfaceView.setResolutionRatio(ratio);
    }

    public void setCtrlStyle(SettingsManager.CtrlStyle style) {
        mLeftSurfaceView.setCtrlStyle(style);
        mRightSurfaceView.setCtrlStyle(style);
    }

    private enum ShowState {
        Single,
        LeftAndRight,
        UpAndDown,
        NoLicence
    }

    private enum PlayState {
        UnPrepare,
        Preparing,
        Prepared,
        Playing,
        Pause,
        Release,
    }

    private Object mSyncObj = new Object();

    public void updateFrame(Bitmap bitmap) {
        if (isUsePlayer() || bitmap == null) return;
        synchronized (mSyncObj) {
            mLeftSurfaceView.updateFrame(bitmap);
            mRightSurfaceView.updateFrame(bitmap);

            mLeftSurfaceView.requestRender();
            mRightSurfaceView.requestRender();
            //removeMSG();
            //sendMsg(MSG_DRAW_UPDATE_FRAME, 0);
        }
    }

    private static final int MSG_DRAW_PLAYER_PAUSE = 1;
    private static final int MSG_DRAW_UPDATE_FRAME = 2;
    private Handler mDelayHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_DRAW_PLAYER_PAUSE:
                    if (!isPlaying()) {
                        mLeftSurfaceView.requestRender();
                        mRightSurfaceView.requestRender();
                        sendMsg(MSG_DRAW_PLAYER_PAUSE, mSingleFrameTime);
                    } else {
                        removeMSG();
                    }
                    break;
                case MSG_DRAW_UPDATE_FRAME:
                    mLeftSurfaceView.requestRender();
                    mRightSurfaceView.requestRender();
                    sendMsg(MSG_DRAW_UPDATE_FRAME, mSingleFrameTime);
                    break;
            }
        }
    };

    private void sendMsg(int msg, int delay) {
        mDelayHandler.removeMessages(msg);
        mDelayHandler.sendEmptyMessageDelayed(msg, delay);
    }

    private void removeMSG() {
        mDelayHandler.removeMessages(MSG_DRAW_PLAYER_PAUSE);
        mDelayHandler.removeMessages(MSG_DRAW_UPDATE_FRAME);
    }
}
