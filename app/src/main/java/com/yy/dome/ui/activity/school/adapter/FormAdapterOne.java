package com.yy.dome.ui.activity.school.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.ui.activity.school.form.FormLookUpInfo;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.utils.ZQView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Call;


public class FormAdapterOne extends BaseAdapter {
    private LayoutInflater inflater;
    ArrayList<FormLookUpInfo.DataBean> listdata;
    private Context con;
    public FormAdapterOne(Context context, ArrayList<FormLookUpInfo.DataBean> list) {
        // TODO Auto-generated constructor stub
        this.con=context;
        this.listdata=list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return  listdata.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return listdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        ZQView slideView = (ZQView) convertView;
        if (null == slideView) {
            View  view = inflater.inflate(R.layout.item_form_one, null);
            slideView = new ZQView(con);
            slideView.setContentView(view);

            holder = new ViewHolder(slideView,position);
            slideView.setTag(holder);
        } else {
            holder = (ViewHolder) slideView.getTag();
        }
        slideView.shrink();
        holder.school_name.setText(listdata.get(position).getSchoolName());
        holder.school_lqv.setText(listdata.get(position).getEnrollRatio()+"%");
        holder.school_wlk.setText(listdata.get(position).getType1());
        holder.school_city.setText(listdata.get(position).getCity());
        if (listdata.get(position).getHotRank().equals("0")){
            holder.school_pm.setText("排名:"+"--");
        }else {
            holder.school_pm.setText("排名:"+listdata.get(position).getHotRank());
        }

        holder.school_zsrs.setText(listdata.get(position).getPlanNumber());
        holder.school_score.setText(listdata.get(position).getMaxScore());
        holder.school_lqrs.setText("录取人数："+listdata.get(position).getEnrollNumber());
        try{
                holder.school_volunteer1.setText("1、"+listdata.get(position).getSubs().get(0).getName());
                holder.school_volunteer2.setText("2、"+listdata.get(position).getSubs().get(1).getName());
                holder.school_volunteer3.setText("3、"+listdata.get(position).getSubs().get(2).getName());
                holder.school_volunteer4.setText("4、"+listdata.get(position).getSubs().get(3).getName());
                holder.school_volunteer5.setText("5、"+listdata.get(position).getSubs().get(4).getName());
                holder.school_volunteer6.setText("6、"+listdata.get(position).getSubs().get(5).getName());
        }catch (Exception E){

        }


        return slideView;
    }
    public void changeSelected(){ //刷新方法
        notifyDataSetChanged();
    }

    class ViewHolder {
        public TextView school_lqv;//录取率
        public TextView school_name;//学校名字
        public TextView school_wlk;//理工类
        public TextView school_city;//所在位置
        public TextView school_pm;//学校排名
        public TextView school_zsrs;//招生人数
        public TextView school_score;//分数
        public TextView school_lqrs;//录取人数
        public TextView school_volunteer1;//专业
        public TextView school_volunteer2;//专业
        public TextView school_volunteer3;//专业
        public TextView school_volunteer4;//专业
        public TextView school_volunteer5;//专业
        public TextView school_volunteer6;//专业

        public TextView deleteView;//删除
        public LinearLayout ContentView;
        public ViewGroup deleteHolder;

        public ViewHolder(View view,final int postuion){
            school_lqv = view.findViewById(R.id.form_school_lqv);
            school_name = view.findViewById(R.id.form_school_name);
            school_wlk = view.findViewById(R.id.form_school_wlk);
            school_city = view.findViewById(R.id.form_school_city);
            school_pm = view.findViewById(R.id.form_school_pm);
            school_zsrs = view.findViewById(R.id.form_school_zsrs);
            school_score = view.findViewById(R.id.form_school_score);
            school_lqrs = view.findViewById(R.id.form_school_lqrs);
            school_volunteer1 = view.findViewById(R.id.schoolvolunteer1);
            school_volunteer2 = view.findViewById(R.id.schoolvolunteer2);
            school_volunteer3 = view.findViewById(R.id.schoolvolunteer3);
            school_volunteer4 = view.findViewById(R.id.schoolvolunteer4);
            school_volunteer5 = view.findViewById(R.id.schoolvolunteer5);
            school_volunteer6 = view.findViewById(R.id.schoolvolunteer6);


            ContentView = view.findViewById(R.id.ll_content);
            deleteView = view.findViewById(R.id.iv_fun);
            deleteHolder = (ViewGroup) view.findViewById(R.id.holder);
            deleteHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OkHttpUtils.post().url(Api.URLs+ "ideal.htm?").params(Api.DeleteForm(UtilFileDB.Tel(),
                            UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123),
                            listdata.get(postuion).getSchoolId(),listdata.get(postuion).getName())).build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                }
                                @Override
                                public void onResponse(String response, int id) {
                                    JSONObject jsonobj;
                                    try {
                                        jsonobj = new JSONObject(response);
                                        String errno = jsonobj.getString("ret");
                                        if (errno.equals("0")) {
                                            Log.e("删除结果",response);
                                            listdata.remove(postuion);
                                            changeSelected();
                                        }
                                    } catch (JSONException e) {
                                        return;
                                    }
                                }
                            });


                }
            });
        }
    }

}