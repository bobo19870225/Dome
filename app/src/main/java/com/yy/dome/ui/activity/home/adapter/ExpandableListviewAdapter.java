package com.yy.dome.ui.activity.home.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.alibaba.fastjson.JSON;
import com.yy.dome.R;
import com.yy.dome.entity.Groupsinfo;
import com.yy.dome.entity.HomeSchoolInfo;
import com.yy.dome.entity.PrestigiousSchoolInfo;
import com.yy.dome.ui.MainFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ExpandableListviewAdapter extends BaseExpandableListAdapter {
    //Model：定义的数据
    private Context context;
    private List<Groupsinfo> lists;//数据源
    private ArrayList<ArrayList<PrestigiousSchoolInfo>> mItemNameList ;

    private static final int N = 99;
    public ExpandableListviewAdapter(Context context, List<Groupsinfo> lists, ArrayList<ArrayList<PrestigiousSchoolInfo>> mItemNameList){
        this.context=context;
        this.lists=lists;
        this.mItemNameList=mItemNameList;
/*        Log.i("0", JSON.toJSONString(this.lists));//run
        Log.i("1", ""+mItemNameList.size());*/
    }

    @Override
    public int getGroupCount() {
        return lists.size();
        //return (lists==null || lists.size()==0) ? 0 : lists.size();
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return mItemNameList.get(groupPosition).size();
        //return (mItemNameList==null || mItemNameList.get(groupPosition).size()==0) ? 0 : mItemNameList.get(groupPosition).size();
    }
    @Override
    public Object getGroup(int groupPosition) {
        return lists.get(groupPosition);
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mItemNameList.get(groupPosition).get(childPosition);
        //return mItemNameList.get(groupPosition).get(childPosition-1);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;

    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    //分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们
    public boolean hasStableIds() {
        return true;
    }


    @Override
/**
 *
 * 获取显示指定组的视图对象
 *
 * @param groupPosition 组位置
 * @param isExpanded 该组是展开状态还是伸缩状态，true=展开
 * @param convertView 重用已有的视图对象
 * @param parent 返回的视图对象始终依附于的视图组
 */
    public View getGroupView( int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expand_parent_item,parent,false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.parent_textview_id = convertView.findViewById(R.id.parent_textview_id);
            groupViewHolder.relativeLayout = convertView.findViewById(R.id.ray_out);
            groupViewHolder.linearLayout = convertView.findViewById(R.id.group_lay_out);
            groupViewHolder.parent_image = convertView.findViewById(R.id.parent_image);
            convertView.setTag(groupViewHolder);
        }else {
            groupViewHolder = (GroupViewHolder)convertView.getTag();
        }
       // groupViewHolder.parent_textview_id.setVisibility(lists.get(groupPosition).equals("null")?View.VISIBLE:View.GONE);
        groupViewHolder.parent_textview_id.setText(lists.get(groupPosition).getName());
        //groupViewHolder.relativeLayout.setVisibility(lists.get(groupPosition).getChildList().get(groupPosition).equals("null")?View.VISIBLE:View.GONE);
/*
        if (lists.get(groupPosition).equals("null")){


        }
*/




        if (isExpanded){
            groupViewHolder.parent_image.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.down));
        }else{
            groupViewHolder.parent_image.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.arrow_right));
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expand_chidren_item,parent,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.degree = (TextView)convertView.findViewById(R.id.chidren_degree);
            childViewHolder.chidren_layout = (LinearLayout) convertView.findViewById(R.id.chidren_layout);
            childViewHolder.lay = (LinearLayout) convertView.findViewById(R.id.lay);
            childViewHolder.chidren_item = (TextView)convertView.findViewById(R.id.chidren_item);
            childViewHolder.chidren_item_text = (TextView)convertView.findViewById(R.id.chidren_item_text);
            childViewHolder.chidren_school_name = (TextView)convertView.findViewById(R.id.chidren_school_name);
            childViewHolder.chidren_school_wkl = (TextView)convertView.findViewById(R.id.chidren_school_wkl);
            childViewHolder.chidren_school_city = (TextView)convertView.findViewById(R.id.chidren_school_city);
            childViewHolder.chidren_school_Fraction = (TextView)convertView.findViewById(R.id.chidren_school_Fraction);
            childViewHolder.chidren_school_admission = (TextView)convertView.findViewById(R.id.chidren_school_admission);
            childViewHolder.code = (TextView)convertView.findViewById(R.id.chidren_school_code);
            childViewHolder.chidren_school_zsrs = (TextView)convertView.findViewById(R.id.chidren_school_zsrs);
            childViewHolder.chidren_school_is211 = (TextView)convertView.findViewById(R.id.chidren_school_is211);
            childViewHolder.chidren_school_is985 = (TextView)convertView.findViewById(R.id.chidren_school_is985);
            convertView.setTag(childViewHolder);

        }else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }


        PrestigiousSchoolInfo info= mItemNameList.get(groupPosition).get(childPosition);

        Log.i("222"+childPosition,JSON.toJSONString(info));//运行

            if (info.getEnrollRatio()<=50){
                childViewHolder.degree.setText("冲");
                childViewHolder.chidren_item_text.setText("风险极大");
                childViewHolder.chidren_item_text.setTextColor(ContextCompat.getColor(context,R.color.c));
                childViewHolder.chidren_layout.setBackground(context.getResources().getDrawable(R.drawable.table_h_divider));
            }if(info.getEnrollRatio() <= 76 && info.getEnrollRatio() >= 50){
            childViewHolder.degree.setText("稳");
            childViewHolder.chidren_item_text.setText("风险适中");
            childViewHolder.chidren_item_text.setTextColor(ContextCompat.getColor(context,R.color.w));
            childViewHolder.chidren_layout.setBackground(context.getResources().getDrawable(R.drawable.table_v_divider));
            }if (info.getEnrollRatio()>=77){
            childViewHolder.degree.setText("保");
            childViewHolder.chidren_item_text.setText("风险极小");
            childViewHolder.chidren_item_text.setTextColor(ContextCompat.getColor(context,R.color.b));
            childViewHolder.chidren_layout.setBackground(context.getResources().getDrawable(R.drawable.ic_bookmark_24dp));

        }
            childViewHolder.chidren_school_name.setText(info.getName());
            childViewHolder.chidren_school_Fraction.setText("录取最低分:" + info.getMinScore());
            childViewHolder.chidren_school_admission.setText("录取最低位次:" + info.getRanking());

            if (info .getSchoolCode()!=null){
                childViewHolder.code.setText("院校代码："+info .getSchoolCode());
            }else {
                childViewHolder.code.setText("院校代码："+"");
            }

            childViewHolder.chidren_school_city.setText("所在地:"+info.getCity());
            childViewHolder.chidren_school_zsrs.setText("招生人数:"+info.getEnrollNumber());
            childViewHolder.chidren_school_wkl.setText(info.getType1());
            childViewHolder.chidren_item.setText(String.valueOf(info.getEnrollRatio()+"%"));
            Log.e("tag",JSON.toJSONString(childViewHolder));

            childViewHolder.chidren_school_is211.setVisibility(info.getIs211().equals("true")?View.VISIBLE:View.GONE);
            childViewHolder.chidren_school_is985.setVisibility(info.getIs985().equals("true")?View.VISIBLE:View.GONE);



        return convertView;
    }

    //指定位置上的子元素是否可选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    static class GroupViewHolder {
        TextView parent_textview_id;
        ImageView parent_image;
        RelativeLayout relativeLayout;
        LinearLayout linearLayout;
    }

    static class ChildViewHolder {
        TextView degree;
        TextView chidren_item;
        TextView chidren_item_text;
        TextView chidren_school_name;
        TextView chidren_school_wkl;
        TextView chidren_school_city;
        TextView chidren_school_Fraction;
        TextView chidren_school_admission;
        TextView code;
        TextView chidren_school_zsrs;
        LinearLayout chidren_layout;
        LinearLayout lay;
        TextView chidren_school_is211;
        TextView chidren_school_is985;
    }
}