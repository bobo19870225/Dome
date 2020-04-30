package com.yy.dome.ui.activity.my;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.UtilImags;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.ui.MainFragment;
import com.yy.dome.ui.activity.details.DisclaimerActivity;
import com.yy.dome.ui.activity.details.PrivacyPolicyActivity;
import com.yy.dome.ui.activity.my.item.ActivityAlterName;
import com.yy.dome.ui.activity.my.item.ActivityXiuGaiPwd;
import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;
import com.yy.dome.widget.ZQRoundOvalImageView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class SettingActivity extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
    ACache aCache;
    @BindView(R.id.public_img_close)
    ImageView publicImgClose;
    @BindView(R.id.top_title)
    TextView topTitle;
    @BindView(R.id.zhanghao)
    TextView zhanghao;
    @BindView(R.id.my_name)
    TextView myTel;
    @BindView(R.id.bangdin_email)
    ImageView bangdinEmail;
    @BindView(R.id.main_r2)
    RelativeLayout mainR2;
    @BindView(R.id.my_pwd)
    TextView myPwd;
    @BindView(R.id.act_member_pwd)
    ImageView actMemberPwd;
    @BindView(R.id.main_r4)
    RelativeLayout mainR4;
    @BindView(R.id.sex)
    ImageView sex;
    @BindView(R.id.main_r5)
    RelativeLayout mainR5;
    @BindView(R.id.main_r6)
    RelativeLayout mainR6;
    @BindView(R.id.my_sign_out)
    TextView mySignOut;
    Context context;
    Intent intent;

    LinearLayout ll_popup;
    PopupWindow pop;
    @BindView(R.id.huiyuan_name)
    TextView huiyuanName;
    @BindView(R.id.my_touxiang)
    ZQRoundOvalImageView myTouxiang;
    @BindView(R.id.ss)
    ImageView ss;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;

    @Override
    protected int setMainLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        aCache = ACache.get(this);
        context = this;
    }

    @Override
    protected void initBeforeData() {
        StatusBarUtil.setTransparentForWindow(this);
        try {
            UtilImags.getImage(context, aCache, Api.URLs + "files/dfHeadImg/defaultHead.jpg", myTouxiang);
            myTel.setText(UtilFileDB.uname());

        } catch (Exception e) {

        }

    }

    @Override
    public void onLoadContributorStart() {

        try {
            if (UtilFileDB.ISLOGIN()){
                intent = new Intent(context, LoginActivity.class);
                startActivityForResult(intent, 1);
            }else {
                intent = new Intent(context, MainFragment.class);
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        if (position == 1) {
            JSONObject jsonobjs;
            try {
                jsonobjs = new JSONObject(topContributor);
                String errno = jsonobjs.getString("headImg");
                Log.e("errno",errno);
                //myTouxiang.setImageBitmap(errno);
                myTouxiang.setImageBitmap(UtilFileDB.LOGINIMGBITMAP(aCache));

            } catch (JSONException e) {

            }
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


    @OnClick({R.id.fanhui_lay, R.id.main_r2, R.id.main_r4, R.id.main_r5, R.id.main_r6, R.id.my_sign_out, R.id.main_r1_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui_lay:
                setResult(6);
                finish();
                break;
            case R.id.main_r2:
                intent = new Intent(SettingActivity.this, ActivityAlterName.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.main_r4:
                openActivity(ActivityXiuGaiPwd.class);
                break;
            case R.id.main_r5:
                openActivity(DisclaimerActivity.class);
                break;
            case R.id.main_r6:
                openActivity(PrivacyPolicyActivity.class);
                break;
            case R.id.my_sign_out:
                showDataRemove();
                break;
            case R.id.main_r1_img:
/*
                intent = new Intent(SettingActivity.this, HeadPortraitActivity.class);
                startActivityForResult(intent, 1);
*/
/*                InitImg();
                ll_popup.startAnimation(AnimationUtils.loadAnimation(
                        context, R.anim.activity_translate_in));
                pop.showAtLocation(view, Gravity.LEFT, 0, 0);*/

                break;
        }
    }

    /**
     * 清除信息 退出
     */
    private void showDataRemove() {
        UtilFileDB.DELETESHAREDDATA("zqname");
        UtilFileDB.DELETESHAREDDATA("useruid");
        UtilFileDB.DELETESHAREDDATA("tel");
        UtilFileDB.DELETESHAREDDATA("score");
        setResult(3);
        openActivity(LoginActivity.class);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 2) {
                //myTouxiang.setImageBitmap(UtilFileDB.LOGINIMGBITMAP(aCache));
            } else if (resultCode == 3) {

                myTel.setText(UtilFileDB.uname());
            }
        }

        if (requestCode == 1 && resultCode == Activity.RESULT_OK
                && null != data) {
            String sdState = Environment.getExternalStorageState();
            if (!sdState.equals(Environment.MEDIA_MOUNTED)) {
                return;
            }
            new DateFormat();
            String name = DateFormat.format("yyyyMMdd_hhmmss",
                    Calendar.getInstance(Locale.CHINA)) + ".jpg";
            Bundle bundle = data.getExtras();
            // 获取相机返回的数据，并转换为图片格式
            Bitmap bmp = (Bitmap) bundle.get("data");
            FileOutputStream fout = null;
            String filename = null;
            try {
                filename = UtilImags.SHOWFILEURL(context) + "/" + name;
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
            myTouxiang.setImageBitmap(bmp);
            showOkHttp(new File((filename)));
            Log.e("image2", imageToBase64(filename));

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

                Bitmap bmp = BitmapFactory.decodeFile(picturePath);
                // 获取图片并显示
                myTouxiang.setImageBitmap(bmp);
                saveBitmapFile(UtilImags.compressScale(bmp), UtilImags.SHOWFILEURL(context) + "/stscname.jpg");
                showOkHttp(new File(UtilImags.SHOWFILEURL(context) + "/stscname.jpg"));
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
                setResult(6, intent);
                finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    private void showOkHttp(File file) {
        postFile(file);
    }

    public void postFile(File file) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("method", "updateScore");
        params.put("phone", UtilFileDB.Tel());
        params.put("token", UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123));
        params.put("headImg", imageToBase64(String.valueOf(file)));
        Log.e("image1", imageToBase64(String.valueOf(file)));
        Log.e("image2", (String.valueOf(file)));
        OkHttpUtils.post().addFile("filedata", "stscname.jpg", file).url(Api.URLs).params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("data1", response);
                    }
                });
    }

    /****
     * 头像提示框
     */
    public void InitImg() {
        pop = new PopupWindow(context);
        View view = getLayoutInflater().inflate(R.layout.item_popwindows,
                null);
        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        TextView bt1 = (TextView) view.findViewById(R.id.item_popupwindows_camera);
        TextView bt2 = (TextView) view.findViewById(R.id.item_popupwindows_Photo);
        TextView bt3 = (TextView) view.findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, 1);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent picture = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(picture, 2);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
    }


    public void saveBitmapFile(Bitmap bitmap, String path) {
        File file = new File(path);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 将图片转换成Base64编码的字符串
     */
    public static String imageToBase64(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try {
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }


    private Bitmap byteBitmap(byte[] data) {
        try {
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);  //生成位图
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "图片转换失败！", Toast.LENGTH_LONG).show();
        }
        return null;
    }


}