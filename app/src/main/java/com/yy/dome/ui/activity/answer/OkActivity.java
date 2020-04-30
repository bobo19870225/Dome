package com.yy.dome.ui.activity.answer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.tool.SpiderView;
import com.yy.dome.ui.MainFragment;
import com.yy.dome.util.ActivityCollector;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 胡勇江 on 2020/2/18
 * Describe:
 */
public class OkActivity extends AppCompatActivity {

    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.top)
    TextView top;


    String stringType;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;
    @BindView(R.id.top_lift)
    TextView topLift;
    @BindView(R.id.cx_top_right)
    TextView cxTopRight;

    SpiderView spiderview;
    @BindView(R.id.webview_home)
    WebView webviewHome;
    @BindView(R.id.ok_lay)
    LinearLayout okLay;
    @BindView(R.id.ok_sview)
    ScrollView okSview;
    Context context;
    private LinkedList<Double> dataSeries  = new LinkedList<Double>();
    List<Integer> integerList = new ArrayList<>();
    //标签文字
    private List<String> mlables =new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ok_activity);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForWindow(OkActivity.this);
        top.setText("测试报告");
        stringType = getIntent().getStringExtra("stringType");
        context=this;
        text();
        show();

    }

    private void text() {
        try {
            int[] numberss = getIntent().getIntArrayExtra("nuu");
            for (int i = 0; i < numberss.length; i++) {
                double d2 = Double.valueOf(numberss[i]*5);
                dataSeries.add(d2);
            }
        }catch (Exception E){

        }

        spiderview = (SpiderView) findViewById(R.id.spiderview);
        spiderview.setDataList(dataSeries);



    }

    private void show() {

        WebSettings settings = webviewHome.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.supportMultipleWindows();
        settings.setAppCacheMaxSize(1024 * 1024 * 25);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setSavePassword(true);
        settings.setAppCacheEnabled(true);
        settings.setSaveFormData(true);
        settings.setJavaScriptEnabled(true);

        if (stringType.substring(0, stringType.indexOf("型")).equals("艺术")) {
            webviewHome.loadUrl(Api.URLs + "ideal.htm?method=articleManyPage&phone=18185153729&token=token&ids=[112]&title" + stringType.substring(0, stringType.indexOf("型"))+"型");
        }
        if (stringType.substring(0, stringType.indexOf("型")).equals("研究")) {
            webviewHome.loadUrl(Api.URLs + "ideal.htm?method=articleManyPage&phone=18185153729&token=token&ids=[111]&title=" + stringType.substring(0, stringType.indexOf("型"))+"型");
        }
        if (stringType.substring(0, stringType.indexOf("型")).equals("现实")) {
            webviewHome.loadUrl(Api.URLs + "ideal.htm?method=articleManyPage&phone=18185153729&token=token&ids=[110]&title=" + stringType.substring(0, stringType.indexOf("型"))+"型");
        }
        if (stringType.substring(0, stringType.indexOf("型")).equals("常规")) {
            webviewHome.loadUrl(Api.URLs + "ideal.htm?method=articleManyPage&phone=18185153729&token=token&ids=[109]&title=" + stringType.substring(0, stringType.indexOf("型"))+"型");
        }
        if (stringType.substring(0, stringType.indexOf("型")).equals("企业")) {
            webviewHome.loadUrl(Api.URLs + "ideal.htm?method=articleManyPage&phone=18185153729&token=token&ids=[108]&title=" + stringType.substring(0, stringType.indexOf("型"))+"型");
        }
        if (stringType.substring(0, stringType.indexOf("型")).equals("社会")) {
            webviewHome.loadUrl(Api.URLs + "ideal.htm?method=articleManyPage&phone=18185153729&token=token&ids=[107]&title=" + stringType.substring(0, stringType.indexOf("型"))+"型");
        }
    }

    @OnClick({ R.id.fanhui_lay, R.id.cx_top_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui_lay:
                startActivity(new Intent(context, MainFragment.class));
                break;
            case R.id.cx_top_right:
                dialogExit();
                break;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getRepeatCount() == 0) {
                ActivityCollector.finishAll();//销毁所有的活动
                startActivity(new Intent(context, MainFragment.class));

            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    private void dialogExit() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(OkActivity.this);
        dialog.setMessage("重新测试后你的结果将清空");
        dialog.setTitle("提示");
        dialog.setPositiveButton("继续测评", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                startActivity(new Intent(OkActivity.this, AnswerActivitys.class));
            }
        });
        dialog.setNegativeButton("确认放弃",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
        dialog.show();
    }
}
