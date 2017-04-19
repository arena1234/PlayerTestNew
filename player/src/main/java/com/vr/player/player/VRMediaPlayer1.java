package com.vr.player.player;

import android.media.MediaPlayer;

/**
 * Created by arena on 2017/2/23.
 */

public class VRMediaPlayer1 extends MediaPlayer {

    private OnPreparedListener mOnPreparedListener;
    private OnCompletionListener mOnCompletionListener;
    private OnBufferingUpdateListener mOnBufferingUpdateListener;
    public interface OnPreparedListener {
        void onPrepared();
    }
    public interface OnCompletionListener {
        void onCompletion();
    }

    public interface OnBufferingUpdateListener {
        void onBufferingUpdate(int percent);
    }

    public void setOnBufferingUpdateListener(OnBufferingUpdateListener listener) {
        mOnBufferingUpdateListener = listener;
        super.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                if(mOnBufferingUpdateListener != null) mOnBufferingUpdateListener.onBufferingUpdate(percent);
            }
        });
    }

    public void setOnCompletionListener(OnCompletionListener listener) {
        mOnCompletionListener = listener;
        super.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(mOnCompletionListener != null) mOnCompletionListener.onCompletion();
            }
        });
    }

    public void setOnPreparedListener(OnPreparedListener listener) {
        mOnPreparedListener = listener;
        super.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if(mOnPreparedListener != null) mOnPreparedListener.onPrepared();
            }
        });
    }
}
