package com.wjh.guoding.activity.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wjh.guoding.R;
import com.wjh.guoding.activity.Bean.DiscoverSecondBean;
import com.wjh.guoding.activity.Utils.APIManager;
import com.wjh.guoding.activity.Utils.Constant;
import com.wjh.guoding.activity.Utils.IHome;
import com.wjh.guoding.activity.adapter.SpecialAdapter;
import com.wjh.guoding.activity.fragment.DiscoveryFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2017/2/22.
 */
public class DiscoverListActivity extends AppCompatActivity {

    private ListView lv;
    List<DiscoverSecondBean.ResultEntity.SpecialListEntity> data=new ArrayList<>();
    private SpecialAdapter<DiscoverSecondBean.ResultEntity.SpecialListEntity> adapter;
    String page="1";
    private String next="0";
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_list_layout);
        //获取上个界面传过来的tag_id
        String tag_id = getIntent().getStringExtra(Constant.TAG_ID);
        //获取上个界面传来的标题数据
        String title = getIntent().getStringExtra(Constant.DISCOVER_TITLE);
        //找到控件
        initViews();
        //初始化adapter,绑定数据
        adapter = new SpecialAdapter<DiscoverSecondBean.ResultEntity.SpecialListEntity>(getApplicationContext(),data, R.layout.special_adapter_item_layout) {
            @Override
            protected void bindData(ViewHolder viewHolder, DiscoverSecondBean.ResultEntity.SpecialListEntity specialListEntity, int position) {
                ImageView iv= (ImageView) viewHolder.getViewById(R.id.special_image);
                Glide.with(getApplicationContext()).load(specialListEntity.getImage().trim()).placeholder(R.drawable.placeholder).into(iv);
                TextView title= (TextView) viewHolder.getViewById(R.id.special_title);
                title.setText(specialListEntity.getSpecial_name());
                TextView viewNum= (TextView) viewHolder.getViewById(R.id.special_view);
                viewNum.setText(specialListEntity.getLike_num());
            }
        };
        //设置toolbar
        setToolBar(title);
        //设置adapter
        lv.setAdapter(adapter);
        //网络请求数据
        getData(tag_id);
        //设置lv的item点击事件
        setItemClick();
    }

    private void setItemClick() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(DiscoverListActivity.this,WebActivity.class);
                intent.putExtra(Constant.WEBURL_TAG,data.get(position).getWeb_url());
                intent.putExtra(Constant.WEBIMG_TAG,data.get(position).getImage());
                intent.putExtra(Constant.WEBTITLE_TAG,data.get(position).getSpecial_name());
                //创建共享元素
                ImageView iv= (ImageView) view.findViewById(R.id.special_image);
                ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation(
                        DiscoverListActivity.this,
                        iv,
                        Constant.WEBSHARE_TAG
                );
                //启动
                ActivityCompat.startActivity(DiscoverListActivity.this,intent,optionsCompat.toBundle());
            }
        });
    }

    private void setToolBar(String title) {
        //设置标题
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(Color.parseColor("#FFFE9477"));
        setSupportActionBar(toolbar);
        //设置显示导航箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置导航按钮可用
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getData(String tag_id) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(APIManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IHome iHome = retrofit.create(IHome.class);
        Call<DiscoverSecondBean> call = iHome.getDiscoverSecondData(tag_id, page);
        call.enqueue(new Callback<DiscoverSecondBean>() {
            @Override
            public void onResponse(Call<DiscoverSecondBean> call, Response<DiscoverSecondBean> response) {
                DiscoverSecondBean body = response.body();
                //用来判断是否还有下一页数据
                next = body.getResult().getNext();
                data.addAll(body.getResult().getSpecial_list());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<DiscoverSecondBean> call, Throwable t) {

            }
        });
    }

    private void initViews() {
        lv = (ListView) findViewById(R.id.discover_list_view);
        toolbar = (Toolbar) findViewById(R.id.discover_toolbar);
    }
}
