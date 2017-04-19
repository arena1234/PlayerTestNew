package com.wq.playertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

import com.vr.player.VideoLayout;
import com.vr.player.player.PlayManager;
import com.vr.player.settings.SettingsManager;
import com.wq.playertest.ui.PlayButton;
import com.wq.playertest.ui.ResolutionButton;
import com.wq.playertest.ui.TouchButton;

/**
 * 客户需求：
 * 1)原像模式：原始的圆形图像(可否做360度平面旋转，类似转盘)
 * 2)默认所有视角 zoom缩到最小。
 * 3)边界问题，1640点边界扭曲较大，增加点数
 * 4)模式只保留
 * a)原像
 * b)前视(zoom拉到最小)
 * c)前后(zoom拉到最小)
 * d)仰视(zoom 缩到最小，是否可以调整不全屏，变成正方形视窗，修改缩放bug)
 * 5)陀螺仪模式去掉
 */
public class NewPlayerActivity extends BaseActivity implements
        View.OnClickListener,
        SeekBar.OnSeekBarChangeListener,
        PlayManager.Listener {
    private VideoLayout mVideoLayout;
    private UIManager mUIManager;
    private boolean isUseBitmap = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_new);

        ImageUtil.init(this);

        mVideoLayout = (VideoLayout) findViewById(R.id.layout_video);

        mUIManager = new UIManager(findViewById(R.id.layout_player_ctrl));
        mUIManager.setOnClickListener(this);
        mUIManager.setOnSeekBarChangeListener(this);

        setPlayerCtrlVisibility(true);

        Bundle bundle = getIntent().getExtras();
        String fileName = bundle.getString("file_name");
        if (fileName != null) {
            if(fileName.equalsIgnoreCase("use_bitmap")){
                isUseBitmap = true;
                mVideoLayout.setUseBitmap(ImageUtil.mBmp[0], this);
            } else {
                isUseBitmap = false;
                mVideoLayout.setVideoSource(fileName, this);
            }
        } else {
            isUseBitmap = true;
            mVideoLayout.setUseBitmap(ImageUtil.mBmp[0], this);
        }
    }

    @Override
    protected void setSystemUiState() {
        setSystemUIFull();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoLayout.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoLayout.onPause();
        removeMSG();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoLayout.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        setPlayerCtrlVisibility(true);
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.vr_btn_player_play:
                if (mUIManager.getBtnPlay().getState() == PlayButton.State.Pause) {
                    mVideoLayout.pause();
                    if(isUseBitmap) removeMSG();
                } else {
                    mVideoLayout.play();
                    if(isUseBitmap) sendMsg(MSG_UPDATE_FRAME, 0);
                }
                break;
            case R.id.vr_btn_angle_reset:
                mVideoLayout.resetRotate();
                break;
            case R.id.vr_btn_resolution:
                if (mUIManager.getResolutionButton().getState() == ResolutionButton.State.P4K) {
                    mVideoLayout.setResolutionRatio(SettingsManager.ResolutionRatio.RR_4K);
                } else {
                    mVideoLayout.setResolutionRatio(SettingsManager.ResolutionRatio.RR_720P);
                }
                break;
            case R.id.vr_btn_touch:
                if (mUIManager.getTouchButton().getState() == TouchButton.State.Touch) {
                    mVideoLayout.setCtrlStyle(SettingsManager.CtrlStyle.CS_DRAG_ZOOM);
                } else {
                    mVideoLayout.setCtrlStyle(SettingsManager.CtrlStyle.CS_SENSOR);
                }
                break;
            case R.id.vr_btn_show_mode:
                switch (mUIManager.getShowModeButton().getState()) {
                    case Original:
                        mVideoLayout.setShowMode(SettingsManager.ShowMode.SM_ORIGINAL);
                        mVideoLayout.setCtrlStyle(SettingsManager.CtrlStyle.CS_DRAG);
                        mUIManager.getTouchButton().setVisibility(View.INVISIBLE);
                        break;
                    case Front:
                        mVideoLayout.setShowMode(SettingsManager.ShowMode.SM_SPHERE_FRONT);
                        mVideoLayout.setCtrlStyle(SettingsManager.CtrlStyle.CS_DRAG_ZOOM);
                        mUIManager.getTouchButton().reset();
                        mUIManager.getTouchButton().setVisibility(View.VISIBLE);
                        break;
                    case FrontBack:
                        mVideoLayout.setShowMode(SettingsManager.ShowMode.SM_SPHERE_FRONT_BACK);
                        mVideoLayout.setCtrlStyle(SettingsManager.CtrlStyle.CS_DRAG_ZOOM);
                        mUIManager.getTouchButton().reset();
                        mUIManager.getTouchButton().setVisibility(View.VISIBLE);
                        break;
                    case Up:
                        mVideoLayout.setShowMode(SettingsManager.ShowMode.SM_SPHERE_UP);
                        mVideoLayout.setCtrlStyle(SettingsManager.CtrlStyle.CS_DRAG_ZOOM);
                        mUIManager.getTouchButton().reset();
                        mUIManager.getTouchButton().setVisibility(View.VISIBLE);
                        break;
                    case Down:
                        mVideoLayout.setShowMode(SettingsManager.ShowMode.SM_SPHERE_DOWN);
                        mVideoLayout.setCtrlStyle(SettingsManager.CtrlStyle.CS_DRAG_ZOOM);
                        mUIManager.getTouchButton().reset();
                        mUIManager.getTouchButton().setVisibility(View.VISIBLE);
                        break;
                    case VR:
                        mVideoLayout.setShowMode(SettingsManager.ShowMode.SM_SPHERE_VR);
                        mVideoLayout.setCtrlStyle(SettingsManager.CtrlStyle.CS_SENSOR);
                        mUIManager.getTouchButton().setVisibility(View.INVISIBLE);
                        break;
                    case Plane:
                        mVideoLayout.setShowMode(SettingsManager.ShowMode.SM_PLANE_UP_DOWN);
                        mVideoLayout.setCtrlStyle(SettingsManager.CtrlStyle.CS_NULL);
                        mUIManager.getTouchButton().setVisibility(View.INVISIBLE);
                        break;
                }
                break;
        }
        setPlayerCtrlVisibility(true);
    }

    private int userPlayerProgress = 0;
    private boolean isSeekDrag = false;

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.vr_player_progress:
                userPlayerProgress = progress;
                if (fromUser) setPlayerCtrlVisibility(true);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.vr_player_progress) {
            isSeekDrag = true;
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.vr_player_progress) {
            isSeekDrag = false;
            mVideoLayout.seekTo(userPlayerProgress);
        }
    }

    @Override
    public void updatePlayingProgress(int time) {
        if (!isSeekDrag) {
            mUIManager.updatePlayerProgress(time);
        }
    }

    @Override
    public void updateBufferProgress(int percent) {
        mUIManager.updateBufferPrograss(percent);
    }

    @Override
    public void onBufferingStart() {

    }

    @Override
    public void onBufferingFinish() {

    }

    @Override
    public void onCompletion() {
        finish();
    }

    @Override
    public void onPrepareFinish() {
        if(!isUseBitmap) {
            mUIManager.initPlayerProgress(mVideoLayout.getTotalTime());
        } else {
            sendMsg(MSG_UPDATE_FRAME, 0);
        }

        mVideoLayout.setShowMode(SettingsManager.ShowMode.SM_ORIGINAL);
        mVideoLayout.setCtrlStyle(SettingsManager.CtrlStyle.CS_DRAG);
        mUIManager.getTouchButton().setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPrepareStart() {
    }

    @Override
    public void onFrameUpdate() {

    }

    private void setPlayerCtrlVisibility(boolean visibility) {
        if (visibility) {
            mUIManager.show();
            sendMsg(MSG_DRAW_PLAYER_CTRL_HIDE, 3000);
        } else {
            mUIManager.hide();
        }
    }

    private static final int MSG_DRAW_PLAYER_CTRL_HIDE = 1;
    private static final int MSG_UPDATE_FRAME = 2;
    private Handler mDelayHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_DRAW_PLAYER_CTRL_HIDE:
                    if (mVideoLayout.isPlaying()) {
                        setPlayerCtrlVisibility(false);
                    }
                    break;
                case MSG_UPDATE_FRAME:
                    mVideoLayout.updateFrame(ImageUtil.mBmp[index]);
                    index ++;
                    index = index % ImageUtil.mBmp.length;
                    sendMsg(MSG_UPDATE_FRAME, 33);
                    break;
            }
        }
    };
    private int index = 0;

    private void sendMsg(int msg, int delay) {
        mDelayHandler.removeMessages(msg);
        mDelayHandler.sendEmptyMessageDelayed(msg, delay);
    }

    private void removeMSG() {
        mDelayHandler.removeMessages(MSG_DRAW_PLAYER_CTRL_HIDE);
        mDelayHandler.removeMessages(MSG_UPDATE_FRAME);
    }
}
