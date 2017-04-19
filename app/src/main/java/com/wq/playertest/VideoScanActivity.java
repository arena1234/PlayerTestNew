package com.wq.playertest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.vr.player.Utils;
import com.wq.playertest.ui.RecyclerView.DividerGridDecoration;
import com.wq.playertest.ui.RecyclerView.Item;
import com.wq.playertest.ui.RecyclerView.VideoListAdapter;
import com.wq.playertest.ui.RecyclerView.VideoListViewHolder;
import com.wq.playertest.ui.imagemanager.ImageManager;

import java.io.File;
import java.util.ArrayList;

public class VideoScanActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private VideoListAdapter mAdapter;
    private ArrayList<Item> mItems;
    private String mVideoPath = Utils.ROOT_PATH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_scan);

        ImageManager.init(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.video_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setBackgroundColor(Color.TRANSPARENT);
        mRecyclerView.addItemDecoration(new DividerGridDecoration(this));

        getVideoFile();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mAdapter = new VideoListAdapter(mItems, dm.widthPixels/2);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new VideoListViewHolder.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra(FILE_NAME, mItems.get(position).getURL());
                intent.setClass(VideoScanActivity.this, NewPlayerActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.btn_input_net).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });
        findViewById(R.id.btn_use_bitmap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                Intent intent = new Intent();
                intent.putExtra(FILE_NAME, "use_bitmap");
                intent.setClass(VideoScanActivity.this, NewPlayerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showInputDialog(){
        final EditText et = new EditText(this);
        //et.setText("http://120.76.122.176/future-looker/VideoTest_1.mp4");
        et.setText("http://vod.cntv.lxdns.com/flash/mp4video50/TMS/2016/04/19/f1db9354900946a19a7fefc3bf040593_h264200000nero_aac16.mp4");
        new AlertDialog.Builder(this).setTitle(R.string.input_video_uri)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String input = et.getText().toString();
                        if (input.equals("")) {
                            Toast.makeText(getApplicationContext(), "搜索内容不能为空！", Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent();
                            intent.putExtra(FILE_NAME, input);
                            intent.setClass(VideoScanActivity.this, NewPlayerActivity.class);
                            startActivity(intent);
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private void getVideoFile(){
        mItems = new ArrayList<>();
        File folder = new File(mVideoPath);
        if(folder.exists() && folder.isDirectory()) {
            for(File file:folder.listFiles()){
                if(file.getPath().endsWith("mp4") || file.getPath().endsWith("MP4") ||
                        file.getPath().endsWith("avi") || file.getPath().endsWith("AVI") ||
                        file.getPath().endsWith("mkv") || file.getPath().endsWith("MKV")){
                    Item item = new Item(file.getPath(), file.getName());
                    mItems.add(item);
                }
            }
        }
    }
}
