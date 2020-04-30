package com.yy.dome.model;





import com.yy.dome.callback.ICallBack;

import java.util.Map;


/**
 * Created by huyongjiang on 2017/8/22.
 * 接口回调
 */

public interface IModel {

    void onHttp(String url, Map<String, String> map, ICallBack callBack);

}
