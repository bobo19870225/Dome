package com.yy.dome.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.entity.HomeSchoolInfo;
import com.yy.dome.entity.TestBean;
import com.yy.dome.entity.home.SchoolInfo;
import com.yy.dome.entity.home.VolunteerInfo;
import com.yy.dome.util.Utils;
import com.yy.dome.widget.ZQRoundOvalImageView;

import java.util.ArrayList;
import java.util.List;


public class HorizontalListViewAdapter extends BaseAdapter {
    private Context con;
    String[] bt={"清华大学","北京大学","人民大学","中国科大","上海交大","南京大学","武汉大学","复旦大学"};
    String[] city={"北京市","北京市","北京市","合肥市","上海市","南京市","武汉市","上海市"};
    int[] img={R.mipmap.qh_log,R.mipmap.bd_log,R.mipmap.rm_log
            ,R.mipmap.kd_log,R.mipmap.sh_log,R.mipmap.nj_log,
            R.mipmap.wh_log,R.mipmap.fd_log
    };
    private List<HomeSchoolInfo> lists;//数据源


    private final Context context;

    public HorizontalListViewAdapter(Context context, List<HomeSchoolInfo> lists) {
        this.context = context;
        this.lists=lists;
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
            convertView = View.inflate(context, R.layout.item_view, null);

            holder.tv_name = (TextView)convertView.findViewById(R.id.item_gridview_name);
            holder.tv_city = (TextView)convertView.findViewById(R.id.item_city);
            holder.iv_nul = (ZQRoundOvalImageView)convertView.findViewById(R.id.item_gridview_image);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(lists.get(position).getName());
        holder.tv_city.setText(lists.get(position).getCity());
        holder.iv_nul.setImageResource(lists.get(position).getImage());
       /// holder.linearLayout.setBackgroundResource(img[position]);
        //Glide.with(context).load(url+((ArticleInfo.DataBean)path).getTitleImg()).transform(new GlideRoundTransform(context, 10)).into(imageView);
        //Glide.with(con).load(url+lists.get(position).getLogo()).into(holder.iv_nul);
        //holder.iv_nul.setImageBitmap(byteBitmap(Utils.strToByteArray(lists.get(position).getLogo().substring(lists.get(position).getLogo().indexOf(",")+1))));
        return convertView;
    }
    class ViewHolder {
        TextView tv_city;
        TextView tv_name;
        ZQRoundOvalImageView iv_nul;
        LinearLayout lv;
    }

}
