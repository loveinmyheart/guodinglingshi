package com.wjh.guoding.activity.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wjh.guoding.R;
import com.wjh.guoding.activity.Bean.GoodsBean;
import com.wjh.guoding.activity.Bean.SingleSecondBean;
import com.wjh.guoding.activity.Utils.APIManager;
import com.wjh.guoding.activity.Utils.IHome;
import com.wjh.guoding.activity.Utils.RefreshUtils;
import com.wjh.guoding.activity.adapter.SingleSecondAdapter;

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
public class SingleSecondFragment extends Fragment {

    private RecyclerView rv;
    int page=1;
    int next=0;
    private SingleSecondAdapter adapter;
    List<SingleSecondBean.ResultEntity.GoodsListEntity> data=new ArrayList();
    private PtrClassicFrameLayout refresh;
    private LinearLayoutManager llm;
    private GoodsBean body;
    private PopupWindow pop;
    private LinearLayout linear;
    private WindowManager.LayoutParams params;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new SingleSecondAdapter(getContext(), data, new SingleSecondAdapter.OnItemClickListenner() {
            @Override
            public void onItemClick(int position) {
                //获得post参数goods_id
                String goods_id = data.get(position).getGoods_id();
                //请求网络获取商品数据
                getShopData(goods_id);
                //弹出商品信息窗口
                upToPopupWindows();
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_second_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //找到控件
        initViews(view);
        //初始化RecycleView
        initRv();
        //加载数据
        getData();
        //下拉刷新
        pullToRefresh();
        //上拉加载
        upDownToLoad();
    }

    private void upDownToLoad() {
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE&&llm.findLastCompletelyVisibleItemPosition()+1==adapter.getItemCount()){
                    if (next!=0){
                        page=next;
                        getData();
                    }

                }
            }
        });
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

    private void getData() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(APIManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IHome iHome = retrofit.create(IHome.class);
        Call<SingleSecondBean> call = iHome.getSingleSecondData(page);
        call.enqueue(new Callback<SingleSecondBean>() {
            @Override
            public void onResponse(Call<SingleSecondBean> call, Response<SingleSecondBean> response) {
                SingleSecondBean body = response.body();
                next=body.getResult().getNext();
                if (refresh.isRefreshing()){
                    data.clear();
                }
                data.addAll(body.getResult().getGoods_list());
                adapter.notifyDataSetChanged();
                refresh.refreshComplete();

            }

            @Override
            public void onFailure(Call<SingleSecondBean> call, Throwable t) {
                refresh.refreshComplete();
            }
        });
    }

    private void initRv() {
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
    }

    private void initViews(View view) {
        rv = (RecyclerView) view.findViewById(R.id.single_second_rv);
        refresh = (PtrClassicFrameLayout) view.findViewById(R.id.single_second_refresh);
        RefreshUtils.setRefresh(getContext(),refresh);
        linear = (LinearLayout) view.findViewById(R.id.linear);
    }

    private void getShopData(String goods_id) {

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(APIManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IHome iHome = retrofit.create(IHome.class);
        Call<GoodsBean> call = iHome.getGoodsData(goods_id);
        call.enqueue(new Callback<GoodsBean>() {
            @Override
            public void onResponse(Call<GoodsBean> call, Response<GoodsBean> response) {
                body = response.body();

            }

            @Override
            public void onFailure(Call<GoodsBean> call, Throwable t) {
                Toast.makeText(getActivity(), "网络出错啦", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void upToPopupWindows() {
        //加载弹窗布局
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindows_layout, null);
        //将网络获取的商品信息设置给pop
        if (body!=null){
            GoodsBean.ResultEntity.DetailEntity bean = body.getResult().getDetail();

            TextView tao_pwd= (TextView) contentView.findViewById(R.id.tao_pwd);
            tao_pwd.setText(bean.getTao_pwd());

            ImageView iv= (ImageView) contentView.findViewById(R.id.pop_img);
            Glide.with(getContext()).load(bean.getGoods_image().trim()).into(iv);

            TextView content= (TextView) contentView.findViewById(R.id.pop_content);
            content.setText(bean.getGoods_name());

            TextView shop= (TextView) contentView.findViewById(R.id.pop_shop);
            shop.setText(bean.getShop_price());

            TextView market= (TextView) contentView.findViewById(R.id.pop_market);
            market.setText(bean.getMarket_price());

            body=null;
            //最后的参数必须为true才能获取焦点
            pop = new PopupWindow(contentView, PtrFrameLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            //设置可获取焦点
            pop.setFocusable(true);
            pop.setOutsideTouchable(true);
            //加上下面两行可以用back键关闭popupwindow，否则必须调用dismiss();
            ColorDrawable dw = new ColorDrawable(Color.parseColor("#FFFFFFFF"));
            pop.setBackgroundDrawable(dw);
            //设置动画
            pop.setAnimationStyle(R.style.mypopupwindows_anim_style);
            //设置显示位置
            pop.showAtLocation(linear, Gravity.BOTTOM,0,0);
            //设置pop显示时背景变暗
            params = getActivity().getWindow().getAttributes();
            params.alpha=0.7f;
            getActivity().getWindow().setAttributes(params);
            pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    params = getActivity().getWindow().getAttributes();
                    params.alpha=1.0f;
                    getActivity().getWindow().setAttributes(params);
                }
            });
        }

    }
}
