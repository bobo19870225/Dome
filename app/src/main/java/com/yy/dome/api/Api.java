package com.yy.dome.api;


import android.util.Log;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


public class Api  {
    public static final String URLs = "http://192.168.3.111:6868/";
    //public static final String URLs = "http://192.168.3.99:6868/";
    //public static final String URLs = "http://helloyun.cn:8080/";
    public   static final String ul = "http://3s.net579.com:22879/ideal.htm?";

    public static Map<String, String> showRegisterDataInfo( String phone ,String yzm,String password,String time, String key ) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "register");
        map.put("phone", phone);
        map.put("smsCode",yzm);
        map.put("password", password);
        map.put("area", "52");
        map.put("time", time);
        map.put("key", key);
        return map;

    }
    /**
     *
     * 获取时间
     */
    public static Map<String, String> time() {
        Map<String, String> map = new HashMap<>();
        map.put("method", "getTime");

        return map;
    }
    /**
     *
     * 登录
     */
    public static Map<String, String> Login(String phone,String password,String time ,String key) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "login");
        map.put("phone", phone);
        map.put("password", password);
        map.put("time", time);
        map.put("key", key);
        Log.e("登录",map.toString());
        return map;
    }

    /**
     *
     * 验证码
     */
    public static Map<String, String> YZM(String phone) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "rgtSmsApi");
        map.put("phone", phone);
        return map;
    }

    /**
     *
     * 验证码
     */
    public static Map<String, String> UPDATAYZM(String phone) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "smsApi");
        map.put("phone", phone);
        return map;
    }

    /**
     *
     * 修改分数
     */
    public static Map<String, String> updateScore(String phone,String token,String score ,String wlk,String rank) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "updateScore");
        map.put("phone", phone);
        map.put("token", token);
        map.put("score", score);
        map.put("area", "52");
        if (wlk.equals("理科")) {
            map.put("wlk", "1");
        } else {
            map.put("wlk", "0");
        }
        map.put("rank", rank);
        return map;
    }

    /**
     * @param phone
     * @param token
     * 获取院校
     * @return
     */
    public static Map<String, String> AccessSchool(String phone,String token) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "getSchool");
        map.put("phone", phone);
        map.put("token", token);
        map.put("page", "1");

        return map;
    }

    /**
     *
     * @param phone
     * @param token
     * @param ids
     *关注学校
     * @return
     */

    public static Map<String, String> SCHOOLLIST(String phone,String token,String ids) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "getSchool");
        map.put("phone", phone);
        map.put("token", token);
        map.put("page", "1");
        map.put("ids", ids);
        return map;
    }

    /**
     *
     * @param phone
     * @param token
     * @param id
     *文章收藏
     * @return
     */

    public static Map<String, String> FollowArticle(String phone,String token,String id) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "followArticle");
        map.put("phone", phone);
        map.put("token", token);
        map.put("page", "1");
        map.put("ids", id);
        return map;
    }


    /**
     *
     * @param phone
     * @param token
     * @param content
     *  @param contact
     *提交反馈
     * @return
     */

    public static Map<String, String> Feedback(String phone,String token,String content,String contact) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "opinion");
        map.put("phone", phone);
        map.put("token", token);
        map.put("content", content);
        map.put("contact", contact);

        return map;
    }

    /**
     * @param phone
     * @param token
     * 获取专业
     * @return
     */
    public static Map<String, String> Volunteer(String phone,String token) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "getSubjectApp");
        map.put("phone", phone);
        map.put("token", token);
        return map;
    }


    /**
     * @param phone
     * @param token
     * 获取院校
     * 智能填报
     * @return
     */
    public static Map<String, String> SCHOOLITEM(String phone,String token ) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "smartSchoolResult");
        map.put("phone", phone);
        map.put("token", token);

        return map;
    }


    /**
     * @param phone
     * @param year
     * @param code
     * 获取院校专业
     * @return
     */
    public static Map<String, String> SCHOOLIVOLUNTTEER(String phone,String code ,String year) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "smartSubjectResult");
        map.put("phone", phone);
        map.put("code", code);
        map.put("year", year);

        return map;
    }

    /**
     * @param phone
     * @param token
     * 获取知名学校
     * @return
     */
    public static Map<String, String> SCHOOL(String phone,String token ) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "getSchool");
        map.put("phone", phone);
        map.put("token", token);
        map.put("page", "1");
        map.put("IsFamous", "1");
        Log.e("school",map.toString());
        return map;
    }

    /**
     * @param phone
     * @param token
     * 文章高考头条
     * @return
     */
    public static Map<String, String> ARTICLE(String phone,String token,String page) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "getArticle");
        map.put("phone", phone);
        map.put("token", token);
        map.put("type", "3");
        map.put("page", page);
        map.put("pageNo", "10");
        return map;
    }

    /**
     * @param phone
     * @param token
     * 文章咨询
     * @return
     */
    public static Map<String, String> ARTICLES(String phone,String token ,String type) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "getArticle");
        map.put("phone", phone);
        map.put("token", token);
        map.put("type", type);
        Log.e("datamap",map.toString());
        return map;
    }

    /**
     * @param phone
     * @param token
     * 轮播
     * @return
     */
    public static Map<String, String> lunbo(String phone,String token ) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "getArticle");
        map.put("phone", phone);
        map.put("token", token);
        map.put("type", "7");
        map.put("page", "1");
        map.put("pageNo", "10");
        return map;
    }



    /***
     * 地区子查询
     */
    public static Map<String, String> showMyCityForData(String phone, String token) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "getArea");
        map.put("phone", phone);
        map.put("token", token);

        return map;
    }
    /***
     * 文章顶部查询
     */
    public static Map<String, String> TOPNEWS(String phone, String token) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "getArticleType");
        map.put("phone", phone);
        map.put("token", token);
        map.put("valid", "1");

        return map;
    }

    /***
     * 版本检测
     * @return
     */
    public static Map<String,String> showDataVersion(String phone, String token)
    {
        Map<String, String> map = new HashMap<>();
        map.put("method", "getVersion");
        map.put("phone", phone);
        map.put("token", token);
        map.put("type", "0");
        return map;
    }

    /***
     * 修改头像
     */
    public static Map<String, String> showUpdateImageData(String phone, String token) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "getStudentImg");
        map.put("phone", phone);
        map.put("token", token);
        Log.e("map",map.toString());
        return map;
    }

    /***
     * 修改头像
     */
    public static Map<String, String> UPIMAGE(String phone, String token,String base) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "updateScore");
        map.put("phone", phone);
        map.put("token", token);
        map.put("headImg", base);

        Log.e("map",map.toString());
        return map;
    }


    /***
     * 修改用户密码
     *
     * @return
     */
    public static final Map<String, String> USERPWD(String phone, String oldpassword, String newpassword) {
        Map<String, String> map = new Hashtable<String, String>();
        map.put("method", "updatePassword");
        map.put("phone", phone);
        map.put("oldPassword",oldpassword);
        map.put("smsCode","blank");
        map.put("newPassword",newpassword);
        Log.e("map.top",map.toString());
        return map;
    }


    /***
     * 修改用户姓名
     *
     * @return
     */
    public static final Map<String, String> USERNAME(String phone, String token, String name) {
        Map<String, String> map = new Hashtable<String, String>();
        map.put("method", "updateScore");
        map.put("phone", phone);
        map.put("token", token);
        map.put("name", name);
        return map;
    }

    /***
     * 重置志愿表
     *
     * @return
     */
    public static final Map<String, String> CZZYB(String phone, String token) {
        Map<String, String> map = new Hashtable<String, String>();
        map.put("method", "clearIdeal");
        map.put("phone", phone);
        map.put("token", token);
        return map;
    }


    public static Map<String, String> updataPassWORD( String phone ,String smsCode,String newPassword ) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "updatePassword");

        map.put("phone", phone);
        map.put("oldPassword","blank");
        map.put("smsCode",smsCode);
        map.put("newPassword", newPassword);
        Log.e("map",map.toString());
        return map;

    }


    /***
     * 获取recruit
     * id
     *
     * @return
     */
    public static final Map<String, String> SCHOOLResult(String phone, String token) {
        Map<String, String> map = new Hashtable<String, String>();
        map.put("method", "smartSchoolResult");
        map.put("phone", phone);
        map.put("token", token);
        return map;
    }

    /***
     * 前台从智能筛选出的学校页面进入详情页面后，点击数据展示时请求
     * id
     *10.提供学校录取信息用于数据展示的接口
     * @return
     */
    public static final Map<String, String> SCHOOLDATA(String id, String phone) {
        Map<String, String> map = new Hashtable<String, String>();
        map.put("method", "allDataAnalyse");
        map.put("recruit", id);
        map.put("phone", phone);
        Log.i("map",map.toString());
        return map;
    }



    public static final Map<String, String> SCHOOLIdentify(String name) {
        Map<String, String> map = new Hashtable<String, String>();
        map.put("method", "getTrueSchool");
        map.put("name", name);
        Log.i("map",map.toString());
        return map;
    }


    /***
     * 微信支付
     *
     * @return
     */
    public static final Map<String, String> WEIXINPAY(String pay) {
        Map<String, String> map = new Hashtable<>();
        map.put("c", "wxpay");
        map.put("a", "getsign");
        map.put("order_amount", pay);

        return map;
    }

    /***
     * 微信支付
     *
     * @return
     */
    public static final Map<String, String> wxapi(String phone ,String goods_Id) {
        Map<String, String> map = new Hashtable<>();
        map.put("method", "userToshops");
        map.put("shop_Phone", phone);
        map.put("goods_Id",goods_Id);
        Log.e("wwww",map.toString());
        return map;
    }


    /***
     * 微信支付
     *
     * @return
     */
    public static final Map<String, String> VRMapPay(String phone ,String school_Id,String goods_Id) {
        Map<String, String> map = new Hashtable<>();
        map.put("method", "userToshops");
        map.put("shop_Phone", phone);
        map.put("school_Id", school_Id);
        map.put("goods_Id", goods_Id);
        Log.e("VR收费",map.toString());
        return map;
    }

    /***
     * 微信支付
     *
     * @return
     */
    public static final Map<String, String> ExpertVideo(String phone ,String expertIds,String goods_Id) {
        Map<String, String> map = new Hashtable<>();
        map.put("method", "userToshops");
        map.put("shop_Phone", phone);
        map.put("expertIds", expertIds);
        map.put("goods_Id",goods_Id );
        Log.e("wwww",map.toString());
        return map;
    }

    /***
     * 支付接口回调
     *
     * @return
     */
    public static final Map<String, String> pay(String phone,String shop_Trade,String shop_Times,
                                                String shop_Type,String goods_Id,String school_Id,String expertIds) {
        Map<String, String> map = new Hashtable<>();
        map.put("method", "addShops");
        map.put("shop_Phone", phone);
        map.put("shop_Trade", shop_Trade);//订单号
        map.put("shop_Times", shop_Times);//时间戳
        map.put("shop_Type", shop_Type);//交易状态
        map.put("goods_Id",goods_Id );//商品类型
        map.put("school_Id",school_Id );//学校id
        map.put("expertIds",expertIds );//专家id


        Log.e("wwww",map.toString());
        return map;
    }

    /***
     * VR地图
     *
     * @return
     */
    public static final Map<String, String> VRMAP(String phone,String token,String schoolId,String goodsId) {
        Map<String, String> map = new Hashtable<>();
        map.put("method", "getSchoolVRMap");
        map.put("phone", phone);
        map.put("token", token);
        map.put("schoolId", schoolId);
        map.put("goodsId", goodsId);
        Log.e("VRMAP",map.toString());
        return map;
    }


    /***
     * 获取专家视频与学生权限的接口
     *
     * @return
     */
    public static final Map<String, String> SCHOOLVIDEO(String phone,String token) {
        Map<String, String> map = new Hashtable<>();
        map.put("method", "getExpertVideos");
        map.put("phone", phone);
        map.put("token", token);
        Log.e("wwww",map.toString());
        return map;
    }


    /***
     * 观看视频接口
     *
     * @return
     */
    public static final Map<String, String> LOOKVIDEO(String phone,String token,String id) {
        Map<String, String> map = new Hashtable<>();
        map.put("method", "getOneExpertVideos");
        map.put("phone", phone);
        map.put("token", token);
        map.put("id", id);
        Log.e("wwww",map.toString());
        return map;
    }

    /***
     * 保存志愿表接口
     *
     * @return
     */
    public static final Map<String, String> PreservationFORM(String phone,String token,String schoolId,String subjects
            ,String enrollRatio,String branch,String name) {
        Map<String, String> map = new Hashtable<>();
        map.put("method", "saveIdeal");
        map.put("phone", phone);
        map.put("token", token);
        map.put("schoolId", schoolId);
        map.put("subjects", subjects);
        map.put("enrollRatio", enrollRatio);
        map.put("branch", branch);
        map.put("name", name);
        Log.e("wwww",map.toString());
        return map;
    }

    /***
     * 本科一批志愿表
     *
     * @return
     */
    public static final Map<String, String> LookUpFORMONE(String phone,String token) {
        Map<String, String> map = new Hashtable<>();
        map.put("method", "getSaveIdeal");
        map.put("phone", phone);
        map.put("token", token);
        map.put("batch", "1");
        Log.e("wwww",map.toString());
        return map;
    }

    /***
     * 本科二批志愿表
     *
     * @return
     */
    public static final Map<String, String> LookUpFORMTwo(String phone,String token) {
        Map<String, String> map = new Hashtable<>();
        map.put("method", "getSaveIdeal");
        map.put("phone", phone);
        map.put("token", token);
        map.put("batch", "2");
        Log.e("wwww",map.toString());
        return map;
    }

    /***
     * 本科二批志愿表
     *
     * @return
     */
    public static final Map<String, String> LookUpFORMThree(String phone,String token) {
        Map<String, String> map = new Hashtable<>();
        map.put("method", "getSaveIdeal");
        map.put("phone", phone);
        map.put("token", token);
        map.put("batch", "3");
        Log.e("wwww",map.toString());
        return map;
    }

    /***
     * 删除志愿表
     *
     * @return
     */
    public static final Map<String, String> DeleteForm(String phone,String token,String schoolId,String name) {
        Map<String, String> map = new Hashtable<>();
        map.put("method", "delSaveIdeal");
        map.put("phone", phone);
        map.put("token", token);
        map.put("schoolId", schoolId);
        map.put("name", name);

        Log.e("wwww",map.toString());
        return map;
    }


}
