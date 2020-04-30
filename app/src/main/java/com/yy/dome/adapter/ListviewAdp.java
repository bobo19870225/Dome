package com.yy.dome.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.entity.home.ArticleInfo;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.util.GlideRoundTransform;

import java.util.List;

public class ListviewAdp  extends BaseAdapter {
    private Context con;
    private final Context context;
    private  List<ArticleInfo.Data> mList;//数据源
    public ListviewAdp(Context context,List<ArticleInfo.Data> mList) {
        this.context = context;
        this.mList = mList;
        this.con=context;
    }
    @Override
    public int getCount() {
        return (mList==null || mList.size()==0) ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position-1);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_home_list, null);
            holder.text1 = (TextView) convertView.findViewById(R.id.home_title);
            holder.text2 = (TextView) convertView.findViewById(R.id.dianzan);
            holder.text3 = (TextView) convertView.findViewById(R.id.home_time);
            holder.imageView = (ImageView) convertView.findViewById(R.id.home_image);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text1.setText(mList.get(position).getTitle());
        holder.text2.setText(String.valueOf(mList.get(position).getSeeTimes()));
        holder.text3.setText(UtilTools.stampToDate(mList.get(position).getTime()));
        Glide.with(con).load(Api.URLs +mList.get(position).getTitleImg()).into(holder.imageView);
        return convertView;
    }
    class ViewHolder {
        TextView text1;
        TextView text2;
        TextView text3;
        ImageView imageView;
    }
}
