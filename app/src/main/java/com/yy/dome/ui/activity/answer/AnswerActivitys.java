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
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.yy.dome.R;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnswerActivitys extends AppCompatActivity  {


    //适配器
    private FancyCoverFlowSampleAdapter adapter;

    private FancyCoverFlow fancyCoverFlow;

    Map<Integer, String> map = new HashMap<>();

    int answerCount = 0; //总数量
    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;

    private int xsCount = 0; //现实

    private int yjCount = 0; //研究

    private int ysCount = 0;  //艺术

    private int shCount = 0; //社会

    private int qyCount = 0; //企业

    private int cgCount = 0; //常规

    List<Integer> integerList = new ArrayList<>();

    String stringType = "";//得到类型结果
    Context context;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_activity);
        ButterKnife.bind(this);

        top.setText("霍兰德兴趣测评");
        StatusBarUtil.setTransparentForWindow(AnswerActivitys.this);
        context = this;
        initView();

    }

    private void initView() {

        fancyCoverFlow = (FancyCoverFlow) findViewById(R.id.main_gallery);
        fancyCoverFlow.dp2px();// child间距
        String[] strings = getResources().getStringArray(R.array.app_answer);
        answerCount = strings.length;

        adapter = new FancyCoverFlowSampleAdapter(this, strings);
        fancyCoverFlow.setAdapter(adapter);

        adapter.setOnItemClickListenter(new CallBackInterface() {
            @Override
            public void callBackClick(View view, int position) {
              /*  if (view.getId() == R.id.tv_fin) {
                    if (position != 0) {
                        fancyCoverFlow.onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
                    }
                } else {
                    if (position == answerCount - 1) {
                        //完成测试
                        showCalculation();
                        return;
                    }
                    if (view.getId() == R.id.tv1) {
                        map.put(position + 1, "1");

                    }
                    fancyCoverFlow.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
                }*/
                if (position == answerCount - 1) {
                    //完成测试
                    showCalculation();
                    return;
                }
                if (view.getId() == R.id.tv1) {
                    map.put(position + 1, "1");

                }
                fancyCoverFlow.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);

            }
            
        });


    }



    private void showCalculation() {
        for (Integer key : map.keySet()) {
            if (key == 1 || key == 7 || key == 13 || key == 19 || key == 25 || key == 31 || key == 37 || key == 43 || key == 49 ||
                    key == 55 || key == 61 || key == 67 || key == 73 || key == 79 || key == 85) {
                xsCount++;
                Log.e("现实型", String.valueOf(xsCount));
            } else if (key == 2 || key == 8 || key == 14 || key == 20 || key == 26 || key == 32 || key == 38 || key == 44 || key == 50 ||
                    key == 56 || key == 62 || key == 68 || key == 74 || key == 80 || key == 86) {
                yjCount++;
                Log.e("研究型", String.valueOf(yjCount));
            } else if (key == 3 || key == 9 || key == 15 || key == 21 || key == 27 || key == 33 || key == 39 || key == 45 || key == 51 ||
                    key == 57 || key == 63 || key == 69 || key == 75 || key == 81 || key == 87) {
                ysCount++;
                Log.e("艺术型", String.valueOf(ysCount));
            } else if (key == 4 || key == 10 || key == 16 || key == 22 || key == 28 || key == 34 || key == 40 || key == 46 || key == 52 ||
                    key == 58 || key == 64 || key == 70 || key == 76 || key == 82 || key == 88) {
                shCount++;
                Log.e("社会型", String.valueOf(shCount));
            } else if (key == 5 || key == 11 || key == 17 || key == 23 || key == 29 || key == 35 || key == 41 || key == 47 || key == 53 ||
                    key == 59 || key == 65 || key == 71 || key == 77 || key == 83 || key == 89) {
                qyCount++;
                Log.e("企业型", String.valueOf(qyCount));
            } else if (key == 6 || key == 12 || key == 18 || key == 24 || key == 30 || key == 36 || key == 42 || key == 48 || key == 54 ||
                    key == 60 || key == 66 || key == 72 || key == 78 || key == 84 || key == 90) {
                cgCount++;
                Log.e("常规型", String.valueOf(cgCount));
            }
        }

        Integer[] numbers = {xsCount, yjCount, ysCount, shCount, qyCount, cgCount};
        UtilFileDB.ADDSHAREDDATA("xsCount",String.valueOf(xsCount));
        UtilFileDB.ADDSHAREDDATA("yjCount",String.valueOf(yjCount));
        UtilFileDB.ADDSHAREDDATA("ysCount",String.valueOf(ysCount));
        UtilFileDB.ADDSHAREDDATA("shCount",String.valueOf(shCount));
        UtilFileDB.ADDSHAREDDATA("qyCount",String.valueOf(qyCount));
        UtilFileDB.ADDSHAREDDATA("cgCount",String.valueOf(cgCount));


        int numMax = Collections.max(Arrays.asList(numbers));
        int[] numbersnn = {xsCount, yjCount, ysCount, shCount, qyCount, cgCount};

        for (int i = 0; i < numbers.length; i++) {
            if (numMax == numbers[i]) {
                integerList.add(i);
            }
        }

        for (int i = 0; i < integerList.size(); i++) {
            if (integerList.get(i) == 0) {
                stringType += "现实型";
            } else if (integerList.get(i) == 1) {
                stringType += "研究型";
            } else if (integerList.get(i) == 2) {
                stringType += "艺术型";
            } else if (integerList.get(i) == 3) {
                stringType += "社会型";
            } else if (integerList.get(i) == 4) {
                stringType += "企业型";
            } else if (integerList.get(i) == 5) {
                stringType += "常规型";
            }
        }
        Log.i("aaa", stringType);
        Intent intent = new Intent(AnswerActivitys.this, OkActivity.class);
        intent.putExtra("stringType", stringType);
        UtilFileDB.ADDSHAREDDATA("stringType", stringType);
        UtilFileDB.ADDSHAREDDATA("nuu",String.valueOf(numbersnn) );
        intent.putExtra("nuu",numbersnn);
        startActivity(intent);
    }




    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getRepeatCount() == 0) {
                setResult(1);
                dialogExit();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    private void dialogExit() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(AnswerActivitys.this);
        dialog.setMessage("请一次性完成此测评如果放弃，下次需要重新开始");
        dialog.setTitle("提示");
        dialog.setPositiveButton("继续测评", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("确认放弃",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        dialog.show();
    }

    @OnClick(R.id.fanhui_lay)
    public void onViewClicked() {
     finish();
    }


}