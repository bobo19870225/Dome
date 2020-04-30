package com.yy.dome.adapter;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.entity.home.ArticleInfo;
import com.yy.dome.tool.DataList;
import com.yy.dome.util.GlideRoundTransform;

import java.util.List;

public class GalleryAdapter  extends BaseAdapter {


	private List<ArticleInfo.Data> mList;
	private Context con;
	private Context context;
	public GalleryAdapter(Context context,List<ArticleInfo.Data> mList){
		this.context=context;
		this.mList = mList;
		this.con=context;
	}

	public int getCount() {
		return mList.size();
		//return Integer.MAX_VALUE;//返回一个无限大的值，可以 无限循环

	}

	public Object getItem(int position) {
		return mList.get(position-1);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView view = null;

		try {
			view = new ImageView(context);
			view.setScaleType(ImageView.ScaleType.FIT_XY);
			view.setLayoutParams(new Gallery.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
			view.setBackgroundResource(0);
			Glide.with(con).load(Api.URLs +mList.get(position%mList.size()).getTitleImg()).thumbnail(0.1f).transform(new GlideRoundTransform(con,4)).into(view);

		} catch (Exception e) {
			Glide.with(con).load(Api.URLs +mList.get(position%mList.size()).getTitleImg()).thumbnail(0.1f).transform(new GlideRoundTransform(con,4)).into(view);
		}

		return view;
	}
}
