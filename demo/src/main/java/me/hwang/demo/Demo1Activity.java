package me.hwang.demo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import me.hwang.widgets.DefaultLoadingLayout;
import me.hwang.widgets.SmartLoadingLayout;

/**
 * SmartLoadingLayout
 * DefaultLoadingLayout的使用效果演示
 */
public class Demo1Activity extends AppCompatActivity {

    private static final int THREAD_SLEEP_MILLIS = 2500;
    private static final int SHOW_LOADING = 1;
    private static final int SHOW_EMPTY = 2;
    private static final int SHOW_ERROR = 3;
    private static final int SHOW_CONTENT = 4;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_LOADING:
                    mLoadingLayout.onLoading();
                    break;

                case SHOW_EMPTY:
                    mLoadingLayout.onEmpty();
                    break;

                case SHOW_ERROR:
                    mLoadingLayout.onError();
                    break;

                case SHOW_CONTENT:
                    mLoadingLayout.onDone();
                    break;
            }
        }
    };

    private ImageView ivContent;
    private DefaultLoadingLayout mLoadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_1);

        ivContent = (ImageView) findViewById(R.id.iv_content);
        /*
         * 调用SmartLoadingLayout的静态方法createDefaultLayout;
         * 将宿主Activity以及作为ContentView的View对象作为参数传入；
         * 就得到了一个默认样式的切换Loading状态的Layout对象。
         */
        mLoadingLayout = SmartLoadingLayout.createDefaultLayout(this, ivContent);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 首先显示Loading状态
                mHandler.sendEmptyMessage(SHOW_LOADING);
                // 两秒后显示Empty状态
                threadSleep(THREAD_SLEEP_MILLIS);
                mHandler.sendEmptyMessage(SHOW_EMPTY);
                // 两秒后再次显示Loading状态
                threadSleep(THREAD_SLEEP_MILLIS);
                mHandler.sendEmptyMessage(SHOW_LOADING);
                // 两秒后显示Error状态
                threadSleep(THREAD_SLEEP_MILLIS);
                mHandler.sendEmptyMessage(SHOW_ERROR);
            }
        }).start();

        // 为Error界面的error button设置点击监听事件
        mLoadingLayout.setErrorButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 再次进入Loading状态
                        mHandler.sendEmptyMessage(SHOW_LOADING);
                        // 两秒后显示Content
                        threadSleep(THREAD_SLEEP_MILLIS);
                        mHandler.sendEmptyMessage(SHOW_CONTENT);
                    }
                }).start();
            }
        });
    }

    private void threadSleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
