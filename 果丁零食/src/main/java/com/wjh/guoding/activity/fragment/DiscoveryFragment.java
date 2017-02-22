package com.wjh.guoding.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wjh.guoding.R;
import com.wjh.guoding.activity.Bean.DiscoverBean;
import com.wjh.guoding.activity.Utils.APIManager;
import com.wjh.guoding.activity.Utils.Constant;
import com.wjh.guoding.activity.Utils.IHome;
import com.wjh.guoding.activity.Utils.RefreshUtils;
import com.wjh.guoding.activity.activity.DiscoverListActivity;
import com.wjh.guoding.activity.adapter.DiscoverAdapter;

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
public class DiscoveryFragment extends Fragment {

    private RecyclerView rv;
    private PtrClassicFrameLayout refresh;
    List<DiscoverBean.ResultEntity.TagListEntity> data=new ArrayList<>();
    private DiscoverAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化adapter
        adapter = new DiscoverAdapter(getContext(), data, new DiscoverAdapter.OnItemClickListenner() {
            @Override
            public void onItemClick(int position) {
                //item点击跳转到DiscoverListActivity
                Intent intent=new Intent(getActivity(), DiscoverListActivity.class);
                intent.putExtra(Constant.TAG_ID,data.get(position).getTag_id());
                intent.putExtra(Constant.DISCOVER_TITLE,data.get(position).getTag_name());
                startActivity(intent);
            }
        });
        //网络请求数据
        getData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //找到控件
        initViews(view);
        //在oncreate里初始化adapter
        //给Recycleview设置相关属性
        initRv();

        //下拉刷新
        pullToRefresh();
    }

    private void pullToRefresh() {
        refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getData();
            }
        });
    }

    private void getData() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(APIManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IHome iHome = retrofit.create(IHome.class);
        Call<DiscoverBean> call = iHome.getDiscoverData(1);
        call.enqueue(new Callback<DiscoverBean>() {
            @Override
            public void onResponse(Call<DiscoverBean> call, Response<DiscoverBean> response) {
                DiscoverBean body = response.body();
                if (refresh.isRefreshing()){
                    data.clear();
                }
                data.addAll(body.getResult().getTag_list());
                adapter.notifyDataSetChanged();
                refresh.refreshComplete();
            }

            @Override
            public void onFailure(Call<DiscoverBean> call, Throwable t) {
                refresh.refreshComplete();
            }
        });
    }

    private void initRv() {
        GridLayoutManager glm=new GridLayoutManager(getContext(),2);
        glm.setOrientation(GridLayoutManager.VERTICAL);
        rv.setLayoutManager(glm);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
    }

    private void initViews(View view) {
        rv = (RecyclerView) view.findViewById(R.id.discover_rv);
        refresh = (PtrClassicFrameLayout) view.findViewById(R.id.discover_refresh);
        RefreshUtils.setRefresh(getContext(),refresh);
    }
}
