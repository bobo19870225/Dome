package com.yy.dome.ui.activity.school;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.alibaba.fastjson.JSON;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.entity.Groupsinfo;
import com.yy.dome.entity.PrestigiousSchoolInfo;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.ui.MainFragment;
import com.yy.dome.ui.activity.home.MajorActivity;
import com.yy.dome.ui.activity.home.adapter.ExpandableListviewAdapter;
import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SchoolExpandableListView extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
    ACache aCache;
    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.top_right_text_school)
    TextView topRight;
    @BindView(R.id.expand_list_id)
    ExpandableListView expandListId;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;
    @BindView(R.id.tv_c)
    TextView tvC;
    @BindView(R.id.tv_w)
    TextView tvW;
    @BindView(R.id.tv_b)
    TextView tvB;
    @BindView(R.id.risk_text)
    TextView riskText;
    private List<Groupsinfo> mGroupTimeList = null;
    private ArrayList<ArrayList<PrestigiousSchoolInfo>> mItemNameList = new ArrayList<>();
    private Context context;
    private static Intent intent;
    private boolean isMenuOpen = false;

    private List<TextView> textViews = new ArrayList<>();

    @Override
    protected int setMainLayout() {
        return R.layout.expandable_list_view;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        aCache = ACache.get(this);
        context = this;
        StatusBarUtil.setTransparentForWindow(this);
        top.setText("院校智能填报");
        topRight.setText("重新生成");
        textViews.add(tvC);
        textViews.add(tvW);
        textViews.add(tvB);


    }

    @Override
    protected void initBeforeData() {

        try {
            p.functionMultiRequests(Api.URLs + "ideal.htm?", context, Api.SCHOOLITEM(UtilFileDB.Tel(),
                    UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123)), 1);
        } catch (Exception e) {

        }

    }


    @Override
    public void onLoadContributorStart() {
    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        Log.e("ssss", topContributor);
        if (position == 1) {
            try {
                List<PrestigiousSchoolInfo> aaaList = JSON.parseArray(topContributor, PrestigiousSchoolInfo.class);
                ArrayList<PrestigiousSchoolInfo> ab = new ArrayList();//本科1
                ArrayList<PrestigiousSchoolInfo> abc = new ArrayList();//本科2
                //遍历数组
                for (int i = 0; i < aaaList.size(); i++) {
                    if (aaaList.get(i).getBatch().equals("1")) {
                        ab.add(aaaList.get(i));
                    } else {
                        abc.add(aaaList.get(i));
                    }
                }
                mGroupTimeList = new ArrayList();
                if(ab.size()>0){//本一
                    mGroupTimeList.add(new Groupsinfo("1", "本科第一批"));
                    mItemNameList.add(ab);
                }
                if(abc.size()>0){//本一
                    mGroupTimeList.add(new Groupsinfo("2", "本科第二批"));
                    mItemNameList.add(abc);
                }


                initview();
            } catch (Exception e) {

            }
        }
        if (position == 2) {
            JSONObject jsonobjs;
            try {
                jsonobjs = new JSONObject(topContributor);
                String error = jsonobjs.getString("ret");
                if (error.equals("0")) {
                    showToastShort("重置成功");
                    startActivity(new Intent(context, MajorActivity.class));
                } else {
                    showToastShort(error);
                }

            } catch (JSONException e) {
                onError();
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

    }



    private void initview() {

        ExpandableListviewAdapter adapter = new ExpandableListviewAdapter(this, mGroupTimeList, mItemNameList);

        expandListId.setAdapter(adapter);
        //展开所有
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            expandListId.expandGroup(i);
        }


        //默认展开第一个数组
        //expandListId.expandGroup(0);
        //关闭数组某个数组，可以通过该属性来实现全部展开和只展开一个列表功能
        //expand_list_id.collapseGroup(0);


        expandListId.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                return false;
            }
        });
        //子视图的点击事件
        expandListId.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                intent = new Intent(context, SchoolDetailsActivity.class);
                intent.putExtra("id", mItemNameList.get(groupPosition).get(childPosition).getId());
                intent.putExtra("code", mItemNameList.get(groupPosition).get(childPosition).getCode());
                intent.putExtra("year", mItemNameList.get(groupPosition).get(childPosition).getYear());
                intent.putExtra("name", mItemNameList.get(groupPosition).get(childPosition).getSchoolName());
                intent.putExtra("city", mItemNameList.get(groupPosition).get(childPosition).getCity());
                intent.putExtra("image", mItemNameList.get(groupPosition).get(childPosition).getLogo());
                intent.putExtra("type", mItemNameList.get(groupPosition).get(childPosition).getType1());
                intent.putExtra("is211", mItemNameList.get(groupPosition).get(childPosition).getIs211());
                intent.putExtra("is985", mItemNameList.get(groupPosition).get(childPosition).getIs985());
                intent.putExtra("createTime", mItemNameList.get(groupPosition).get(childPosition).getCreateTime());
                intent.putExtra("pertain", mItemNameList.get(groupPosition).get(childPosition).getPertain());
                intent.putExtra("schoolcode", mItemNameList.get(groupPosition).get(childPosition).getSchoolCode());
                intent.putExtra("hotRank", mItemNameList.get(groupPosition).get(childPosition).getHotRank());
                UtilFileDB.ADDSHAREDDATA("schoolid", mItemNameList.get(groupPosition).get(childPosition).getSchoolId());
                UtilFileDB.ADDSHAREDDATA("schoolminscore", mItemNameList.get(groupPosition).get(childPosition).getMinScore());
                UtilFileDB.ADDSHAREDDATA("schoolmaxscore", mItemNameList.get(groupPosition).get(childPosition).getMaxScore());
                UtilFileDB.ADDSHAREDDATA("enrollratio", String.valueOf(mItemNameList.get(groupPosition).get(childPosition).getEnrollRatio()));
                UtilFileDB.ADDSHAREDDATA("branch", String.valueOf(mItemNameList.get(groupPosition).get(childPosition).getBranch()));
                UtilFileDB.ADDSHAREDDATA("schoolname", String.valueOf(mItemNameList.get(groupPosition).get(childPosition).getName()));
                startActivity(intent);
                return true;
            }
        });
        //用于当组项折叠时的通知。
        expandListId.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });
        //
        //用于当组项折叠时的通知。
        expandListId.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {


            }
        });
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getRepeatCount() == 0) {
                intent = new Intent(context, MainFragment.class);
                startActivity(intent);
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    private void dialogExit() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(SchoolExpandableListView.this);
        dialog.setMessage("重新生成后你的志愿表将会清空");
        dialog.setTitle("提示");
        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                try {
                    p.functionMultiRequest(Api.URLs + "ideal.htm?", context, Api.CZZYB(UtilFileDB.Tel(), UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123)), 2);
                } catch (Exception e) {

                }
            }
        });
        dialog.setNegativeButton("放弃",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
        dialog.show();
    }


    //打开扇形菜单的属性动画， dp为半径长度
    private void showOpenAnim(int dp) {
        tvC.setVisibility(View.VISIBLE);
        tvW.setVisibility(View.VISIBLE);
        tvB.setVisibility(View.VISIBLE);


        //for循环来开始小图标的出现动画
        for (int i = 0; i < textViews.size(); i++) {
            AnimatorSet set = new AnimatorSet();
            //标题1与x轴负方向角度为20°，标题2为100°，转换为弧度
            double a = -Math.cos(20 * Math.PI / 180 * (i * 2 + 1));
            double b = -Math.sin(20 * Math.PI / 180 * (i * 2 + 1));
            double x = a * dip2px(dp);
            double y = b * dip2px(dp);

            set.playTogether(
                    ObjectAnimator.ofFloat(textViews.get(i), "translationX", (float) (x * 0.25), (float) x),
                    ObjectAnimator.ofFloat(textViews.get(i), "translationY", (float) (y * 0.25), (float) y),
                    ObjectAnimator.ofFloat(textViews.get(i), "alpha", 0, 1).setDuration(2000)
            );
            set.setInterpolator(new BounceInterpolator());
            set.setDuration(500).setStartDelay(100);
            set.start();

            set.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {

                    //菜单状态置打开
                    isMenuOpen = true;
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }

        //转动加号大图标本身45°
        ObjectAnimator rotate = ObjectAnimator.ofFloat(riskText, "rotation", 0, 0).setDuration(300);
        rotate.setInterpolator(new BounceInterpolator());
        rotate.start();

    }

    //关闭扇形菜单的属性动画，参数与打开时相反
    private void showCloseAnim(int dp) {


        //for循环来开始小图标的出现动画
        for (int i = 0; i < textViews.size(); i++) {
            AnimatorSet set = new AnimatorSet();
            double a = -Math.cos(20 * Math.PI / 180 * (i * 2 + 1));
            double b = -Math.sin(20 * Math.PI / 180 * (i * 2 + 1));
            double x = a * dip2px(dp);
            double y = b * dip2px(dp);

            set.playTogether(
                    ObjectAnimator.ofFloat(textViews.get(i), "translationX", (float) x, (float) (x * 0.25)),
                    ObjectAnimator.ofFloat(textViews.get(i), "translationY", (float) y, (float) (y * 0.25)),
                    ObjectAnimator.ofFloat(textViews.get(i), "alpha", 1, 0).setDuration(2000)
            );
//      set.setInterpolator(new AccelerateInterpolator());
            set.setDuration(500);
            set.start();

            set.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {

                    tvC.setVisibility(View.GONE);
                    tvW.setVisibility(View.GONE);
                    tvB.setVisibility(View.GONE);


                    //菜单状态置关闭
                    isMenuOpen = false;
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }


        //转动加号大图标本身45°
        ObjectAnimator rotate = ObjectAnimator.ofFloat(riskText, "rotation", 0, 0).setDuration(300);
        rotate.setInterpolator(new BounceInterpolator());
        rotate.start();


    }

    private int dip2px(int value) {
        float density = getResources()
                .getDisplayMetrics().density;
        return (int) (density * value + 0.5f);
    }

    @OnClick({R.id.tv_c, R.id.tv_w, R.id.tv_b, R.id.risk_text,R.id.fanhui_lay, R.id.top_right_text_school})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_c:
                Toast.makeText(context,"冲",Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_w:
                Toast.makeText(context,"稳",Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_b:
                Toast.makeText(context,"保",Toast.LENGTH_LONG).show();
                break;
            case R.id.risk_text:

/*                if (!isMenuOpen) {
                    showOpenAnim(80);
                    riskText.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.border_c));
                } else {
                    showCloseAnim(80);
                    riskText.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.border_b));
                }*/
                break;
            case R.id.fanhui_lay:
                intent = new Intent(context, MainFragment.class);
                startActivity(intent);
                break;
            case R.id.top_right_text_school:
                dialogExit();
                break;
        }
    }
}
