package com.yy.dome.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;


import com.yy.dome.api.Api;
import com.yy.dome.callback.ICallBack;
import com.yy.dome.model.IModel;
import com.yy.dome.model.Model;
import com.yy.dome.tool.NetUtils;
import com.yy.dome.util.ACache;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by huyongjiang on 2017/9/1.
 */

public class MultiFunctionPresenter extends BasePresenter<IView> {
    public static final String urls = "http://192.168.3.200:3957/ideal.htm?";
    private IModel iModel;

    public MultiFunctionPresenter(){
        iModel = new Model();
    }

    /***
     * 请求数据
     * @param context
     * @param map
     * @param position
     */
    public void functionMultiRequest(String url,Context context, Map<String,String> map,final int position)
    {
        final IView iRxJavaView=getView();
        if (iModel!=null && iRxJavaView != null)
        {
            iRxJavaView.onLoadContributorStart();

            if (!NetUtils.isNetworkAvailable(context)){
                iRxJavaView.onNetWork();
                return;
            }
            iModel.onHttp(url,map, new ICallBack() {
                @Override
                public void onDataCallBackListenter(String contributorSubscribr) {
                    if(iRxJavaView!=null)
                    {
                        iRxJavaView.onLoadContribtorComplete(contributorSubscribr,position);
                    }
                }
                @Override
                public void onError(String string) {
                    if(iRxJavaView!=null)
                    {
                        iRxJavaView.onError();
                    }
                }
            });
        }else {
            iRxJavaView.onError();
        }
    }


    /***
     * 请求数据
     * @param context
     * @param map
     * @param position
     */
    public void functionMultiRequests(String url,Context context, Map<String,String> map,final int position)
    {
        final IView iRxJavaView=getView();

        if (iModel!=null && iRxJavaView != null)
        {
            iRxJavaView.onLoadContributorStart();

            if (!NetUtils.isNetworkAvailable(context)){
                iRxJavaView.onNetWork();
                return;
            }
            iModel.onHttp(url,map, new ICallBack() {
                @Override
                public void onDataCallBackListenter(String contributorSubscribr) {
                    if(iRxJavaView!=null)
                    {
                        iRxJavaView.onLoadContribtorComplete(contributorSubscribr,position);
                    }
                }
                @Override
                public void onError(String string) {
                    if(iRxJavaView!=null)
                    {
                        iRxJavaView.onError();
                    }
                }
            });
        }else {
            iRxJavaView.onError();
        }
    }



    /***
     * 请求数据并缓存
     * @param context
     * @param map
     * @param position
     */
    public void functionMultiRequestCache(Context context, final ACache aCache, Map<String, String> map, final String cache, final int position)
    {
        final IView iRxJavaView=getView();
        if (iModel!=null && iRxJavaView != null)
        {
            iRxJavaView.onLoadContributorStart();
            if (UtilFileDB.ISSELETEDATA(aCache,cache)){
                iRxJavaView.onLoadContribtorComplete(UtilFileDB.SELETEFile(aCache,cache),position);
                return;
            }
            if (!NetUtils.isNetworkAvailable(context)){
                iRxJavaView.onNetWork();
                return;
            }
            iModel.onHttp((Api.URLs),map, new ICallBack() {

                @Override
                public void onDataCallBackListenter(String contributorSubscribr) {
                    if(iRxJavaView!=null)
                    {
                        iRxJavaView.onLoadContribtorComplete(contributorSubscribr,position);

                    }
                }
                @Override
                public void onError(String string) {
                    if(iRxJavaView!=null)
                    {
                        iRxJavaView.onError();
                    }
                }
            });
        }else {
            iRxJavaView.onError();
        }
    }



    /***
     * 上传文件
     * @param context
     * @param file
     * @param map
     * @param position
     */

    public void functionMultiRequestFile(Context context, final File file, Map<String, String> map, final int position)
    {
        final IView iRxJavaView=getView();
        if (iModel!=null && iRxJavaView != null)
        {
            iRxJavaView.onLoadContributorStart();
            if (!NetUtils.isNetworkAvailable(context)){
                iRxJavaView.onNetWork();
                return;
            }
            OkHttpUtils.post().addFile("filedata", "scb.jpg", file).url(urls).params(map)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            iRxJavaView.onError();
                        }
                        @Override
                        public void onResponse(String response, int id) {
                            iRxJavaView.onLoadContribtorComplete(response,position);
                        }
                    });

        }else {
            iRxJavaView.onError();
        }
    }

    public void functionDataReqReturn(String content,int position)
    {
        final IView iRxJavaView=getView();
        iRxJavaView.onLoadContribtorComplete(content,position);
    }


    public void showDownloadAPK(final Context context,final String url) {
        Log.i("ss", Environment.getExternalStorageDirectory().getAbsolutePath());
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils
                        .get()
                        .url(url)
                        .build()
                        .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(),"lastest.apk") {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.i("ss", "123");
                            }
                            @Override
                            public void inProgress(float progress, long total, int id) {
                                super.inProgress(progress, total, id);
                                Log.i("ss","1111");
                            }

                            @Override
                            public void onResponse(File response, int id) {
                                showSelectAPK(context);
                            }
                        });
            }
        }).start();

    }

    private void showSelectAPK(Context context){
       // uninstallApk(context,context.getPackageName());
        File fileLocation = new File(Environment.getExternalStorageDirectory(), "lastest.apk");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(fileLocation), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static void uninstallApk(Context context, String packageName) {
        Uri uri = Uri.parse("package:" + packageName);
        Intent intent = new Intent(Intent.ACTION_DELETE, uri);
        context.startActivity(intent);
        Toast.makeText(context,"卸载",Toast.LENGTH_LONG).show();

    }

}
