package com.yy.dome.ui.activity.school.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.yy.dome.R;
import com.yy.dome.adapter.OnItemClickListenter;

import com.yy.dome.api.Api;
import com.yy.dome.entity.PrestigiousSchoolInfo;
import com.yy.dome.ui.activity.home.video.ExpertVideoInfo;
import com.yy.dome.ui.activity.home.video.VideoInfo;
import com.yy.dome.util.GlideRoundTransform;

import java.util.ArrayList;

public class ExpertVideoAdapter extends RecyclerView.Adapter<ExpertVideoAdapter.ViewHolder> {

    private ArrayList<ExpertVideoInfo>  lists;//数据源
    private LayoutInflater layoutInflater;
    private Context con;

    public ExpertVideoAdapter(Context context,ArrayList<ExpertVideoInfo>  lists) {
        this.lists = lists;
        con=context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @NonNull
    @Override
    public ExpertVideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolder = layoutInflater.inflate(R.layout.item_expertvideo, parent, false);
        return new ExpertVideoAdapter.ViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpertVideoAdapter.ViewHolder holder, int position) {
        holder.tv_name.setText(lists.get(position).getName());
        Glide.with(con).load(Api.URLs+lists.get(position).getCover()).thumbnail(0.1f).transform(new GlideRoundTransform(con,4)).into(holder.iv_nul);


    }

    public void setOnItemClickListenter(OnItemClickListenter onItemClickListenter) {
        this.onItemClickListenter = onItemClickListenter;
    }
    public OnItemClickListenter onItemClickListenter;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        ImageView iv_nul;

        public ViewHolder(View view) {
            super(view);

            tv_name = view.findViewById(R.id.expertvideo_name);
            iv_nul = view.findViewById(R.id.expertvideo_image);
            tv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListenter != null) {
                        onItemClickListenter.onItemClick(view, getAdapterPosition());
                    }
                }
            });

            iv_nul.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListenter != null) {
                        onItemClickListenter.onItemClick(view, getAdapterPosition());
                    }
                }
            });

        }

    }

}