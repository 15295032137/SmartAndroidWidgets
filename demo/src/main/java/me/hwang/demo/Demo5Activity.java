package me.hwang.demo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import me.hwang.widgets.SmartPullableLayout;

/**
 * SmartPullableLayout效果演示
 * 与ListView的配合使用
 */
public class Demo5Activity extends AppCompatActivity {

    private static final int ON_REFRESH = 1;
    private static final int ON_LOAD_MORE = 2;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ON_REFRESH:
                    listData.add(0, "这是全新的数据");
                    mLvAdapter.notifyDataSetChanged();
                    mPullableLayout.stopPullBehavior();
                    break;
                case ON_LOAD_MORE:
                    listData.add("这是加载出来的数据");
                    mLvAdapter.notifyDataSetChanged();
                    mPullableLayout.stopPullBehavior();
                    break;
            }
        }
    };

    private SmartPullableLayout mPullableLayout;
    private ListView lvContent;
    private ArrayList<String> listData;
    private ArrayAdapter<String> mLvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_5);

        lvContent = (ListView) findViewById(R.id.lv_content);
        listData = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            listData.add("这是第" + i + "条数据");
        }
        mLvAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        lvContent.setAdapter(mLvAdapter);

        mPullableLayout = (SmartPullableLayout) findViewById(R.id.layout_pullable);
        mPullableLayout.setOnPullListener(new SmartPullableLayout.OnPullListener() {
            @Override
            public void onPullDown() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(ON_REFRESH);
                    }
                }).start();
            }

            @Override
            public void onPullUp() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(ON_LOAD_MORE);
                    }
                }).start();
            }

        });
    }
}
