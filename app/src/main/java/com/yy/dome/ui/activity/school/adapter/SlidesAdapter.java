package com.yy.dome.ui.activity.school.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.yy.dome.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/4.
 */

public class SlidesAdapter extends RecyclerView.Adapter<SlidesAdapter.SlidesViewHolder> {


    private List<String> mList = new ArrayList<>();
    String[] city={"北京","上海","贵州","深圳"};
    private Context mContext;
    private OnDeleteListener mDeleteListener;

    public SlidesAdapter(Context context) {
        this.mContext = context;
        for (int i = 0; i < 10; i++) {
            mList.add("slides::" + i);
        }
    }

    public void deleteItem(int position) {
        mList.remove(position);
        notifyDataSetChanged();
    }

    public void setDeleteListener(OnDeleteListener deleteListener) {
        this.mDeleteListener = deleteListener;
    }

    @Override
    public SlidesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_form_one, parent, false);

        return new SlidesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SlidesViewHolder holder, final int position) {
        holder.school_name.setText(mList.get(position));
        holder.deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDeleteListener!=null){
                    mDeleteListener.onDelete(position,holder.itemView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class SlidesViewHolder extends RecyclerView.ViewHolder {
        public TextView school_lqv;//录取率
        public TextView school_name;//学校名字
        public TextView school_wlk;//理工类
        public TextView school_city;//所在位置
        public TextView school_pm;//学校排名
        public TextView school_zsrs;//招生人数
        public TextView school_score;//分数
        public TextView school_lqrs;//录取人数
        public TextView school_volunteer;//专业
        public TextView deleteView;//录取人数
        public LinearLayout ContentView;

        public SlidesViewHolder(View view) {
            super(view);
            school_lqv = view.findViewById(R.id.form_school_lqv);
            school_name = view.findViewById(R.id.form_school_name);
            school_wlk = view.findViewById(R.id.form_school_wlk);
            school_city = view.findViewById(R.id.form_school_city);
            school_pm = view.findViewById(R.id.form_school_pm);
            school_zsrs = view.findViewById(R.id.form_school_zsrs);
            school_score = view.findViewById(R.id.form_school_score);
            school_lqrs = view.findViewById(R.id.form_school_lqrs);
            school_volunteer = view.findViewById(R.id.schoolvolunteer1);
            ContentView = itemView.findViewById(R.id.ll_content);
            deleteView = itemView.findViewById(R.id.iv_fun);
        }
    }

   public interface OnDeleteListener {
        void onDelete(int position, View view);
    }
}
