package com.yy.dome.util;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;


import com.yy.dome.api.PubcApplication;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by huyongjiang on 2017/3/1.
 */

public class UtilFileDB {

    public static SharedPreferences sharedPreferences = PubcApplication.preferences;
    public static SharedPreferences.Editor editor = PubcApplication.editor;
    private SimpleDateFormat sf = null;



    /****
     * 永久缓存
     * @param key
     * @param content
     */
    public static final void ADDSHAREDDATA(String key,String content)
    {
        editor.putString(key,content);
        editor.commit();
    }
    /***
     * 清空
     * @param key
     */
    public static final void DELETESHAREDDATA(String key)
    {
        editor.remove(key);
        editor.commit();
    }
    /***
     * 查询数据
     * @param key
     * @return
     */
    public static final String SELECTSHAREDDATA(String key) {
        String content = sharedPreferences.getString(key, "");
        if (!content.equals("")) {
            return content;
        }
        return null;
    }

    public static final String LOGINNAME() {
        String loginName = sharedPreferences.getString("zqname ", "");
        if (!loginName.equals("")) {
            return loginName;
        }
        return "尊敬的用户您好！";
    }

    /**
     * 验证是否登录并获取相关信息
     * @return
     */
    public static final boolean ISLOGIN() {
        if (sharedPreferences.getString("tel", "").equals("")  || sharedPreferences.getString("useruid", "").equals("")) {
            return true;
        }
        return false;
    }

    /**
     * 验证是否登录并获取相关信息
     * @return
     */
    public static final boolean ANSWER() {
        if (sharedPreferences.getString("stringType", "").equals("")  || sharedPreferences.getString("useruid", "").equals("")) {
            return true;
        }
        return false;
    }


    /***
     * 获取用户头像
     * @return
     */
    public static final Bitmap LOGINIMGBITMAP(ACache aCache) {
        return aCache.getAsBitmap("myimg");
    }
    public static final String LOGINIMAGEURL() {
        String URL = sharedPreferences.getString("image", "");
        if (!URL.equals("")) {
            return URL;
        }
        return null;
    }



    /***
     * 用户头像是否存在
     * @return
     */
    public static final boolean ISLOGINIMG(ACache aCache) {
        if (aCache.getAsBitmap("myimg")!=null)
        {
            return true;
        }
        return false;
    }


    public static final String Token() {
        String token = sharedPreferences.getString("token", "");
        if (!token.equals("")) {
            return token;
        }
        return "";
    }

    public static final String stringType() {
        String stringType = sharedPreferences.getString("stringType", "");
        if (!stringType.equals("")) {
            return stringType;
        }
        return "";
    }





    public static final String UID() {
        String useruid = sharedPreferences.getString("useruid", "");
        if (!useruid.equals("")) {
            return useruid;
        }
        return "";
    }


    public static final String uname() {
        String uname = sharedPreferences.getString("zqname", "");
        if (!uname.equals("")) {
            return uname;
        }
        return "";
    }

    /***
     *获取学校id
     */
    public static final String SCHOOLID() {
        String schoolid = sharedPreferences.getString("schoolid", "");
        if (!schoolid.equals("")) {
            return schoolid;
        }
        return "";
    }

    /***
     *获取学校最高分
     */
    public static final String SchoolMaxScore() {
        String schoolmaxscore = sharedPreferences.getString("schoolmaxscore", "");
        if (!schoolmaxscore.equals("")) {
            return schoolmaxscore;
        }
        return "";
    }

    /***
     *获取学校最低分
     */
    public static final String SchoolMinScore() {
        String schoolminscore = sharedPreferences.getString("schoolminscore", "");
        if (!schoolminscore.equals("")) {
            return schoolminscore;
        }
        return "";
    }

    /***
     *获取学校录取率
     */
    public static final String Schoolenrollratio() {
        String enrollratio = sharedPreferences.getString("enrollratio", "");
        if (!enrollratio.equals("")) {
            return enrollratio;
        }
        return "";
    }


    /***
     *获取学校id
     */
    public static final String SCHOOLid() {
        String id = sharedPreferences.getString("id", "");
        if (!id.equals("")) {
            return id;
        }
        return "";
    }




    public static final String code() {
        String code = sharedPreferences.getString("code", "");
        if (!code.equals("")) {
            return code;
        }
        return "";
    }
    public static final String year() {
        String year = sharedPreferences.getString("year", "");
        if (!year.equals("")) {
            return year;
        }
        return "";
    }



    public static final String Tel() {
        String tel = sharedPreferences.getString("tel", "");
        if (!tel.equals("")) {
            return tel;
        }
        return "";
    }


    /****
     * 永久缓存
     * @param aCache
     * @param key
     * @param content
     */
    public static final void ADDFile(ACache aCache, String key, String content)
    {
        aCache.put(key,content);
    }
    /***
     * 按照时间长度缓存
     * @param aCache
     * @param key
     * @param content
     * @param time
     */
    public static final void ADDFile(ACache aCache, String key, String content, int time)
    {
        aCache.put(key,content,time);
    }

    /***
     *查询数据
     * @param aCache
     * @param key
     * @return
     */
    public static final String SELETEFile(ACache aCache, String key)
    {
        return aCache.getAsString(key);
    }

    /***
     * 清空
     * @param aCache
     * @param key
     */
    public static final void DELETEFile(ACache aCache, String key)
    {
        aCache.remove(key);
    }

    /***
     *查询数据是否为空
     * @param aCache
     * @param key
     * @return
     */
    public static final boolean ISSELETEDATA(ACache aCache,String key)
    {
        if (aCache.getAsString(key) !=null){
            return true;
        }
        return false;
    }

    /***
     * 用户姓名
     * @return
     */
    public static final String LOGINNAMS(ACache aCache) {
        return aCache.getAsString("zqname");
    }



    public static final String NAME() {
        String URL = sharedPreferences.getString("name", "");
        if (!URL.equals("")) {
            return URL;
        }
        return null;
    }
    public static final String PWD() {
        String pwd = sharedPreferences.getString("pwd", "");
        if (!pwd.equals("")) {
            return pwd;
        }
        return null;
    }
    public static final String WLKS() {
        String wlk = sharedPreferences.getString("wlk", "");
        if (!wlk.equals("")) {
            return wlk;
        }
        return null;
    }

    public static final String SCORE() {
        String score = sharedPreferences.getString("score", "");
        if (!score.equals("")) {
            return score;
        }
        return null;
    }
    public static final String prepayId() {
        String prepayId = sharedPreferences.getString("prepayId", "");
        if (!prepayId.equals("")) {
            return prepayId;
        }
        return null;
    }




    public static final String xsCount() {
        String xsCount = sharedPreferences.getString("xsCount", "");
        if (!xsCount.equals("")) {
            return xsCount;
        }
        return null;
    }

    public static final String yjCount() {
        String yjCount = sharedPreferences.getString("yjCount", "");
        if (!yjCount.equals("")) {
            return yjCount;
        }
        return null;
    }
    public static final String ysCount() {
        String ysCount = sharedPreferences.getString("ysCount", "");
        if (!ysCount.equals("")) {
            return ysCount;
        }
        return null;
    }
    public static final String shCount() {
        String shCount = sharedPreferences.getString("shCount", "");
        if (!shCount.equals("")) {
            return shCount;
        }
        return null;
    }

    public static final String qyCount() {
        String qyCount = sharedPreferences.getString("qyCount", "");
        if (!qyCount.equals("")) {
            return qyCount;
        }
        return null;
    }

    public static final String cgCount() {
        String cgCount = sharedPreferences.getString("cgCount", "");
        if (!cgCount.equals("")) {
            return cgCount;
        }
        return null;
    }
    /**
    缓存用户是否有观看视频权限
     */

    public static final String flag() {
        String flag = sharedPreferences.getString("flag", "");
        if (!flag.equals("")) {
            return flag;
        }
        return null;
    }
    /**
     学校是否分校标识
     */

    public static final String branch() {
        String branch = sharedPreferences.getString("branch", "");
        if (!branch.equals("")) {
            return branch;
        }
        return null;
    }
    /**
     学校名字name
     */

    public static final String Schoolname() {
        String schoolname = sharedPreferences.getString("schoolname", "");
        if (!schoolname.equals("")) {
            return schoolname;
        }
        return null;
    }
    /**
     服务器版本号
     */

    public static final String serverVersion() {
        String serverVersion = sharedPreferences.getString("serverVersion", "");
        if (!serverVersion.equals("")) {
            return serverVersion;
        }
        return null;
    }
    /**
     服务器版本号
     */

    public static final String clientVersion() {
        String serverVersion = sharedPreferences.getString("clientVersion", "");
        if (!serverVersion.equals("")) {
            return serverVersion;
        }
        return null;
    }

    public static final String Goodid() {
        String goodid = sharedPreferences.getString("goodid", "");
        if (!goodid.equals("")) {
            return goodid;
        }
        return null;
    }

    /***
     * 专家视频id
     * @return
     */
    public static final String expertIds() {
        String expertIds = sharedPreferences.getString("expertIds", "");
        if (!expertIds.equals("")) {
            return expertIds;
        }
        return null;
    }
    /***
     * 微信回调结果
     * @return
     */
    public static final String result() {
        String result = sharedPreferences.getString("result", "");
        if (!result.equals("")) {
            return result;
        }
        return null;
    }






    /***
     * 用户分数
     * @return
     */
    public static final String LOGINSCORE(ACache aCache) {
        return aCache.getAsString("fenshu");
    }
    /***
     * 文理科
     * @return
     */
    public static final String WIK(ACache aCache) {
        return aCache.getAsString("wlk");
    }

    /****
     * 时间戳
     * @return
     */
    public static final String currentTimeMillis(){
        return String.valueOf(System.currentTimeMillis());
    }

}
