package com.yy.dome.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;
import com.yy.dome.R;
import com.yy.dome.wx.Constants;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by huyongjiang on 17/3/8.
 *
 */
public class EventBusUtil {


    /****
     * 获取版本号
     * @param activity
     * @return
     */
    public static final String getVersion(Context activity) {
        try {
            PackageManager manager = activity.getPackageManager();
            PackageInfo info = manager.getPackageInfo(activity.getPackageName(), 0);
            String version =info.versionName+"."+info.versionCode;
            return  version;
        } catch (Exception e) {
            return "1.1.0";
        }
    }





    /****
     * qq分享
     * @param tencent
     * @param context
     * @param url
     */
    public static final void onClickShare(Tencent tencent, Activity context, String url) {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE,
                QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "测试项目");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "");
        if (url.equals("12")){
            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,
                    "https://www.hao123.com/?tn=88013251_2_hao_pg");
        }else {
            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,
                    url);
        }
        tencent.shareToQQ(context, params, null);
    }

    /****
     * QQ空间分享
     * @param tencent
     * @param context
     * @param url
     */
    @SuppressWarnings("unused")
    public static final void shareToQQzone(Tencent tencent, Activity context, String url) {
        final Bundle params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE,
                QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "测试");
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "");
        params.putString(QzoneShare.SHARE_TO_QQ_APP_NAME, "测试");
        if (url.equals("12")){
            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,
                    "https://www.hao123.com/?tn=88013251_2_hao_pg");
        }else {
            params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL,
                    url);
        }
        ArrayList<String> imageUrls = new ArrayList<String>();
        imageUrls.add("https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=背景图片&step_word=&hs=0&pn=20&spn=0&di=18150&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2178133010%2C1163630923&os=47473959%2C252432212&simid=0%2C0&adpicid=0&lpn=0&ln=1048&fr=&fmq=1575600474394_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic27.nipic.com%2F20130311%2F5056611_175953598000_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FdAzdH3F9dAzdH3F0mmc99dh9wb0bua1_z%26e3Bip4s&gsm=&rpstart=0&rpnum=0&islist=&querylist=&force=undefined");
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);
        params.putInt(QzoneShare.SHARE_TO_QQ_EXT_INT,
                QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        tencent.shareToQzone(context, params, null);

    }

    public static void SHOWSTSCSHARE(View view, final PopupWindow popShrak, final LinearLayout ll_popups, final Tencent mTencent, final Activity context, final String url) {
        popShrak.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popShrak.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popShrak.setBackgroundDrawable(new BitmapDrawable());
        popShrak.setFocusable(true);
        popShrak.setOutsideTouchable(true);
        popShrak.setContentView(view);
        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parents);
        view.findViewById(R.id.shread_qq).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        onClickShare(mTencent,context,url);
                    }
                });
        view.findViewById(R.id.shread_qqzone).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        EventBusUtil.shareToQQzone(mTencent,context,url);
                    }
                });

        view.findViewById(R.id.shread_wx).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                       onShowWx(context,1);
                    }
                });

        view.findViewById(R.id.shread_wxments).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        onShowWx(context,2);
                    }
                });
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShrak.dismiss();
                ll_popups.clearAnimation();
            }
        });

    }


    /****
     * 微信分享
     * @param activity
     * @param i 好友或者朋友圈
     */
    private static final void onShowWx(Activity activity, int i) {
        try {
            IWXAPI api = WXAPIFactory.createWXAPI(activity, Constants.APP_ID);
            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl = "http://zq.liaidi.com/?m=app&c=index";
            WXMediaMessage msg = new WXMediaMessage(webpage);
            msg.title = "中企联盟";
            msg.description = "力爱迪科技有限公司";
            try{
                Bitmap thumb = BitmapFactory.decodeStream(new URL("http://hyj.songdewei.com/logo.png").openStream());
                Bitmap thumbBmp = Bitmap.createScaledBitmap(thumb,120,150,true);
                thumb.recycle();

            msg.setThumbImage(thumb);

            }catch(IOException e) {

                e.printStackTrace();
            }
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("webpage");
            req.message = msg;
            req.scene = i == 1 ? SendMessageToWX.Req.WXSceneSession
                    : SendMessageToWX.Req.WXSceneTimeline;
            api.sendReq(req);
        } catch (Exception e) {
            Toast.makeText(activity,"分享失败", Toast.LENGTH_LONG).show();
        }
    }
    private static final String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis())
                : type + System.currentTimeMillis();
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
