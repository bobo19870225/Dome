package com.yy.dome.ui.activity.my;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yy.dome.R;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.UtilImags;
import com.yy.dome.util.ACache;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;
import com.yy.dome.widget.BottomDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HeadPortraitActivity extends BasenActivity<IView, MultiFunctionPresenter> implements IView, BottomDialog.OnBottomMenuItemClickListener {
    ACache aCache;
    @BindView(R.id.top_text)
    TextView topText;
    @BindView(R.id.my_fanhui)
    ImageView myFanhui;
    @BindView(R.id.my_top)
    TextView myTop;
    @BindView(R.id.my_right_img)
    ImageView myRightImg;
    @BindView(R.id.up_image)
    ImageView upImage;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;

    private Context context;

    BottomDialog bottomDialog;
    private Intent intent;

    @Override
    protected int setMainLayout() {
        return R.layout.activity_my_touxiang;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        aCache = ACache.get(this);
        context = this;
        myTop.setText("设置头像");
        bottomDialog = new BottomDialog(this, R.layout.item_popwindows, new int[]{R.id.item_popupwindows_camera, R.id.item_popupwindows_Photo, R.id.item_popupwindows_cancel});
        bottomDialog.setOnBottomMenuItemClickListener(this);
    }

    @Override
    protected void initBeforeData() {
        if (UtilFileDB.ISLOGINIMG(aCache)) {
            upImage.setImageBitmap(UtilFileDB.LOGINIMGBITMAP(aCache));
        } else {
            try {
                UtilImags.getImage(this, aCache, UtilFileDB.LOGINIMAGEURL(), upImage);
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void onLoadContributorStart() {
    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        JSONObject jsonobj;
        try {
            jsonobj = new JSONObject(topContributor);
            String errno = jsonobj.getString("errcode");
            String error = jsonobj.getString("errmsg");
            String data = jsonobj.getString("data");
            if (errno.equals("0") && error.equals("success")) {
                JSONObject jsonTow = new JSONObject(data);
                String headimg = jsonTow.getString("headimg");
                UtilImags.getImage(this, aCache, headimg, upImage);
                UtilFileDB.ADDSHAREDDATA("zqimage", headimg);
            } else {
                showToastShort(error);
            }
        } catch (JSONException e) {
            onError();
        }
    }


    @Override
    public void onNetWork() {
        showToastShort(R.string.no_network_error);
    }

    @Override
    public void onError() {
        showToastShort(R.string.error_no_data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void onBottomMenuItemClick(BottomDialog dialog, View view) {
        switch (view.getId()) {
            case R.id.item_popupwindows_camera:
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, 1);
                bottomDialog.dismiss();

                break;
            case R.id.item_popupwindows_Photo:
                Intent picture = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(picture, 2);
                bottomDialog.dismiss();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK
                && null != data) {
            String sdState = Environment.getExternalStorageState();
            if (!sdState.equals(Environment.MEDIA_MOUNTED)) {
                return;
            }
            new DateFormat();
            String name = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
            Bundle bundle = data.getExtras();
            Bitmap bmp = (Bitmap) bundle.get("data");
            FileOutputStream fout = null;
            String filename = null;
            try {
                filename = UtilImags.SHOWFILEURL(HeadPortraitActivity.this) + "/" + name;
            } catch (IOException e) {
            }
            try {
                fout = new FileOutputStream(filename);
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, fout);
            } catch (FileNotFoundException e) {
                showToastShort("上传失败");
            } finally {
                try {
                    fout.flush();
                    fout.close();
                } catch (IOException e) {
                    showToastShort("上传失败");
                }
            }
            //p.functionMultiRequestFile(context, Api.UPIMAGE(UtilFileDB.Tel(), UtilTools.MD5(UtilFileDB.Token()+UtilFileDB.Tel()+123),imageToBase64(String.valueOf(filename))), 1);

        }
        if (requestCode == 2 && resultCode == Activity.RESULT_OK
                && null != data) {
            try {
                Uri selectedImage = data.getData();
                String[] filePathColumns = {MediaStore.Images.Media.DATA};
                Cursor c = this.getContentResolver().query(selectedImage,
                        filePathColumns, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePathColumns[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
/*                p.functionMultiRequestFile(context, new File(picturePath), Api.showUpdateImageData(getString(R.string.appId), getString(R.string.appKey), UtilFileDB.currentTimeMillis(),
                        UtilFileDB.LOGINUID()), 1);*/

            } catch (Exception e) {
                showToastShort("上传失败");
            }
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getRepeatCount() == 0) {
                setResult(2, intent);
                finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @OnClick({R.id.fanhui_lay, R.id.my_right_img, R.id.up_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui_lay:
                setResult(2);
                finish();
                break;
            case R.id.my_right_img:
                bottomDialog.show();
                break;
            case R.id.up_image:
                break;
        }
    }
}
