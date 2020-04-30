package com.yy.dome.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.text.TextUtils;

import androidx.appcompat.app.AlertDialog;

/**
 * Created by huyongjiang on 2017/9/2.
 */

public class DialogHelper {

    /***
     * 获取一个进度对话框(耗时操作使用)
     * @param context
     * @param message 加载提示信息
     * @return
     */
    public static ProgressDialog getWaitDialog(Context context, String message) {
        ProgressDialog waitDialog = new ProgressDialog(context);
        if (!TextUtils.isEmpty(message)) {
            waitDialog.setMessage(message);
            waitDialog.setMax(25);
        }
        return waitDialog;
    }

    public static AlertDialog.Builder getMessageDialog(Context context, String message, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListenerclear) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setMessage(message);
        builder.setPositiveButton("确定", onClickListener);
        builder.setNegativeButton("取消", onClickListenerclear);
        return builder;
    }

    /***
     * 获取一个dialog对象，进行相关操作
     *
     * @param context
     * @return
     */
    public static AlertDialog.Builder getDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder;
    }




}
