package com.yy.dome.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yy.dome.R;
import com.yy.dome.entity.HomeSchoolInfo;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.widget.ZQRoundOvalImageView;

import java.util.List;

public class SchoolListAdp extends BaseAdapter {
    private Context con;
    String[] bt={"清华大学","北京大学","人民大学","中国科大","上海交大","南京大学","武汉大学","复旦大学"};
    String[] city={"北京市","北京市","北京市","合肥市","上海市","南京市","武汉市","上海市"};
    int[] img={R.mipmap.qh_log,R.mipmap.bd_log,R.mipmap.rm_log
            ,R.mipmap.kd_log,R.mipmap.sh_log,R.mipmap.nj_log,
            R.mipmap.wh_log,R.mipmap.fd_log
    };

    private final Context context;

    public SchoolListAdp(Context context) {
        this.context = context;
        this.bt=bt;
        this.con=context;
    }

    @Override
    public int getCount() {
        return bt.length;
    }

    @Override
    public Object getItem(int position) {
        return bt[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SchoolListAdp.ViewHolder holder;
        if (null == convertView) {
            holder = new SchoolListAdp.ViewHolder();
            convertView = View.inflate(context, R.layout.item_school_list, null);
            holder.tv_name = (TextView)convertView.findViewById(R.id.school_name_s);//学校名字
            holder.tv_city = (TextView)convertView.findViewById(R.id.school_form_city);//城市
            holder.schoolenrollratio = (TextView)convertView.findViewById(R.id.item_schoolenrollratio);//录取率
            holder.minimumscore = (TextView)convertView.findViewById(R.id.minimum_score);//最低分
            holder.schoolvolunteer1 = (TextView)convertView.findViewById(R.id.schoolvolunteer1);//学校名字
            holder.schoolvolunteer2 = (TextView)convertView.findViewById(R.id.schoolvolunteer2);//城市
            holder.schoolvolunteer3 = (TextView)convertView.findViewById(R.id.schoolvolunteer3);//录取率
            holder.schoolvolunteer4 = (TextView)convertView.findViewById(R.id.schoolvolunteer4);//学校名字
            holder.schoolvolunteer5 = (TextView)convertView.findViewById(R.id.schoolvolunteer5);//城市
            holder.schoolvolunteer6 = (TextView)convertView.findViewById(R.id.schoolvolunteer6);//录取率

            convertView.setTag(holder);
        }else {
            holder = (SchoolListAdp.ViewHolder) convertView.getTag();
        }
        holder.tv_city.setText(city[position]);
        holder.schoolenrollratio.setText(UtilFileDB.Schoolenrollratio()+"%");

        return convertView;
    }
    class ViewHolder {
        TextView tv_city;//城市
        TextView tv_name;//学校名字
        TextView schoolenrollratio;//录取率
        TextView minimumscore;//学校名字
        TextView schoolvolunteer1;//城市
        TextView schoolvolunteer2;//学校名字
        TextView schoolvolunteer3;//城市
        TextView schoolvolunteer4;//学校名字
        TextView schoolvolunteer5;//城市
        TextView schoolvolunteer6;//学校名字
    }

}
