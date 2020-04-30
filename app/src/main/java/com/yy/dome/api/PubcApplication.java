package com.yy.dome.api;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.DisplayMetrics;

import com.yy.dome.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.MemoryCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;


/**
 * Created by huyongjiang on 2017/11/15.
 */

public class PubcApplication extends Application {
    /**
     * 小型数据库读取
     */
    public static SharedPreferences preferences;
    /**
     * 小型数据库写入
     */
    public static SharedPreferences.Editor editor;
    public static int H,W;
    public static PubcApplication app;
    @Override
    public void onCreate()
    {
       //SpeechUtility.createUtility(PubcApplication.this, "appid=" + getString(R.string.app_id));
        super.onCreate();
        preferences = getSharedPreferences("scbdata", MODE_PRIVATE);
        editor = preferences.edit();
        app=this;
        DisplayMetrics dm = this.getResources().getDisplayMetrics();
        H=dm.heightPixels;
        W=dm.widthPixels;
        if (!(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)) {
            setTheme(R.style.Theme_AppCompat_NoActionBar);
        }
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null,
                null, null);
        CookieJarImpl cookieJar1 = new CookieJarImpl(new MemoryCookieStore());
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor("TAG"))
                .cookieJar(cookieJar1).hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                }).sslSocketFactory(sslParams.sSLSocketFactory).build();
        OkHttpUtils.initClient(okHttpClient);
        // 初始化小型数据库的读写
        preferences = getSharedPreferences("guobuga", MODE_PRIVATE);
        Boolean user_first = preferences.getBoolean("FIRST",true);
        editor = preferences.edit();

    }





}
