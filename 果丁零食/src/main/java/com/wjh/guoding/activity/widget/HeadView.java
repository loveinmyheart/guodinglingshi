package com.wjh.guoding.activity.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wjh.guoding.R;
import com.wjh.guoding.activity.Bean.BroadcastBean;
import com.wjh.guoding.activity.Utils.APIManager;
import com.wjh.guoding.activity.Utils.IHome;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2017/2/18.
 */
public class HeadView extends FrameLayout {
    Banner banner;
    public HeadView(Context context) {
        super(context);
        init();
    }
    public HeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init() {
        //加载布局
        View view = LayoutInflater.from(getContext()).inflate(R.layout.headview_layout, this, true);
        banner= (Banner) view.findViewById(R.id.banner);
        //设置相关属性
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR
        );
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
//        banner.setIndicatorGravity(BannerConfig.CENTER);
        //加载数据
        getData();
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
//            eg：
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);
        }
    }

    private void getData(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(APIManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IHome iHome = retrofit.create(IHome.class);
        Call<BroadcastBean> call = iHome.getBroadcastData();
        call.enqueue(new Callback<BroadcastBean>() {
            @Override
            public void onResponse(Call<BroadcastBean> call, Response<BroadcastBean> response) {
                //图片url集合
                List<String> images=new ArrayList<String>();
                //标题集合
                List<String> titles=new ArrayList<String>();

                BroadcastBean bean = response.body();
                List<BroadcastBean.ResultEntity.BannerEntity> data = bean.getResult().getBanner();
                for (BroadcastBean.ResultEntity.BannerEntity entity:data){
                    images.add(entity.getPic());
                    titles.add("");
                }
                //将数据设置给banner
                banner.setImages(images);
                banner.setBannerTitles(titles);
                banner.start();
            }

            @Override
            public void onFailure(Call<BroadcastBean> call, Throwable t) {

            }
        });
    }
}
