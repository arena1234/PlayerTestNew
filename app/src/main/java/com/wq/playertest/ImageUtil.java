package com.wq.playertest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by arena on 2017/2/14.
 */

public class ImageUtil {
    public static Bitmap[] mBmp = new Bitmap[5];
    public static void init(Context context){
        mBmp[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_1);
        mBmp[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_2);
        mBmp[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_3);
        mBmp[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_4);
        mBmp[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_5);
    }
}
