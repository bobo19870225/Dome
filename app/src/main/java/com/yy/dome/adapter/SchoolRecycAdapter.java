package com.yy.dome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yy.dome.R;
import com.yy.dome.ui.activity.home.video.VideoInfo;
import com.yy.dome.util.GlideRoundTransform;

import java.util.List;

public class SchoolRecycAdapter extends RecyclerView.Adapter<SchoolRecycAdapter.ViewHolder> {

    private List<VideoInfo> lists;//数据源
    private LayoutInflater layoutInflater;
    private Context con;

    public SchoolRecycAdapter(Context context,List<VideoInfo> lists) {
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolder = layoutInflater.inflate(R.layout.item_school_video, parent, false);
        return new ViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolRecycAdapter.ViewHolder holder, int position) {
        holder.tv_name.setText(lists.get(position).getName());
        Glide.with(con).load(lists.get(position).getImage()).thumbnail(0.1f).transform(new GlideRoundTransform(con,10)).into(holder.iv_nul);


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

            tv_name = view.findViewById(R.id.school_title_video);
            iv_nul = view.findViewById(R.id.school_video_image);


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