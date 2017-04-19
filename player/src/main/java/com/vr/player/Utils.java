package com.vr.player;

import android.os.Environment;

public class Utils {
    public static final String ROOT_PATH = Environment.getExternalStorageDirectory()+"/Movies";

    public static final float DEGREE_Y_FOR_OPENGL = 90;     // 投影Y轴角度
    public static float RATIO_FOR_OPENGL_VIEW = 16.0f/9.0f; // 显示比例
    public static int WIDTH_FOR_OPENGL_VIEW = 0;            // 显示宽度
    public static int HEIGHT_FOR_OPENGL_VIEW = 0;           // 显示高度
    public static float DEGREE_X_FOR_OPENGL = DEGREE_Y_FOR_OPENGL*RATIO_FOR_OPENGL_VIEW;

    public static final int ZOOM_AMP_MAX = 85;
    public static final int ZOOM_AMP_MIN = 5;
    public static final int ZOOM_AMP_DEFAULT = 5;
    public static final float ZOOM_AMP = 100.0f;
    public static final float ZOOM_MAX = ZOOM_AMP_MAX/ZOOM_AMP;
    public static final float ZOOM_MIN = ZOOM_AMP_MIN/ZOOM_AMP;
    public static final float ZOOM_DELTA_MAX = (ZOOM_MAX-ZOOM_MIN)/20.0f;
}
