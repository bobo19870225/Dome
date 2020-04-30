package com.yy.dome.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yy.dome.R;

public class MyMainDialog extends Dialog {
    Context context;
    private ClickListenerInterface clickListenerInterface;

    public interface ClickListenerInterface{
        void doTime();
        void doSinger();
        void doSong();
        void doAlum();
    }

    public MyMainDialog(Context context){
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init(){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_vrmap_pay,null);
        setContentView(view);

        LinearLayout ll_time = (LinearLayout) view.findViewById(R.id.main_dialog_ll_time);
        TextView ll_singer = (TextView) view.findViewById(R.id.vip_pay);
        TextView ll_alum = (TextView) view.findViewById(R.id.vrmap_quxiao);
        TextView ll_song = (TextView) view.findViewById(R.id.vrp_determine);

        ll_time.setOnClickListener(new ClickListener());
        ll_singer.setOnClickListener(new ClickListener());
        ll_alum.setOnClickListener(new ClickListener());
        ll_song.setOnClickListener(new ClickListener());

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics();//获取屏幕的宽和高用
        lp.width =(int) (d.widthPixels*0.8);
        dialogWindow.setAttributes(lp);
    }

    public void setClickLinstener(ClickListenerInterface clickListenerInterface){
        this.clickListenerInterface = clickListenerInterface;
    }

    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.main_dialog_ll_time:
                    clickListenerInterface.doAlum();
                    break;
                case R.id.vip_pay:
                    clickListenerInterface.doSinger();
                    break;
                case R.id.vrmap_quxiao:
                    clickListenerInterface.doSong();
                    break;
                case R.id.vrp_determine:
                    clickListenerInterface.doTime();
                    break;
            }
        }
    }


}
