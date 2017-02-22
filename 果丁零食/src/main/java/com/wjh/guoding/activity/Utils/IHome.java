package com.wjh.guoding.activity.Utils;

import com.wjh.guoding.activity.Bean.BroadcastBean;
import com.wjh.guoding.activity.Bean.DiscoverBean;
import com.wjh.guoding.activity.Bean.DiscoverSecondBean;
import com.wjh.guoding.activity.Bean.GoodsBean;
import com.wjh.guoding.activity.Bean.SingleFirstBean;
import com.wjh.guoding.activity.Bean.SingleFirstDetailBean;
import com.wjh.guoding.activity.Bean.SingleSecondBean;
import com.wjh.guoding.activity.Bean.SpecialBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by lenovo on 2017/2/18.
 */
public interface IHome {

    //获取专题界面轮播图数据
    @POST(APIManager.BROADCAST_URL)
    Call<BroadcastBean> getBroadcastData();

    //获取专题界面数据
    @FormUrlEncoded
    @POST(APIManager.SPECIAL_URL)
    Call<SpecialBean> getSpecialData(@Field("p") int p);

    //获取精选优品界面数据
    @FormUrlEncoded
    @POST(APIManager.SINGLE_FIRST_URL)
    Call<SingleFirstBean> getSingleFirstData(@Field("p") int p);

    //获取精选优品详情数据
    @FormUrlEncoded
    @POST(APIManager.SINGLE_DETAIL_URL)
    Call<SingleFirstDetailBean> getSingleFirstDetailData(@Field("goods_id") String goods_id);

    //获取每日折扣界面数据
    @FormUrlEncoded
    @POST(APIManager.DISCOUNT_URL)
    Call<SingleSecondBean> getSingleSecondData(@Field("p") int p);

    //获取每日折扣商品详情
    @FormUrlEncoded
    @POST(APIManager.GOODS_URL)
    Call<GoodsBean> getGoodsData(@Field("goods_id") String goods_id);

    //发现界面首页数据
    @FormUrlEncoded
    @POST(APIManager.DISCOVER_URL)
    Call<DiscoverBean> getDiscoverData(@Field("p") int p);

    //发现界面二级界面(注意 此处p是String类型 明天获取next数据也是String类型  用equals)
    @FormUrlEncoded
    @POST(APIManager.DISCOVER_SECOND_URL)
    Call<DiscoverSecondBean> getDiscoverSecondData(@Field("tag_id") String tag_id, @Field("p") String p);
}
