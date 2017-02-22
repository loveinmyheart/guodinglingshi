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
import com.wjh.guoding.activity.Bean.SingleSecondBean;

import java.util.List;

/**
 * Created by lenovo on 2017/2/21.
 */
public class SingleSecondAdapter extends RecyclerView.Adapter<SingleSecondAdapter.SecondViewHolder> {
    List<SingleSecondBean.ResultEntity.GoodsListEntity> data;
    LayoutInflater inflater;


    OnItemClickListenner listenner;
    //item点击事件接口回调
    public interface OnItemClickListenner{
        void onItemClick(int position);
    }
    public SingleSecondAdapter(Context context,
                               List<SingleSecondBean.ResultEntity.GoodsListEntity> data,
                               OnItemClickListenner listenner) {
        this.data = data;
        inflater = LayoutInflater.from(context);
        this.listenner=listenner;
    }

    @Override
    public SecondViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fragment_single_second_item_layout, parent, false);
        SecondViewHolder holder = new SecondViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SecondViewHolder holder, final int position) {

        if (listenner!=null){
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenner.onItemClick(position);
                }
            });
        }
        //绑定数据
        ImageView iv= (ImageView) holder.getViewById(R.id.single_second_iv);
        Glide.with(iv.getContext()).load(data.get(position)
                                             .getGoods_image())
                                             .placeholder(R.drawable.placeholder)
                                             .into(iv);

        TextView contentView= (TextView) holder.getViewById(R.id.single_second_content);
        contentView.setText(data.get(position).getGoods_name());

        TextView shopView= (TextView) holder.getViewById(R.id.single_second_shop_price);
        shopView.setText(data.get(position).getShop_price());

        TextView marketView= (TextView) holder.getViewById(R.id.single_second_market_price);
        marketView.setText(data.get(position).getMarket_price());
        //计算优惠券
        float shop = Float.valueOf(data.get(position).getShop_price());
        float market = Float.valueOf(data.get(position).getMarket_price());
        TextView preView= (TextView) holder.getViewById(R.id.preferential);
        preView.setText((int)(market-shop)+"");


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class SecondViewHolder extends RecyclerView.ViewHolder {
        View view;
        public SecondViewHolder(View itemView) {
            super(itemView);
            view=itemView;
        }
        public View getViewById(int id){
            return view.findViewById(id);
        }
    }
}
