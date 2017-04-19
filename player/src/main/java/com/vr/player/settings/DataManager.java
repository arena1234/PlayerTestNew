package com.vr.player.settings;

import android.content.Context;

/**
 * Created by arena on 2017/1/19.
 */

public class DataManager {
//    private static final String TAG = "VR_DataManager";
//    private static final String FILE_NAME = "data_storage";
//    private static final String KEY_HARD_ID = "hard_id";
//    private static final String KEY_RESULT_1 = "result_1";
//    private static final String KEY_RESULT_2 = "result_2";
//    private SharedPreferences mSharedPreferences;
    private static String mHardId = null;//"6666661111111111";
    private static String mResult1 = null;//"F7B22EAD1C13DE9CB83E84823691E46EDA0839FCD520AC6641B8B7054FE86C03";
    private static String mResult2 = null;//"32DE04DAFE29FEAE06ECACA6FDB5044AC25AF2F2A985145C6BC7493AADE68DEA";

    public DataManager(Context context){
//        mSharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public static String getHardId() {
        return mHardId;
    }

    public static String getResult1() {
        return mResult1;
    }

    public static String getResult2() {
        return mResult2;
    }

    //    private synchronized void write(String key, String value){
//        SharedPreferences.Editor editor = mSharedPreferences.edit();
//        editor.putString(key, value);
//        editor.commit();
//        L.d(TAG, "write key="+key+", value="+value);
//    }
//
//    private synchronized String read(String key, String defaultValue){
//        String result = mSharedPreferences.getString(key, defaultValue);
//        L.d(TAG, "read key="+key+", value="+result);
//        return result;
//    }
//
//    public String getHardId(){
//        return read(KEY_HARD_ID, null);
//    }
//
//    public String getResult1(){
//        return read(KEY_RESULT_1, null);
//    }
//
//    public String getResult2(){
//        return read(KEY_RESULT_2, null);
//    }
//
    public static synchronized void setAesData(String hardId, String result1, String result2){
        mHardId = hardId;
        mResult1 = result1;
        mResult2 = result2;
//        SharedPreferences.Editor editor = mSharedPreferences.edit();
//        editor.putString(KEY_HARD_ID, hardId);
//    editor.putString(KEY_RESULT_1, result1);
//    editor.putString(KEY_RESULT_2, result2);
//    editor.commit();
//    L.d(TAG, "write hardId="+hardId+", result1="+result1+", result2="+result2);
}
//
//    public void clear(){
//        SharedPreferences.Editor editor = mSharedPreferences.edit();
//        editor.clear();
//        editor.commit();
//        L.d(TAG, "clear");
//    }
}
