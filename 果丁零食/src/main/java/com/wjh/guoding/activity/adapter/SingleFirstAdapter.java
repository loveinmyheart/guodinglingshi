package com.wjh.guoding.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wjh.guoding.R;
import com.wjh.guoding.activity.Bean.SingleFirstBean;

import java.util.List;

/**
 * Created by lenovo on 2017/2/20.
 */
public class SingleFirstAdapter extends RecyclerView.Adapter<SingleFirstAdapter.SingLeViewHolder>{

    List<SingleFirstBean.ResultEntity.GoodsListEntity>  data;
    LayoutInflater inflater;
    Context context;
    OnItemClickListenner listenner;

    //item点击事件的接口回调
    public interface OnItemClickListenner{
         void onItemClick(View v,int position);
    }


    public SingleFirstAdapter(Context context,
                              List<SingleFirstBean.ResultEntity.GoodsListEntity> data,
                              OnItemClickListenner listenner
    ){
        this.data=data;
        inflater=LayoutInflater.from(context);
        this.context=context;
        this.listenner=listenner;
    }
    @Override
    public SingLeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_first_item_layout, parent, false);
        SingLeViewHolder holder=new SingLeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SingLeViewHolder holder, final int position) {
        holder.setImage(R.id.single_first_item_iv,data.get(position).getGoods_image());
        holder.setTitle(R.id.single_first_item_title,data.get(position).getGoods_name());
        holder.setPrice(R.id.single_first_item_price,data.get(position).getShop_price());
        holder.setContent(R.id.single_first_item_content,data.get(position).getGoods_brief());
        holder.setCollect(R.id.single_first_item_collect,data.get(position).getGoods_collect());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listenner!=null){
                    listenner.onItemClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class SingLeViewHolder extends RecyclerView.ViewHolder{
        View view;
        public SingLeViewHolder(View itemView) {
            super(itemView);
            view=itemView;
        }
        public void setImage(int id,String url){
            ImageView iv= (ImageView) view.findViewById(R.id.single_first_item_iv);
            Glide.with(context).load(url.trim()).placeholder(R.drawable.placeholder).into(iv);
        }
        public void setPrice(int id,String price){
            TextView priceView= (TextView) view.findViewById(R.id.single_first_item_price);
            priceView.setText(price);
        }
        public void setContent(int id,String content){
            TextView contentView= (TextView) view.findViewById(R.id.single_first_item_content);
            contentView.setText(content);
        }
        public void setTitle(int id,String title){
            TextView titleView= (TextView) view.findViewById(R.id.single_first_item_title);
            titleView.setText(title);
        }
        public void setCollect(int id,String collect){
            TextView collectView= (TextView) view.findViewById(R.id.single_first_item_collect);
            collectView.setText(collect);
        }

    }
}
