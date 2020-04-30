package com.yy.dome.ui.activity.answer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.yy.dome.R;
import com.yy.dome.util.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnswerActivity extends Activity {
    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.start)
    TextView start;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;
    @BindView(R.id.top_lift)
    TextView topLift;
    @BindView(R.id.altr_top_lay)
    LinearLayout altrTopLay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evalution_s);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForWindow(this);
    }

    @OnClick({R.id.fanhui_lay, R.id.start,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui_lay:
                finish();
                break;
            case R.id.start:
                Intent intent = new Intent(AnswerActivity.this, AnswerActivitys.class);
                startActivity(intent);
                break;
        }
    }

}
