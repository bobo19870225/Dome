package com.yy.dome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yy.dome.R;


public class GridviewAdapter extends BaseAdapter {

    int[] img={R.mipmap.bd,R.mipmap.qhdx,R.mipmap.wh,R.mipmap.hz};
    String[] city={"北京","上海","贵州","深圳"};
    String[] text={"我去我婆婆佛怕啊","口袋里撒考虑到斯科拉","威卡威流量卡都快来","我看电视拉拉队克"};
    String[] add={"88","66","9999","6666"};
    private LayoutInflater inflater;

    public GridviewAdapter(Context context) {
        // TODO Auto-generated constructor stub
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return img.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return img[position];
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
            convertView = inflater.inflate(R.layout.item_gridview, null);

            holder.image1 = (ImageView) convertView.findViewById(R.id.image_grid);
            holder.image2 = (ImageView) convertView.findViewById(R.id.add_image);
            holder.text1 = (TextView) convertView.findViewById(R.id.grid_text);
            holder.text2 = (TextView) convertView.findViewById(R.id.grid_city);
            holder.text3 = (TextView) convertView.findViewById(R.id.add_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.image1.setImageResource(img[position]);
        holder.text1.setText(text[position]);
        holder.text2.setText(city[position]);
        holder.text3.setText(add[position]);
        // holder.image1=(ImageView) convertView.getTag();
        return convertView;
    }

    class ViewHolder {
        TextView text3;
        TextView text2;
        TextView text1;
        ImageView image1;
        ImageView image2;
    }
}