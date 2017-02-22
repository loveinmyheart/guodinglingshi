package com.wjh.guoding.activity.activity;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wjh.guoding.R;
import com.wjh.guoding.activity.Utils.Constant;

/**
 * Created by lenovo on 2017/2/20.
 */
public class WebActivity extends AppCompatActivity {

    private WebView webView;
    private ImageView iv;
    private Toolbar toolbar;
    private NestedScrollView nsv;
    int len = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_web_layout);
        //找到控件
        initViews();

        //获取标题
        String title = getIntent().getStringExtra(Constant.WEBTITLE_TAG);

        //设置scrollview滚动监听
        setScrollListenner();
        //设置沉浸模式
        //设置状态栏颜色(在5.0以后才有的，所以需要判断版本)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        //设置toolbar
        setToolBar(title);
        //设置共享参数
        ViewCompat.setTransitionName(iv, Constant.WEBSHARE_TAG);
        //获取图片url
        String imgurl = getIntent().getStringExtra(Constant.WEBIMG_TAG);
        Glide.with(this).load(imgurl.trim()).placeholder(R.drawable.placeholder).into(iv);
        //获取weburl
        String weburl = getIntent().getStringExtra(Constant.WEBURL_TAG);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(weburl);
    }

    private void setScrollListenner() {
        nsv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int ivH = iv.getMeasuredHeight();
                int toolbarH = toolbar.getMeasuredHeight();
                len = scrollY;
                if (len >= (ivH - toolbarH)) {
                    len = (ivH - toolbarH);
                }
                float alpha = len * 1.0f / (ivH - toolbarH);
                toolbar.setAlpha(alpha);
            }
        });
    }

    private void setToolBar(String title) {
        //设置 toolbar
        toolbar.setTitleTextColor(Color.parseColor("#FFF6F6F6"));
        toolbar.setPadding(0,getStatusHeight(this),0,0);

        ViewGroup.LayoutParams params = toolbar.getLayoutParams();
        params.height+=getStatusHeight(this);
        toolbar.setLayoutParams(params);

        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        //设置显示导航箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置导航箭头可点击
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void initViews() {
        iv = (ImageView) findViewById(R.id.special_web_iv);
        webView = (WebView) findViewById(R.id.special_webView);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        nsv = (NestedScrollView) findViewById(R.id.web_scroll_view);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }


}
