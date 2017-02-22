package com.wjh.guoding.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wjh.guoding.R;
import com.wjh.guoding.activity.Bean.DiscoverBean;

import java.util.List;

/**
 * Created by lenovo on 2017/2/21.
 */
public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder> {

    //数据源
    List<DiscoverBean.ResultEntity.TagListEntity> data;
    LayoutInflater inflater;
    OnItemClickListenner listenner;
    //点击事件接口回调
    public interface OnItemClickListenner{
        void onItemClick(int position);
    }
    public DiscoverAdapter(Context context, List<DiscoverBean.ResultEntity.TagListEntity> data,OnItemClickListenner listenner) {
         this.data=data;
        inflater=LayoutInflater.from(context);
        this.listenner=listenner;

    }

    @Override
    public DiscoverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.discover_item_layout, parent, false);
        DiscoverViewHolder holder=new DiscoverViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DiscoverViewHolder holder, final int position) {
        if (listenner!=null){
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenner.onItemClick(position);
                }
            });
        }

        //绑定数据
        ImageView iv= (ImageView) holder.getViewById(R.id.discover_iv);
        Glide.with(iv.getContext()).load(data.get(position).getImage().
                                          trim()).
                                          placeholder(R.drawable.placeholder)
                                          .into(iv);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class DiscoverViewHolder extends RecyclerView.ViewHolder {
        View view;

        public DiscoverViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        public View getViewById(int id){
            return view.findViewById(id);
        }

    }
}
