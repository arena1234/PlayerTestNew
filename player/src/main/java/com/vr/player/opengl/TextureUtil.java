package com.vr.player.opengl;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.opengl.GLES10;
import android.opengl.GLES11Ext;
import android.opengl.GLUtils;

import com.vr.player.ndk.NativeApi;

import javax.microedition.khronos.opengles.GL10;

public class TextureUtil {
    static SurfaceTexture mSurfaceTexture;

    public static SurfaceTexture getInstance() {
        if (mSurfaceTexture == null) {
            mSurfaceTexture = new SurfaceTexture(createVideoTextureID());
            mSurfaceTexture.detachFromGLContext();
        }
        return mSurfaceTexture;
    }

    private static int createVideoTextureID() {
        GLES10.glEnable(GLES11Ext.GL_TEXTURE_EXTERNAL_OES);
        int[] texture = new int[2];
        GLES10.glGenTextures(2, texture, 0);
        GLES10.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, texture[0]);
        GLES10.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        GLES10.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        GLES10.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        GLES10.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);

        return texture[0];
    }

    public static void bindTexture(Bitmap bmp, int textureId) {
        try {
            GLES10.glBindTexture(GLES10.GL_TEXTURE_2D, textureId);
            GLUtils.texImage2D(GLES10.GL_TEXTURE_2D, 0, bmp, 0);
        } finally {
            // 生成纹理之后，回收位图
            if (bmp != null)
                bmp.recycle();
        }
    }

    public synchronized static void draw(NativeApi nativeApi, int textureID) {
        getInstance().attachToGLContext(textureID);
        getInstance().updateTexImage();
        nativeApi.draw(textureID);
        getInstance().detachFromGLContext();
    }
}
