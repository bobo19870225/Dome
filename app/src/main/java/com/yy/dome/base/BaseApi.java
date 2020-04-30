package com.yy.dome.base;

import android.content.Context;


import com.yy.dome.R;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.util.UtilFileDB;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huyongjiang on 2017/10/31.
 */

public class BaseApi {

    /***
     * 参数公共类
     * @return
     */
    protected Map<String,String> showRequestDataModel(Context context, String timestamp)
    {
        Map<String, String> map = new HashMap<>();
        map.put("m", "api");
        map.put("appid", context.getString(R.string.appId));
        map.put("token", UtilTools.MD5((context.getString(R.string.appId) + context.getString(R.string.appKey) + timestamp.substring(0, 6))));
        return map;
    }

}
