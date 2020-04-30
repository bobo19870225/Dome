package com.yy.dome.tool;

import android.util.Log;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * Created by huyongjiang on 2016/6/6 0006
 *
 * 程序错误异常提示
 *
 */
public class ApiException {

    public static String exceptionMsg(Exception e) {
        Log.d("haha",e.toString());
        if (e instanceof RuntimeException) {
            return "很抱歉，程序运行出错了！";
        }else if (e instanceof HttpRetryException){
            return "网络未连接,请检查网络";
        }else if (e instanceof SocketTimeoutException) {
            return "服务器连接超时，请稍后重试！";
        } else if (e instanceof SocketException) {
            return "服务器连接失败，请检查网络状态！";
        } else if (e instanceof IOException) {
            return "服务器连接失败，请稍后重试！";
        } else if (e instanceof ConnectException) {
            return "网络未连接，请检查网络！";
        } else {
            return "请求错误，请稍后重试！";
        }
    }

}
