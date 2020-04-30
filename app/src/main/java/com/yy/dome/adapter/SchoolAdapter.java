package com.yy.dome.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.entity.home.SchoolInfo;
import com.yy.dome.util.GlideRoundTransform;

import java.util.List;

public class SchoolAdapter extends BaseAdapter {

    List<SchoolInfo.Data> Listview;
    private LayoutInflater inflater;
    private Context con;

    public SchoolAdapter(Context context, List<SchoolInfo.Data> Listview) {
        // TODO Auto-generated constructor stub
        this.inflater = LayoutInflater.from(context);
        this.Listview = Listview;
        this.con=context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Listview.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return   Listview.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_find_listview, null);
            holder.text1 = (TextView) convertView.findViewById(R.id.school_name);
            holder.text2 = (TextView) convertView.findViewById(R.id.school_city);
            holder.text3 = (TextView) convertView.findViewById(R.id.school_time);
            holder.text4 = (TextView) convertView.findViewById(R.id.school_title);

            holder.imageView = (ImageView) convertView.findViewById(R.id.list_image);




            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text1.setText(Listview.get(position).getName());
        holder.text2.setText(Listview.get(position).getCity());
        holder.text4.setText(Listview.get(position).getPertain());
        holder.text3.setText(Listview.get(position).getSchoolCode());


        Glide.with(con).load(Api.URLs+Listview.get(position).getFamousImg()).thumbnail(0.1f).transform(new GlideRoundTransform(con,10)).into(holder.imageView);


        return convertView;
    }


    class ViewHolder {
        TextView text3;
        TextView text1;
        TextView text2;
        TextView text4;
        TextView textView;
        ImageView imageView;
    }

}