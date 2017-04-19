package com.vr.player.security;

import android.content.Context;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;

import com.vr.player.L;
import com.vr.player.ndk.NativeApi;
import com.vr.player.settings.DataManager;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qiangwang on 1/11/17.
 */

public class Licence {
    private static final String TAG = "VR_Licence";
    private static final String PARAMS_HARD_ID = "hardID";
    private static final String PARAMS_ANDROID_ID = "androidID";
    private static final String URL = "http://120.76.122.176/vr-api/check_auth.php";
    private Context mContext;
    private NativeApi mNativeApi;
    private String mHardId = "1111111111111111";
    private HttpReturnData mHttpReturnData;
    private QueryTask mQueryTask = null;

    private void resetQuery() {
        mQueryTask = null;
    }

    public Licence(Context context) {
        mContext = context;
        mNativeApi = new NativeApi();
    }

    private String getHardId(){
        return limitByte(mHardId);
    }

    private void setHardId(String hardId){
        mHardId = hardId;
    }

    private String getAndroidId() {
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        String androidId = tm.getDeviceId();
        if(androidId == null){
            androidId = "2222222222222222";
        }
        return limitByte(androidId);
    }

    public void setLicence(String hardId, Listener listener) {
        setHardId(hardId);
        addListener(listener);
        if (mQueryTask == null) {
            mQueryTask = new QueryTask();
            mQueryTask.execute();
            L.d(TAG, "request http");
        }
    }

    public void setLicence(Listener listener) {
        addListener(listener);
        if (mNativeApi.hasLicence()) {
            listenerOnSuccess();
        } else {
            listenerOnHardIDError();
        }
    }

    private String limitByte(String in, int size) {
        String out;
        if (in != null) {
            if (in.length() > size) {
                out = in.substring(0, size);
            } else if (in.length() < size) {
                String tmp = "00000000000000000000000000000000";
                in = in + tmp;
                out = in.substring(0, size);
            } else{
                out = new String(in);
            }
        } else{
            String tmp = "00000000000000000000000000000000";
            out = tmp.substring(0, size);
        }
        return out;
    }
    private String limitByte(String in) {
        return limitByte(in, 16);
    }

    private StringBuffer getRequestData(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
        for (Map.Entry<String, String> entry : params.entrySet()) {
            stringBuffer.append(entry.getKey())
                    .append("=")
                    .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                    .append("&");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
        return stringBuffer;
    }

    private String streamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            L.e(TAG, "IOException", e);
            e.printStackTrace();
            listenerOnNetError();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                L.e(TAG, "IOException", e);
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private class QueryTask extends AsyncTask<Void, Void, HttpReturnData> {
        byte [] data = null;

        @Override
        protected void onPreExecute() {
            Map<String, String> postParams = new HashMap<String, String>();
            postParams.put(PARAMS_HARD_ID, mNativeApi.aesEncode(getHardId()));
            postParams.put(PARAMS_ANDROID_ID, mNativeApi.aesEncode(getAndroidId()));
            try {
                data = getRequestData(postParams).toString().getBytes();
            } catch (UnsupportedEncodingException e) {
                L.e(TAG, "IOException", e);
                e.printStackTrace();
            }
        }

        @Override
        protected HttpReturnData doInBackground(Void... params) {
            if(data == null){
                listenerOnNetError();
                return null;
            }
            HttpURLConnection connection = null;
            HttpReturnData mReturnData = null;
            try {
                URL url = new URL(URL);
                connection = (HttpURLConnection) url.openConnection();
                // 设置输入和输出流
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod("POST");
                connection.setUseCaches(false);
                connection.setRequestProperty("Charset", "UTF-8");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("Content-Length", String.valueOf(data.length));
                connection.connect();

                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(data);
                outputStream.flush();
                outputStream.close();

                // 获取返回数据
                if (connection.getResponseCode() == 200) {
                    InputStream is = connection.getInputStream();
                    mReturnData = HttpReturnData.toObject(streamToString(is));
                    is.close();
                } else {
                    L.d(TAG, "getResponseCode=" + connection.getResponseCode());
                    listenerOnNetError();
                }
            } catch (MalformedURLException e) {
                L.e(TAG, "MalformedURLException", e);
                e.printStackTrace();
                listenerOnNetError();
            } catch (IOException e) {
                L.e(TAG, "IOException", e);
                e.printStackTrace();
                listenerOnNetError();
            } catch (JSONException e) {
                L.e(TAG, "JSONException", e);
                e.printStackTrace();
                listenerOnNetError();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return mReturnData;
        }

        @Override
        protected void onPostExecute(HttpReturnData result) {
            mHttpReturnData = null;
            if (result != null) {
                if (result.getReturnCode() == HttpReturnData.RESULT_CODE_SUCCESS) {
                    mHttpReturnData = result;
                    if (mNativeApi.aesDecode(
                            getHardId(),
                            getAndroidId(),
                            mHttpReturnData.getAESHardId(),
                            mHttpReturnData.getAESMcrpty())) {
                        DataManager.setAesData(
                                getHardId(),
                                mHttpReturnData.getAESHardId(),
                                mHttpReturnData.getAESMcrpty());
                        listenerOnSuccess();
                    } else {
                        listenerOnHardIDError();
                    }
                } else {
                    mNativeApi.aesDecode("", "", "", "");
                    listenerOnHardIDError();
                }
            }
            resetQuery();
        }
    }

    public interface Listener {
        void onSuccess();

        void onNetError();

        void onHardIDError();
    }

    private List<Listener> mListeners = new ArrayList<>();

    private void addListener(Listener listener) {
        mListeners.add(listener);
    }

    public void removeListener(Listener listener) {
        mListeners.remove(listener);
    }

    public void removeAllListener() {
        mListeners.clear();
    }

    private void listenerOnSuccess() {
        for (Listener listener : mListeners) {
            listener.onSuccess();
        }
    }

    private void listenerOnNetError() {
        for (Listener listener : mListeners) {
            listener.onNetError();
        }
    }

    private void listenerOnHardIDError() {
        for (Listener listener : mListeners) {
            listener.onHardIDError();
        }
    }
}
