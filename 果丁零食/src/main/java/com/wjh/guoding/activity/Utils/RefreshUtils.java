package com.wjh.guoding.activity.Utils;

import android.content.Context;
import com.wjh.guoding.R;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Created by Administrator on 2017/2/20.
 */
public class RefreshUtils {

    public static void setRefresh(Context context, final PtrClassicFrameLayout refresh) {
        //设置控件是否向下移动，true为不移动
        refresh.setPinContent(true);
        //设置header
        MaterialHeader header = new MaterialHeader(context);
        //设置头部颜色
        int[] colors = context.getResources().getIntArray(R.array.refresh_colors);
        header.setColorSchemeColors(colors);
        //设置头部的布局属性
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1,-2));
        header.setPadding(0,50,0,0);
        header.setPtrFrameLayout(refresh);
        //添加头部到刷新控件
        refresh.setLoadingMinTime(1000);
        refresh.setDurationToCloseHeader(1500);
        refresh.setHeaderView(header);
        refresh.addPtrUIHandler(header);
        refresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh.autoRefresh(false);
            }
        },100);
    }
}

