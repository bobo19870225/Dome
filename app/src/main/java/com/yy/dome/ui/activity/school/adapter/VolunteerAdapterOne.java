package com.yy.dome.ui.activity.school.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yy.dome.R;
import com.yy.dome.entity.SchoolVolunteerInfo;
import com.yy.dome.ui.activity.school.OnItemClickListenters;

import java.util.List;

public class VolunteerAdapterOne extends RecyclerView.Adapter<VolunteerAdapterOne.ViewHolder> {

    private List<SchoolVolunteerInfo.SmartSubject> data;
    private LayoutInflater layoutInflater;
    private Context con;

    public VolunteerAdapterOne(Context context, List<SchoolVolunteerInfo.SmartSubject> data) {

        this.data = data;
        con = context;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @NonNull
    @Override
    public  VolunteerAdapterOne.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View viewHolder = layoutInflater.inflate(R.layout.item_volunteer_one, parent, false);
        return new VolunteerAdapterOne. ViewHolder(viewHolder);
    }


    @Override
    public void onBindViewHolder(@NonNull VolunteerAdapterOne.ViewHolder holder, int position) {

        holder.tv_name.setText(data.get(position).getName());
        holder.tv_zs.setText("计划招生：" + data.get(position).getPlanNumber() + "人");
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