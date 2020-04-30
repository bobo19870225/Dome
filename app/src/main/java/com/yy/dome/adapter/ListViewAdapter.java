package com.yy.dome.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.LinearLayout;
import android.widget.TextView;


import com.yy.dome.R;
import com.yy.dome.entity.home.VolunteerInfo;


import java.util.List;

public class ListViewAdapter  extends BaseAdapter {
    private Context con;
    private final Context context;
    private List<VolunteerInfo.Data> lists;//数据源
    public ListViewAdapter(Context context, List<VolunteerInfo.Data> lists) {
        this.context = context;
        this.lists = lists;
        this.con=context;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
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
            convertView = View.inflate(context, R.layout.item_home_listview, null);
            holder.tv_name = (TextView)convertView.findViewById(R.id.item_top);
            holder.lv = (LinearLayout) convertView.findViewById(R.id.lv);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(lists.get(position).getName());
        return convertView;
    }
    class ViewHolder {
        TextView tv_name;
        LinearLayout lv;
    }


}
