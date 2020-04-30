/*
 * Copyright 2013 David Schreiber
 *           2013 John Paul Nalog
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.yy.dome.ui.activity.answer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yy.dome.R;


/**
 * Created by zhh on 2017/11/18.
 */
public class FancyCoverFlowSampleAdapter extends FancyCoverFlowAdapter {

    private LayoutInflater mInflater;
    public String[] strings;
    boolean isHide = false;



    public FancyCoverFlowSampleAdapter(Context context, String[] strings) {
        this.strings = strings;
        mInflater = LayoutInflater.from(context);
    }




    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return strings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getCoverFlowItem(final int position, View reuseableView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (reuseableView == null) {
            holder = new ViewHolder();
            reuseableView = mInflater.inflate(R.layout.gallery_item, null);
            //由屏幕大小适配边距(可调)--(一般不修改)
            reuseableView.setLayoutParams(new FancyCoverFlow.LayoutParams(ScreenUtils.dip2px(viewGroup.getContext(),
                    ScreenUtils.getScreenHeight(viewGroup.getContext()) > 1000 ? 280 : 265),
                    ScreenUtils.dip2px(viewGroup.getContext(), 350)));

            holder.tv_content = (TextView) reuseableView.findViewById(R.id.tv_content);
            holder.tv1 = (TextView) reuseableView.findViewById(R.id.tv1);
            holder.tv2 = (TextView) reuseableView.findViewById(R.id.tv2);
/*            holder.tv_fin = (TextView) reuseableView.findViewById(R.id.tv_fin);*/
            reuseableView.setTag(holder);
        } else {
            holder = (ViewHolder) reuseableView.getTag();
        }

        holder.tv_content.setText((position + 1) + "." + strings[position]);

/*
        if (position != 0) {
            holder.tv_fin.setVisibility(View.VISIBLE);
        }else {
            holder.tv_fin.setVisibility(View.INVISIBLE);
        }
*/


        //listview中的点击事件
         holder.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mCallBackInterface.callBackClick(v,position);

                if (mCallBackInterface != null) {
                    mCallBackInterface.callBackClick(v,position);
                }
            }
        });
        holder.tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mCallBackInterface.callBackClick(v,position);

                if (mCallBackInterface != null) {
                    mCallBackInterface.callBackClick(v,position);
                }
            }
        });

/*
        holder.tv_fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mCallBackInterface != null) {
                    mCallBackInterface.callBackClick(v,position);
                }
              // mCallBackInterface.callBackClick(v,position);
            }
        });
*/


        return reuseableView;
    }


    public void setOnItemClickListenter(CallBackInterface onItemClickListenter) {
        this.mCallBackInterface = onItemClickListenter;
    }
    private CallBackInterface mCallBackInterface; //回调接口


    final class ViewHolder {

        public TextView tv_content;
        public TextView tv1;
        public TextView tv2;
/*        public TextView tv_fin;*/
    }


}
