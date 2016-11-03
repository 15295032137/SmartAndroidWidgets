package me.hwang.demo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import me.hwang.widgets.SmartPullableLayout;

/**
 * SmartPullableLayout效果演示
 * 与RecyclerView的配合使用
 */
public class Demo6Activity extends AppCompatActivity {
    private static final int ON_REFRESH = 1;
    private static final int ON_LOAD_MORE = 2;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ON_REFRESH:
                    recyclerData.add(0, "这是全新的数据");
                    mRvAdapter.notifyDataSetChanged();
                    mPullableLayout.stopPullBehavior();
                    break;
                case ON_LOAD_MORE:
                    recyclerData.add("这是加载出来的数据");
                    mRvAdapter.notifyDataSetChanged();
                    mPullableLayout.stopPullBehavior();
                    break;
            }
        }
    };

    private SmartPullableLayout mPullableLayout;
    private RecyclerView rvContent;
    private SimpleRecyclerAdapter mRvAdapter;
    private ArrayList<String> recyclerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_6);

        rvContent = (RecyclerView) findViewById(R.id.rv_content);
        rvContent.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        recyclerData = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            recyclerData.add("这是第" + i + "条数据");
        }

        mRvAdapter = new SimpleRecyclerAdapter(recyclerData);
        rvContent.setAdapter(mRvAdapter);

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
