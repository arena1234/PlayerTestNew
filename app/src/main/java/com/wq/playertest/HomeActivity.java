package com.wq.playertest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.vr.player.security.Licence;

public class HomeActivity extends BaseActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_VIDEO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.btn_video_list).setOnClickListener(this);
        findViewById(R.id.btn_video_list_other).setOnClickListener(this);
        findViewById(R.id.btn_settings).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_video_list:
                startActivity(HomeActivity.this, VideoScanActivity.class);
                break;
            case R.id.btn_video_list_other:
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                Intent wrapperIntent = Intent.createChooser(intent, null);
                startActivityForResult(wrapperIntent, REQUEST_CODE_VIDEO);
                break;
            case R.id.btn_settings:
                //startActivity(HomeActivity.this, SettingsActivity.class);
                showDialog();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_VIDEO) {
            if (data != null) {
                Intent intent = new Intent();
                intent.putExtra(FILE_NAME, data.getData().toString());
                //intent.setClass(HomeActivity.this, VRPlayerActivity.class);
                startActivity(intent);
            }
        }
    }

    private void showDialog(){
        final EditText et = new EditText(this);
        et.setInputType(InputType.TYPE_CLASS_PHONE);
        et.setText("6666661111111111");

        new AlertDialog.Builder(this).setTitle("请输入Licence")
            .setIcon(android.R.drawable.ic_dialog_info)
            .setView(et)
            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    String input = et.getText().toString();

                    Licence licence = new Licence(HomeActivity.this);
                    licence.setLicence(input, new Licence.Listener() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(
                                    HomeActivity.this,
                                    "Licence验证成功!",
                                    Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNetError() {
                            HomeActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(
                                            HomeActivity.this,
                                            "您的网络故障!",
                                            Toast.LENGTH_LONG).show();
                                }
                            });
                        }

                        @Override
                        public void onHardIDError() {
                            Toast.makeText(
                                    HomeActivity.this,
                                    "您输入的Licence无效!",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            })
            .setNegativeButton("取消", null)
            .show();
    }
}
