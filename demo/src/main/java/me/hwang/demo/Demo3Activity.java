package me.hwang.demo;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import me.hwang.widgets.SmartPullableLayout;

/**
 * SmartPullableLayout效果演示
 * 与通常的view的配合使用
 */
public class Demo3Activity extends AppCompatActivity {

    private SmartPullableLayout mPullableLayout;
    private ImageView ivContent;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ivContent.setBackgroundColor(Color.RED);
            // 当下拉事件完成，记得调用stopPullBehavior停止下拉行为
            mPullableLayout.stopPullBehavior();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_3);

        mPullableLayout = (SmartPullableLayout) findViewById(R.id.layout_pullable);
        ivContent = (ImageView) findViewById(R.id.iv_content);

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
                        mHandler.sendEmptyMessage(0);
                    }
                }).start();
            }

            @Override
            public void onPullUp() {
                // nope
            }
        });
    }
}
