package me.hwang.demo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import me.hwang.widgets.CustomLoadingLayout;
import me.hwang.widgets.DefaultLoadingLayout;
import me.hwang.widgets.SmartLoadingLayout;

/**
 * SmartLoadingLayout
 * CustomLoadingLayout的使用效果演示
 */
public class Demo2Activity extends AppCompatActivity {

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
    private CustomLoadingLayout mLoadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_2);

        ivContent = (ImageView) findViewById(R.id.iv_content);
        /*
         * 调用SmartLoadingLayout的静态方法createCustomLayout
         * 将宿主Activity作为参数传入；随后就可以通过：
         * @setContentView 设置你的内容视图
         * @setLoadingView 设置你的加载视图
         * @setEmptyView 设置你的空内容视图
         * @setErrorView 设置你的加载错误视图
         */
        mLoadingLayout = SmartLoadingLayout.createCustomLayout(this);
        mLoadingLayout.setContentView(R.id.iv_content);
        mLoadingLayout.setLoadingView(R.id.custom_loading_view);
        mLoadingLayout.setEmptyView(R.id.custom_empty_view);
        mLoadingLayout.setErrorView(R.id.custom_error_view);

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
                // 两秒后显示Loading状态
                threadSleep(THREAD_SLEEP_MILLIS);
                mHandler.sendEmptyMessage(SHOW_LOADING);
                // 两秒后显示Content
                threadSleep(THREAD_SLEEP_MILLIS);
                mHandler.sendEmptyMessage(SHOW_CONTENT);
            }
        }).start();
    }

    private void threadSleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
