package com.yy.dome.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.yy.dome.R;
import com.yy.dome.entity.CityInfo;
import com.yy.dome.entity.PrestigiousSchoolInfo;
import com.yy.dome.entity.SchoolVolunteetInfo;

import java.util.ArrayList;
import java.util.List;

public class SchoolVolunteer extends BaseAdapter {


    private List<SchoolVolunteetInfo.AllSubject> datas;
    private LayoutInflater inflater;

    private Context con;
    public SchoolVolunteer(Context context,  List<SchoolVolunteetInfo.AllSubject> list) {
        // TODO Auto-generated constructor stub
        this.inflater = LayoutInflater.from(context);
        this.datas=list;
        this.con=context;
        Log.i("1", "123235566"+list.toString());

    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return datas == null ? 0 : datas.size();

    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return datas.get(position);
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
            convertView = inflater.inflate(R.layout.item_list_service, null);
            holder.text1 = (TextView) convertView.findViewById(R.id.school_volunteer_name);
            holder.text2 = (TextView) convertView.findViewById(R.id.school_rs);
            holder.text3 = (TextView) convertView.findViewById(R.id.school_cost);
            holder.text4 = (TextView) convertView.findViewById(R.id.school_year);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text1.setText(datas.get(position).getName());
        holder.text2.setText(datas.get(position).getPlanNumber()+"人");
        holder.text3.setText(datas.get(position).getMoney()+"/年");
        holder.text4.setText(datas.get(position).getStudyYear());

        return convertView;
    }

    class ViewHolder {
        TextView text1;
        TextView text2;
        TextView text3;
        TextView text4;
    }

}