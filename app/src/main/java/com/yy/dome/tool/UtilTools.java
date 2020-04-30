package com.yy.dome.tool;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yy.dome.R;
import com.yy.dome.wx.Constants;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.tencent.tauth.Tencent;
/**
 * Created by huyongjiang on 2017/11/18.
 */

public class UtilTools {

    /*
    * 将时间戳转换为时间
    */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /****
     * 时间戳
     * @return
     */
    public static final String currentTimeMillis(){
        return String.valueOf(System.currentTimeMillis()/1000);
    }



    /****
     * qq分享
     * @param tencent
     * @param context
     * @param url
     */
    public static final void onClickShare(Tencent tencent, Activity context, String url, String title) {
        try {
            final Bundle params = new Bundle();
            params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE,
                    QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
            params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
            params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "");
            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,
                    url);
            params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,
                    "http://hyj.songdewei.com/logo.png");
            params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "中企联盟");
            params.putString(QQShare.SHARE_TO_QQ_EXT_INT, "");
            tencent.shareToQQ(context, params, null);
        } catch (Exception e) {
            return;
        }
    }

    /****
     * QQ空间分享
     * @param tencent
     * @param context
     * @param url
     */
    @SuppressWarnings("unused")
    public static final void shareToQQzone(Tencent tencent, Activity context, String url,String title) {
        try {
            final Bundle params = new Bundle();
            params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE,
                    QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
            params.putString(QzoneShare.SHARE_TO_QQ_TITLE, title);
            params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "");
            params.putString(QzoneShare.SHARE_TO_QQ_APP_NAME, "中企联盟");
            params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL,
                    url);
            ArrayList<String> imageUrls = new ArrayList<String>();
            imageUrls.add("https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=背景图片&step_word=&hs=0&pn=20&spn=0&di=18150&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2178133010%2C1163630923&os=47473959%2C252432212&simid=0%2C0&adpicid=0&lpn=0&ln=1048&fr=&fmq=1575600474394_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic27.nipic.com%2F20130311%2F5056611_175953598000_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FdAzdH3F9dAzdH3F0mmc99dh9wb0bua1_z%26e3Bip4s&gsm=&rpstart=0&rpnum=0&islist=&querylist=&force=undefined");
            params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);
            params.putInt(QzoneShare.SHARE_TO_QQ_EXT_INT,
                    QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
            tencent.shareToQzone(context, params, null);
        } catch (Exception e) {

        }

    }


    /****
     * 微信分享
     * @param activity
     * @param i 好友或者朋友圈
     */
    public static final void onShowWx(Activity activity,String title,int i,String url) {
        try {
            IWXAPI api = WXAPIFactory.createWXAPI(activity, Constants.APP_ID);
            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl = url;
            WXMediaMessage msg = new WXMediaMessage(webpage);
            msg.title = "中企联盟";
            msg.description = title;
            Bitmap thumb = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.cs_tx);
            msg.thumbData = bmpToByteArray(thumb, true);
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
    public static final String buildTransaction(final String type) {
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

        }

        return result;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }



    public static final String showRandom(){
        java.util.Random random=new java.util.Random();
        int result=random.nextInt(10);
        int lm=result+5;
        return lm+".0"+result;
    }

    public static final String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5= MessageDigest.getInstance("MD5");
        md5.update(str.getBytes());
        //加密后的字符串
        Log.i("md5"+str.getBytes(),md5.digest(str.getBytes()).toString());
        String newstr=md5.digest(str.getBytes("utf-8")).toString();
        return newstr;
    }

    public static final String MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];

        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();
    }

    public static String stringToMD5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString().toLowerCase();
    }


    /**
     * 计算ListView的高度
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        /**
         * getAdapter这个方法主要是为了获取到ListView的数据条数，所以设置之前必须设置Adapter
         */
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {

            View listItem = listAdapter.getView(i, null, listView);
            //计算每一项的高度
            listItem.measure(0, 0);
            //总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        //真正的高度需要加上分割线的高度
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


}
