package com.wjh.guoding.activity.activity;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.wjh.guoding.R;
import com.wjh.guoding.activity.fragment.DiscoveryFragment;
import com.wjh.guoding.activity.fragment.MineFragment;
import com.wjh.guoding.activity.fragment.SingleFragment;
import com.wjh.guoding.activity.fragment.SpecialFragment;

public class MainActivity extends AppCompatActivity {
//123456
    String titles[]={
            "专题",
            "优品",
            "发现",
            "我"
    };

    int[]imgs={
            R.drawable.special_seletor,
            R.drawable.single_seletor,
            R.drawable.discovery_seletor,
            R.drawable.mine_seletor,
    };
    Class[] fragments={
            SpecialFragment.class,
            SingleFragment.class,
            DiscoveryFragment.class,
            MineFragment.class,
    };

    private FragmentTabHost tabHost;
    LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initViews();
        //初始化TabHost
        initTabs();
    }

    private void initTabs() {
        for (int i=0;i<titles.length;i++){
            //给每个fragment设置不同的标签
            TabHost.TabSpec tab=tabHost.newTabSpec(""+i);
            //设置视图
            tab.setIndicator(setTabView(i));
            //添加到tabhost
            tabHost.addTab(tab,fragments[i],null);
            //去掉每个tab分割线
            tabHost.getTabWidget().setDividerDrawable(new ColorDrawable(0x00000000));
        }
    }

    private View setTabView(int i) {
        if (inflater==null){
            inflater=LayoutInflater.from(this);
        }
        View view = inflater.inflate(R.layout.activity_home_layout, null);
        ImageView iv= (ImageView) view.findViewById(R.id.activity_home_iv);
        iv.setImageResource(imgs[i]);
        TextView tv= (TextView) view.findViewById(R.id.activity_home_tv);
        tv.setText(titles[i]);
        return view;
    }

    private void initViews() {
        tabHost = (FragmentTabHost) findViewById(R.id.tabHost);
        //设置上下文，内容区域
        tabHost.setup(this,getSupportFragmentManager(),R.id.activity_framelayout);
    }
}
