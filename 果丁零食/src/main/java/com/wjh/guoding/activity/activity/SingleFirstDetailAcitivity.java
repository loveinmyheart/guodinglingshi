package com.wjh.guoding.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wjh.guoding.R;
import com.wjh.guoding.activity.Bean.SingleFirstDetailBean;
import com.wjh.guoding.activity.Utils.APIManager;
import com.wjh.guoding.activity.Utils.Constant;
import com.wjh.guoding.activity.Utils.IHome;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2017/2/20.
 */
public class SingleFirstDetailAcitivity extends AppCompatActivity {

    private ImageView iv;
    private TextView titleView;
    private TextView contentView;
    private TextView priceView;
    private String goods_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_first_item_details_layout);
        //找到控件
        initViews();
        //设置共享参数
        ViewCompat.setTransitionName(iv, "detail");
        //获得上个界面传过来的post参数
        Intent intent = getIntent();
        goods_id = intent.getStringExtra(Constant.GOODS_ID);
        //网络请求数据
        getData();

    }


    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IHome iHome = retrofit.create(IHome.class);
        Call<SingleFirstDetailBean> call = iHome.getSingleFirstDetailData(goods_id);
        call.enqueue(new Callback<SingleFirstDetailBean>() {
            @Override
            public void onResponse(Call<SingleFirstDetailBean> call, Response<SingleFirstDetailBean> response) {
                SingleFirstDetailBean bean = response.body();
                Log.e("qwer", "onResponse: "+bean.toString() );
                SingleFirstDetailBean.ResultEntity.DetailEntity entity = bean.getResult().getDetail();
                Log.e("qwer", "onResponse: "+call.request().url().toString());
                //设置数据
                Glide.with(SingleFirstDetailAcitivity.this).load(entity.getGoods_image()).into(iv);
                titleView.setText(entity.getGoods_name());
                contentView.setText(entity.getGoods_brief());
                priceView.setText(entity.getShop_price());
            }

            @Override
            public void onFailure(Call<SingleFirstDetailBean> call, Throwable t) {

            }
        });

    }

    private void initViews() {

        iv = (ImageView) findViewById(R.id.detail_iv);
        titleView = (TextView) findViewById(R.id.detail_title);
        contentView = (TextView) findViewById(R.id.detail_content);
        priceView = (TextView) findViewById(R.id.detail_price);
    }
}
