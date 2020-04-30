package com.yy.dome.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.yy.dome.R;


public class BottomDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private int layoutResID;

    private int[] listenedItems;

    private OnBottomMenuItemClickListener listener;

    public BottomDialog(Context context, int layoutResID, int[] listenedItems) {
        super(context, R.style.dialog_custom);
        this.context = context;
        this.layoutResID = layoutResID;
        this.listenedItems = listenedItems;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_menu_animation);
        setContentView(layoutResID);
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth()*9/10;
        getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(true); // 点击Dialog外部消失
        for (int id : listenedItems) {
            findViewById(id).setOnClickListener(this);
        }
    }

    public interface OnBottomMenuItemClickListener {

        void onBottomMenuItemClick(BottomDialog dialog, View view);

    }

    public void setOnBottomMenuItemClickListener(OnBottomMenuItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        dismiss();
        listener.onBottomMenuItemClick(this, view);
    }

}
