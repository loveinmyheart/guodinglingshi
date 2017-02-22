package com.wjh.guoding.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wjh.guoding.R;
import com.wjh.guoding.activity.Bean.SingleFirstBean;
import com.wjh.guoding.activity.Utils.APIManager;
import com.wjh.guoding.activity.Utils.Constant;
import com.wjh.guoding.activity.Utils.IHome;
import com.wjh.guoding.activity.Utils.RefreshUtils;
import com.wjh.guoding.activity.activity.SingleFirstDetailAcitivity;
import com.wjh.guoding.activity.adapter.SingleFirstAdapter;

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
public class SingleFirstFragment extends Fragment {

    private RecyclerView rv;
    List<SingleFirstBean.ResultEntity.GoodsListEntity> data=new ArrayList<>();
    private SingleFirstAdapter adapter;
    private int page=1;
    private GridLayoutManager glm;
    private PtrClassicFrameLayout refresh;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new SingleFirstAdapter(getActivity(), data, new SingleFirstAdapter.OnItemClickListenner() {
            @Override
            public void onItemClick(View v, int position) {
                //跳转到优品详情界面
//                点击启动一个新的Fragment,传值
                Intent intent=new Intent(getActivity(),SingleFirstDetailAcitivity.class);
                intent.putExtra(Constant.GOODS_ID,data.get(position).getGoods_id());
                Log.e("qwer", "onItemClick: "+ data.get(position).getGoods_id());
                //创建共享元素
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(),
                        v,
                        "detail"
                );
                //启动
                ActivityCompat.startActivity(
                        getActivity(),
                        intent,
                        optionsCompat.toBundle()
                );
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_first_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //找到控件
        initViews(view);
        //初始化RecycleView控件
        initRv(view);
        //获取网络数据
        getData();
        //上拉加载
        upDownLoad();
        //下拉刷新
        pullToRefresh();
    }

    private void initViews(View view) {
        rv = (RecyclerView) view.findViewById(R.id.single_first_rv);

        refresh = (PtrClassicFrameLayout) view.findViewById(R.id.single_first_refresh);
        RefreshUtils.setRefresh(getActivity(),refresh);

    }

    private void pullToRefresh() {
        refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                page=1;
                getData();
            }
        });
    }

    private void upDownLoad() {
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE&&glm.findLastCompletelyVisibleItemPosition()+1==adapter.getItemCount()){
                    page++;
                    getData();
                }
            }
        });
    }

    private void getData() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(APIManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IHome iHome = retrofit.create(IHome.class);
        Call<SingleFirstBean> call = iHome.getSingleFirstData(page);
        call.enqueue(new Callback<SingleFirstBean>() {
            @Override
            public void onResponse(Call<SingleFirstBean> call, Response<SingleFirstBean> response) {
                SingleFirstBean bean = response.body();
                if (refresh.isRefreshing()){
                    data.clear();
                }
                data.addAll(bean.getResult().getGoods_list());
                adapter.notifyDataSetChanged();
                refresh.refreshComplete();
            }

            @Override
            public void onFailure(Call<SingleFirstBean> call, Throwable t) {
                refresh.refreshComplete();
            }
        });
    }

    private void initRv(View view) {

        glm = new GridLayoutManager(getActivity(),2);
        glm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(glm);
        rv.setAdapter(adapter);

    }



}
