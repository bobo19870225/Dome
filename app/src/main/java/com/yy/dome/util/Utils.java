package com.yy.dome.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {
    
    public static boolean isServiceRunning(Context context, String className) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
        if (serviceList.isEmpty()) {
            return false;
        }
        for (RunningServiceInfo info : serviceList) {
            if (info.service.getClassName().equals(className)) {
                return true;
            }
        }
        return false;
    }
    
    public static void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }
    
    public static void showKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(v, InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }
    

    public static boolean isNumber(String number) {
        if (TextUtils.isEmpty(number)) {
            return false;
        }
        String strPattern = "^\\d+$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(number);
        return m.matches();
    }
    
    
    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {

        }
        return false;
    }

    public static void copyFileAssets(Context context, String fileName, String targetPath) {
        AssetManager assetManager = context.getAssets();
        InputStream in = null;
        OutputStream out = null;
        try {
            File dir = new File(targetPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            in = assetManager.open(fileName);
            File outFile = new File(targetPath, fileName);

            out = new FileOutputStream(outFile);
            copyFile(in, out);
            out.flush();
        } catch (Exception e) {
            Log.i("tag", "Failed to copy asset file: " + fileName, e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }
    
    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static Rect getScreenRect(Activity activity) {
        Rect r = new Rect();
        if (activity != null) {
            WindowManager wm = activity.getWindowManager();
            Display display = wm.getDefaultDisplay();
            if (Build.VERSION.SDK_INT > 12) {
                Point p = new Point();
                display.getSize(p);
                r.set(0, 0, p.x, p.y);
            } else {
                r.set(0, 0, display.getWidth(), display.getHeight());
            }
        }
        return r;
    }
    

    public static boolean checkApplication(Context context, String packageName) {
        if (packageName == null || "".equals(packageName)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(packageName,
                            PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
    
    public static boolean isActivityRunning(Context context, Class<?> clazz) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> appTasks = am.getRunningTasks(100);
            
        if (appTasks != null && !appTasks.isEmpty()) {
            for (RunningTaskInfo info : appTasks) {
                if (info.topActivity.getClassName().equals(clazz.getName())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
    
    /**
     * 强制打开GPS，高版本系统报错
     */
    @Deprecated
    public static final void openGPS(Context context) {
//        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
//        intent.putExtra("enabled", true);
//        context.sendBroadcast(intent);
//        String provider = Settings.Secure.getString(context.getContentResolver(), Secure.LOCATION_PROVIDERS_ALLOWED);
//        if (!provider.contains("gps")) { // if gps is disabled
//            final Intent poke = new Intent();
//            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
//            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
//            poke.setData(Uri.parse("3"));
//            context.sendBroadcast(poke);
//        }
    }
    
    /**
     * 用于�?掉惠锁屏
     */
    public static final void killBackgroundProcess(Context context) {
        if (context != null) {
            try {
                ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                am.killBackgroundProcesses("com.huaqian");
                am.killBackgroundProcesses("com.huaqian:remote");
                am.killBackgroundProcesses("com.huaqian:bdservice_v1");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    // 图片下载框架为UniversalImageLoader
    private static void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

    /**
     * 构建DisplayImageOptions对象，作为ImageLoader.getInstance().display()方法中的参数
     *
     * @param context
     * @param loading   加载时显示的图片
     * @param empty     加载前显示的默认图片
     * @param failed    加载失败显示的图�?
     * @param displayer 图片显示方式（普通，渐变显示，圆角矩形等�?
     * @return DisplayImageOptions对象
     */
    public static DisplayImageOptions buildDisplayImageOptions(Context context, Drawable loading, Drawable empty, Drawable failed, BitmapDisplayer displayer) {
        initImageLoader(context);
        return new DisplayImageOptions.Builder()
                .showImageOnLoading(loading)
                .showImageForEmptyUri(empty)
                .showImageOnFail(failed).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .displayer(displayer).build();
    }

    /**
     * 设置屏幕的背景明度，取范围为0.0f-1.0f
     * @param bgAlpha 透明
     */
    public static void setBackgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        activity.getWindow().setAttributes(lp);
    }


    //base64转为bitmap
    public static byte[] strToByteArray(String data) {
        return Base64.decode(data, Base64.DEFAULT);
    }



}
