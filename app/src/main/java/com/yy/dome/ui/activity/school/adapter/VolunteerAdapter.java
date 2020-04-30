package com.yy.dome.ui.activity.school.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.yy.dome.R;
import com.yy.dome.adapter.Recycleradatper;
import com.yy.dome.entity.SchoolVolunteerInfo;
import com.yy.dome.ui.activity.school.OnItemClickListenters;

import java.util.List;

public class VolunteerAdapter extends RecyclerView.Adapter<VolunteerAdapter.ViewHolder> {

    private List<SchoolVolunteerInfo.SmartSubject> data;
    private LayoutInflater layoutInflater;
    private Context con;

    public VolunteerAdapter(Context context, List<SchoolVolunteerInfo.SmartSubject> data) {

        this.data = data;
        con = context;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getItemCount() {
        return (data==null || data.size()==0) ? 0 : data.size();
    }




    @NonNull
    @Override
    public VolunteerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolder = layoutInflater.inflate(R.layout.item_volunteer_one, parent, false);
        return new VolunteerAdapter.ViewHolder(viewHolder);
    }


    @Override
    public void onBindViewHolder(@NonNull VolunteerAdapter.ViewHolder holder, int position) {

        holder.tv_name.setText(data.get(position).getName());
        holder.tv_zs.setText("计划招生："+data.get(position).getPlanNumber()+"人");
        holder.checkBox.setChecked(true);
    }



    public void setOnItemClickListentersss(OnItemClickListenters onItemClickListenters) {
        this.onItemClickListenters = onItemClickListenters;
    }
    public OnItemClickListenters onItemClickListenters;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_zs;
        CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.volunteer_name);
            tv_zs = view.findViewById(R.id.volunteer_zs);
            checkBox = view.findViewById(R.id.checkbox);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListenters != null) {
                        onItemClickListenters.onItemClick(view, getAdapterPosition());
                    }
                }
            });


        }

    }
}