package com.yy.dome.model;

import com.yy.dome.callback.ICallBack;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;


/**
 * Created by huyongjiang on 2017/8/22.
 * 网络请求篇
 */

public class Model implements IModel {

    @Override
    public void onHttp(String url, Map<String,String> map, final ICallBack callBack) {

        OkHttpUtils.post().url(url).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(okhttp3.Call call, Exception e, int id) {
                callBack.onError("加载数据失败");
            }
            @Override
            public void onResponse(String response, int id) {
                callBack.onDataCallBackListenter(response);

            }

        });

    }
}
