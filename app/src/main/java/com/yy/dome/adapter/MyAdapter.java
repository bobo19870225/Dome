package com.yy.dome.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.entity.home.ArticleInfo;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.util.GlideRoundTransform;

import java.util.List;


/**
 * Created by huyongjiang on 2017/1/12.
 */

public class MyAdapter extends BaseAdapter {

    LayoutInflater inflater;
    Context context;
    private Context con;
    List<ArticleInfo.Data> datas;
    public MyAdapter(List<ArticleInfo.Data> list, Context context){
        this.datas = list;
        this.inflater = LayoutInflater.from(context);
        this.context=context;
        con=context;
    }

    @Override
    public int getCount() {

        return datas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_list_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
            try {
                ArticleInfo.Data itemsBean= ( ArticleInfo.Data) getItem(position);
                holder.tvTitle.setText(itemsBean.getTitle());
                holder.tvTime.setText(UtilTools.stampToDate(itemsBean.getTime()));
                holder.tvCommentNum.setText(itemsBean.getHot());
                Glide.with(con).load(Api.URLs+itemsBean.getTitleImg()).thumbnail(0.1f).transform(new GlideRoundTransform(con,10)).into(holder.tvNewsImage);
            } catch (Exception e) {

        }
        return convertView;
    }

    class ViewHolder {
        TextView tvTitle;
        ImageView tvNewsImage;
        TextView tvCommentNum;
        TextView tvTime;



        public ViewHolder(View itemView) {
            tvNewsImage=(ImageView) itemView.findViewById(R.id.list_view_image);
            tvTitle = (TextView) itemView.findViewById(R.id.list_view_title);
            tvCommentNum = (TextView) itemView.findViewById(R.id.list_view_cishu);
            tvTime = (TextView) itemView.findViewById(R.id.list_view_time);
        }
    }

}
