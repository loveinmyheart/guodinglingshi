package com.wjh.guoding.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wjh.guoding.R;
import com.wjh.guoding.activity.Bean.SpecialBean;
import com.wjh.guoding.activity.Utils.APIManager;
import com.wjh.guoding.activity.Utils.Constant;
import com.wjh.guoding.activity.Utils.IHome;
import com.wjh.guoding.activity.Utils.RefreshUtils;
import com.wjh.guoding.activity.activity.WebActivity;
import com.wjh.guoding.activity.adapter.SpecialAdapter;
import com.wjh.guoding.activity.widget.HeadView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2017/2/17.
 */
public class SpecialFragment extends Fragment {

    private ListView lv;
    List<SpecialBean.ResultEntity.SpecialListEntity> data=new ArrayList<>();
    private SpecialAdapter<SpecialBean.ResultEntity.SpecialListEntity> adapter;
    private PtrClassicFrameLayout refresh;
    private int page=1;
    private boolean isBottom=false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new SpecialAdapter<SpecialBean.ResultEntity.SpecialListEntity>(getActivity(),data,R.layout.special_adapter_item_layout) {
            @Override
            protected void bindData(ViewHolder viewHolder, SpecialBean.ResultEntity.SpecialListEntity specialListEntity, int position) {
                //绑定数据
                ImageView iv= (ImageView) viewHolder.getViewById(R.id.special_image);
                Glide.with(getActivity()).load(specialListEntity.getImage().trim()).placeholder(R.drawable.placeholder).into(iv);
                TextView title= (TextView) viewHolder.getViewById(R.id.special_title);
                title.setText(specialListEntity.getSpecial_name());
                TextView viewNum= (TextView) viewHolder.getViewById(R.id.special_view);
                viewNum.setText(specialListEntity.getLike_num());
            }
        };
        //网络获取数据
        getData();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_special_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化ListView
        initList(view);
        //设置刷新
        initRefresh(view);
    }

    private void initRefresh(View view) {
        refresh = (PtrClassicFrameLayout) view.findViewById(R.id.refresh);
        //设置刷新头部属性
        RefreshUtils.setRefresh(getActivity(),refresh);
        refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                page=1;
                getData();
            }
        });
    }

    private void initList(View view) {
        lv = (ListView) view.findViewById(R.id.list_view);
        //添加头部
        HeadView headView=new HeadView(getActivity());
        lv.addHeaderView(headView);
        lv.setAdapter(adapter);
        //设置监听，上拉加载
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //判断是否到底部了
                if (isBottom&&scrollState==SCROLL_STATE_IDLE){
                    getData();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem+visibleItemCount==totalItemCount){
                    isBottom=true;
                }else{
                    isBottom=false;
                }
            }
        });
        //设置listview的点击事件
        setLvClick();
    }

    private void setLvClick() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击跳转到web界面
                Intent intent=new Intent(getActivity(), WebActivity.class);
                intent.putExtra(Constant.WEBIMG_TAG,data.get(position-1).getImage());
                intent.putExtra(Constant.WEBURL_TAG,data.get(position-1).getWeb_url());
                intent.putExtra(Constant.WEBTITLE_TAG,data.get(position-1).getSpecial_name());
                //创建共享元素
                ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(),
                        view,
                        Constant.WEBSHARE_TAG
                );
                //启动
                ActivityCompat.startActivity(getActivity(),intent,optionsCompat.toBundle());
            }
        });
    }

    private void getData() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(APIManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IHome iHome = retrofit.create(IHome.class);
        Call<SpecialBean> call = iHome.getSpecialData(page);
        call.enqueue(new Callback<SpecialBean>() {
            @Override
            public void onResponse(Call<SpecialBean> call, Response<SpecialBean> response) {
                SpecialBean bean = response.body();
                page = bean.getResult().getNext();
                if (refresh.isRefreshing()){
                    data.clear();
                }
                data.addAll(bean.getResult().getSpecial_list());
                Log.d("qwer", "onResponse: "+data.get(0).getImage());
                //提醒数据更新
                adapter.notifyDataSetChanged();
                //刷新完成
                refresh.refreshComplete();
            }

            @Override
            public void onFailure(Call<SpecialBean> call, Throwable t) {
                refresh.refreshComplete();
            }
        });
    }
}
