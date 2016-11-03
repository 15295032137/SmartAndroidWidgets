package me.hwang.demo;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import me.hwang.widgets.SmartPullableLayout;

/**
 * SmartPullableLayout效果演示
 * 与ScrollView的配合使用
 */
public class Demo4Activity extends AppCompatActivity {

    private SmartPullableLayout mPullableLayout;
    private TextView tvContent;

    private static final int ON_REFRESH = 1;
    private static final int ON_LOAD_MORE = 2;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case ON_REFRESH:
                    tvContent.setTextColor(Color.RED);
                    mPullableLayout.stopPullBehavior();
                    break;
                case ON_LOAD_MORE:
                    tvContent.setText(tvContent.getText()+"\n"+getMore());
                    mPullableLayout.stopPullBehavior();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_4);

        mPullableLayout = (SmartPullableLayout) findViewById(R.id.layout_pullable);
        tvContent = (TextView) findViewById(R.id.tv_content);

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

    private String getMore(){
        StringBuilder more = new StringBuilder();

        for (int i = 21;i <=30 ;i++){
            more.append(i).append("\n");
        }

        return more.toString();
    }
}
