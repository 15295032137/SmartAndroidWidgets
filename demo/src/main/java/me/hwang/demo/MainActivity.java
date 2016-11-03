package me.hwang.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button showSmartLoadingLayoutDefault;
    private Button showSmartLoadingLayoutCustom;
    private Button showPullableWithCommonView;
    private Button showPullableWithScrollView;
    private Button showPullableWithListView;
    private Button showPullableWithRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showSmartLoadingLayoutDefault = (Button) findViewById(R.id.btn_show_loading_layout_default);
        showSmartLoadingLayoutDefault.setOnClickListener(this);
        showSmartLoadingLayoutCustom = (Button) findViewById(R.id.btn_show_loading_layout_custom);
        showSmartLoadingLayoutCustom.setOnClickListener(this);
        showPullableWithCommonView = (Button) findViewById(R.id.btn_show_pullable_with_common_view);
        showPullableWithCommonView.setOnClickListener(this);
        showPullableWithScrollView = (Button) findViewById(R.id.btn_show_pullable_with_scroll_view);
        showPullableWithScrollView.setOnClickListener(this);
        showPullableWithListView = (Button) findViewById(R.id.btn_show_pullable_with_list_view);
        showPullableWithListView.setOnClickListener(this);
        showPullableWithRecyclerView = (Button) findViewById(R.id.btn_show_pullable_with_recycler_view);
        showPullableWithRecyclerView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_loading_layout_default:
                startActivity(Demo1Activity.class);
                break;
            case R.id.btn_show_loading_layout_custom:
                startActivity(Demo2Activity.class);
                break;
            case R.id.btn_show_pullable_with_common_view:
                startActivity(Demo3Activity.class);
                break;
            case R.id.btn_show_pullable_with_scroll_view:
                startActivity(Demo4Activity.class);
                break;
            case R.id.btn_show_pullable_with_list_view:
                startActivity(Demo5Activity.class);
                break;
            case R.id.btn_show_pullable_with_recycler_view:
                startActivity(Demo6Activity.class);
                break;
        }
    }

    private void startActivity(Class<?> clazz) {
        Intent intent = new Intent(MainActivity.this, clazz);
        startActivity(intent);
    }
}
