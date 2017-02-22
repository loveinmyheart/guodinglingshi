package com.wjh.guoding.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * listview多布局万能适配器
 */
public abstract class SpecialAdapter<T> extends BaseAdapter {
    //数据源
    List<T> data;
    LayoutInflater inflater;
    //布局id数组
    int[] layoutIds;
    //传不定参数
    public SpecialAdapter(Context context, List<T> data, int... ids){
        this.data=data;
        inflater=LayoutInflater.from(context);
        layoutIds=ids;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    //布局的个数，根据传进来的布局id数组的长度
    @Override
    public int getViewTypeCount() {
        return layoutIds.length;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            //得到数据类型
            int index=getItemViewType(position);
            //根据类型加载相应的布局
            convertView=inflater.inflate(layoutIds[index],parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        //绑定数据
        bindData(viewHolder,data.get(position),position);
        return convertView;
    }

    protected abstract void bindData(ViewHolder viewHolder, T t, int position);

    public class ViewHolder{
        View view;
        public ViewHolder(View view){
            this.view=view;
        }

        public View getViewById(int id){
            return view.findViewById(id);
        }
    }
}
