package com.yy.dome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yy.dome.R;
import com.yy.dome.entity.Stock;
import com.yy.dome.ui.activity.StockInfo;

import java.util.List;

public class ARightTableAdapter  extends BaseAdapter {
    private Context context;
    List<StockInfo> list;


    public ARightTableAdapter(Context context,   List<StockInfo>  models) {
        super();
        this.context = context;
        this.list = models;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ARightTableAdapter.ViewHold viewHold;
        if (convertView == null) {
            viewHold = new ARightTableAdapter.ViewHold();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_table_2, parent,false);
            viewHold.textView1 = (TextView) convertView.findViewById(R.id.table_year1);
            viewHold.textView2 = (TextView) convertView.findViewById(R.id.table_lqxs);
            viewHold.textView3 = (TextView) convertView.findViewById(R.id.table_xcfs);
            viewHold.textView4 = (TextView) convertView.findViewById(R.id.table_wcxs);

            convertView.setTag(viewHold);
        } else {
            viewHold = (ARightTableAdapter.ViewHold) convertView.getTag();
        }

        StockInfo stocks = (StockInfo) getItem(position);

        viewHold.textView1.setText(stocks.getYear());
        viewHold.textView2.setText(stocks.getLqxs());
        viewHold.textView3.setText(stocks.getXcfs());
        viewHold.textView4.setText(stocks.getWcxs());

        return convertView;
    }

    static class ViewHold {

        TextView textView1, textView2, textView3, textView4;

    }

}
