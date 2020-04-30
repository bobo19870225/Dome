package com.yy.dome.ui.activity.my.wechatimg;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;


import com.yy.dome.tool.UtilImags;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraUtil {

    public static String getImageFromCamera(Context context, Intent data){
        String sdState = Environment.getExternalStorageState();
        if (!sdState.equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
        String name = sdf.format(new Date()) + ".jpg";
        if(data==null){
            return null;
        }
        Bundle bundle = data.getExtras();
        if(bundle==null){
            return null;
        }
        Bitmap bmp = (Bitmap) bundle.get("data");
        FileOutputStream fout = null;
        String filename;
        try {
            filename = UtilImags.SHOWFILEURL(context) + "/" + name;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
            fout = new FileOutputStream(filename);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fout);
        } catch (FileNotFoundException e) {

            //获取图片失败
            return null;
        } finally {
            try {
                fout.flush();
                fout.close();
            } catch (IOException e) {
                //获取图片失败
            }
        }
        return filename;
    }

    public static String getImageFromAlbum(Context context, Intent data){
        try {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = context.getContentResolver().query(selectedImage,
                    filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            return picturePath;
        } catch (Exception e) {
            return null;
        }
    }

    public interface BitmapCallBack{
        void path(String path);
    }
}
