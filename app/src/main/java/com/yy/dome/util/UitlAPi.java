package com.yy.dome.util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class UitlAPi {
    static  String yy1="";
    static public String show(String data, Handler mHandler){
        JSONObject jsonObject = null;
        try {
            Log.i("data", data);
            jsonObject = new JSONObject(data);
             yy1 = jsonObject.getString("url");
            Log.i("999", yy1);
            //通过mHandler对象obtainMessage()方法得到一个消息msg对象实例
            Message msg = mHandler.obtainMessage();
            //封装消息id
            msg.what = 1;//作为标示，便于接收消息
            msg.obj = yy1;//(这里是Object,标示可以传任意类型的数据(如：对象，集合...))
            mHandler.sendMessage(msg);//发送消息
        } catch (
                JSONException e) {
            e.printStackTrace();
        }
            return yy1;

    }

}
