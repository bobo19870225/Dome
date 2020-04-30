package com.yy.dome.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.donkingliang.banner.CustomBanner;
import com.yy.dome.R;
import com.yy.dome.adapter.FragmentPage;
import com.yy.dome.adapter.HorizontalListViewAdapter;
import com.yy.dome.adapter.ListviewAdp;
import com.yy.dome.adapter.OnItemClickListenter;
import com.yy.dome.adapter.RecycAdapter;
import com.yy.dome.adapter.ViewPagerAdapter;
import com.yy.dome.api.Api;
import com.yy.dome.api.ConvertUtil;
import com.yy.dome.api.IUtilDBRequest;
import com.yy.dome.base.BasenFragment;
import com.yy.dome.entity.HomeSchoolInfo;
import com.yy.dome.entity.VersionInfo;
import com.yy.dome.entity.home.ArticleInfo;
import com.yy.dome.js.BridgeWebView;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.ui.activity.details.ArticleDetails;
import com.yy.dome.ui.activity.home.video.HomeVideoContentActivity;
import com.yy.dome.ui.activity.home.video.VideoInfo;
import com.yy.dome.ui.activity.my.LoginActivity;
import com.yy.dome.ui.activity.my.MyScore;
import com.yy.dome.ui.activity.school.GoSchoolActivity;
import com.yy.dome.ui.activity.school.SchoolDetails;
import com.yy.dome.util.ACache;
import com.yy.dome.util.ControlScrollViewPager;
import com.yy.dome.util.EventBusUtil;
import com.yy.dome.util.GlideRoundTransform;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.utils.UpdateManager;
import com.yy.dome.utils.UpdateVersionController;
import com.yy.dome.view.IView;
import com.yy.dome.widget.CenterDialog;
import com.yy.dome.widget.NoScrollGridView;
import com.yy.dome.widget.XListView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeFragment extends BasenFragment<IView, MultiFunctionPresenter> implements IView,
        CenterDialog.OnCenterItemClickListener, XListView.IXListViewListener {
    @BindView(R.id.middle_viewpager)
    ControlScrollViewPager middleViewpager;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.text_shu)
    TextView textShu;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.image_right)
    ImageView imageRight;
    @BindView(R.id.ry_main1)
    RelativeLayout ryMain1;
    @BindView(R.id.grid_view)
    NoScrollGridView gridView;
    @BindView(R.id.listview_home)
    XListView listviewHome;
    @BindView(R.id.webview_home)
    BridgeWebView webviewHome;
    @BindView(R.id.home_list_video)
    RecyclerView homeListVideo;
    @BindView(R.id.update_score)
    LinearLayout updateScore;

    private List<ArticleInfo.Data> datas = new ArrayList<>();//总的数据源
    Intent intent;
    Context context;

    RecycAdapter recycAdapter;
    private List<ArticleInfo.Data> lists;//总的数据源
    ListviewAdp listviewAdp;
    UpdateManager mUpdateManager;
    ACache aCache;
    private Random mRandom;
    private String url = IUtilDBRequest.URL + "&method=circularPage" + "&phone=" + UtilFileDB.Tel() + "&token=" + UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123);
    String score = "";
    private View view;
    String qh = "清华大学(TsinghuaUniversity),简称“清华”,由中华人民共和国教育部直属,中央直管副部级建制,位列“211工程”、“985工程”、“世界一流大学和一流学科”,入选“基础学科拔尖学生培养试验计划”、“高等学校创新能力提升计划”、“高等学校学科创新引智计划”,为九校联盟、中国大学校长联谊会、东亚研究型大学协会、亚洲大学联盟、环太平洋大学联盟、清华—剑桥—MIT低碳大学联盟成员,被誉为“红色工程师的摇篮”.清华大学的前身清华学堂始建于1911年,因水木清华而得名,是清政府设立的留美预备学校,其建校的资金源于1908年美国退还的部分庚子赔款.1912年更名为清华学校.1928年更名为国立清华大学.1937年抗日战争全面爆发后南迁长沙,与北京大学、南开大学组建国立长沙临时大学,1938年迁至昆明改名为国立西南联合大学.1946年迁回清华园.1949年中华人民共和国成立,清华大学进入了新的发展阶段.1952年全国高等学校院系调整后成为多科性工业大学.1978年以来逐步恢复和发展为综合性的研究型大学.水木清华,钟灵毓秀,清华大学秉持“自强不息、厚德载物”的校训和“行胜于言”的校风,坚持“中西融汇、古今贯通、文理渗透”的办学风格和“又红又专、全面发展”的培养特色,弘扬“爱国奉献、追求卓越”传统和“人文日新”精神.恰如清华园工字厅内对联所书——“槛外山光,历春夏秋冬、万千变幻,都非凡境；窗中云影,任东西南北、去来澹荡,洵是仙居”.";
    String bd = "北京大学(PekingUniversity)简称“北大”,诞生于1898年,初名京师大学堂,是中国近代第一所国立大学,也是最早以“大学”之名创办的学校,其成立标志着中国近代高等教育的开端.北大是中国近代以来唯一以国家最高学府身份创立的学校,最初也是国家最高教育行政机关,行使教育部职能,统管全国教育.北大催生了中国最早的现代学制,开创了中国最早的文科、理科、社科、农科、医科等大学学科,是近代以来中国高等教育的奠基者 .1912年5月3日,京师大学堂改称北京大学校,严复为首任校长 .1917年,蔡元培出任校长,“循思想自由原则、取兼容并包之义”,把北大办成全国学术和思想中心,使北大成为新文化运动中心、五四运动策源地.1937年抗日战争爆发,北大与清华大学、南开大学南迁长沙,组成国立长沙临时大学.不久迁往昆明,改称国立西南联合大学.1946年10月在北平复学 .北大由教育部直属,中央直管副部级建制,是国家双一流 、211工程、985工程 、2011计划重点建设的全国重点大学,九校联盟 、中国大学校长联谊会、京港大学联盟 、亚洲大学联盟 、东亚研究型大学协会、国际研究型大学联盟、环太平洋大学联盟、东亚四大学论坛、国际公立大学论坛、中俄综合性大学联盟重要成员.北大始终与国家民族的命运紧密相连,聚集了许多学者专家,培养了众多优秀人才,创造了大批重大科学成果,影响和推动了中国近现代思想理论、科学技术、文化教育和社会发展的进程.";
    String gd = "贵州大学(GuizhouUniversity),简称“贵大”,创始于1902年,位于中国贵州省贵阳市,是国家“双一流”世界一流学科建设高校 ,是教育部与贵州省人民政府合作共建的国家“211工程”重点大学、贵州省属重点综合性大学,是“卓越法律人才教育培养计划”“卓越农林人才教育培养计划”和“卓越工程师教育培养计划”重点建设大学 ,是教育部来华留学示范基地、教育部教育援外基地、科技部国际科技合作基地、中国政府奖学金来华留学生接收院校、孔子学院奖学金院校,2012年成为教育部“2011计划”协同创新中心牵头高校,入选教育部“中西部高校综合实力提升工程”成为教育部在西部地区重点建设的14所高水平大学之一,“中西部高校联盟”成员,中国最早创办的大学之一.由浙江大学对口支援贵州大学.贵州大学历经贵州大学堂、省立贵州大学、国立贵州农工学院、国立贵州大学等时期,1950年10月定名为贵州大学.1997年8月,与贵州农学院等院校合并.2004年8月,与贵州工业大学合并.2012年12月,成为教育部与贵州省人民政府共建高校 .2016年4月.被列为国家“一省一校”重点支持建设大学.2017年11月荣获第一届全国文明校园称号.截至2018年3月,学校占地面积33833.29亩；下设40个学院,在校全日制本科生31417人,全日制研究生8716人；有教职工3958人,其中,专任教师2842人,教授490人、副教授1121人,具有博士学位902人、硕士学位876人；图书馆馆藏纸质文献368万余册,中外文电子图书210万余万册.";
    String hz = "华中科技大学(HuazhongUniversityofScienceandTechnology)位于湖北省武汉市,学校前身是1952年创办的四大工学院之一的华中工学院、1907年建立的上海德文医学堂和1898年清朝政府建立的湖北工艺学堂.历经传承与发展,2000年由华中理工大学,同济医科大学,武汉城市建设学院合并成立.华中科技大学是中华人民共和国教育部直属的综合性研究型全国重点大学,中央直管副部级高校,是著名的七校联合办学高校之一,是国家首批“双一流”、“985工程”、“211工程”、“2011计划”重点建设高校,“卓越工程师教育培养计划”、“卓越医生教育培养计划”、“111计划”、“海外高层次人才引进计划”、“国家建设高水平大学公派研究生项目”、“教育部来华留学示范基地”入选高校,同时是21世纪学术联盟、中俄工科大学联盟、中欧工程教育平台、七校联合办学、国家海外高层次人才创新创业基地成员,是与国家卫生和计划生育委员会共建医学院的十所院校之一,是拥有国家实验室和国家大科学中心的四所大学之一,是与清华大学一同被美国制造工程师协会(SME)授予“大学领先奖”的两所中国大学之一.入选《Nature》评出的“中国十大科研机构”,被称作“新中国高等教育发展的缩影”.截至2018年3月,学校占地7000余亩；有45个学院,开设98个本科专业,现有专任教师3000余人,其中正副教授2300余人；有214个硕士学位授权点,183个博士学位授权点,39个博士后科研流动站. 设有8所附属三级甲等医院.";
    String wh = "武汉大学(WuhanUniversity),简称“武大”,是一所中国著名的综合研究型大学,也是近代中国建立最早的国立大学.1893年,湖广总督张之洞上奏清政府设立自强学堂片,由此揭开了近代中国高等教育的序幕.1896年更名方言学堂,1913年为六大国立高师之一的国立武昌高等师范学校,1926年更名国立武昌中山大学,1928年定名国立武汉大学,是民国四大名校之一.1949年更为现名.至今已有125年办学历史.武汉大学是中国教育部直属的副部级全国重点大学,国家首批“双一流”A类、“985工程”、“211工程”、“2011计划”重点建设高校,同时是“111计划”、“珠峰计划”、“千人计划”、“卓越计划”等重点建设的中国顶尖名牌大学,是与法国高校联系最紧密、合作最广泛的中国高校,是世界权威期刊《Science》列出的“中国最杰出的大学”之一.武汉大学是中国著名的风景游览地,为国家5A级旅游景区东湖风景区的组成部分.学校坐拥珞珈山,环绕东湖水,占地面积5195亩,建筑面积266万平方米.作为中国建立最早的国立大学,其中西合璧的宫殿式建筑群古朴典雅,巍峨壮观,为近现代中国大学校园建筑的佳作与典范,被称为中国最美丽的大学校园.武大樱花约在每年三月下旬开放.截至2017年,武汉大学16个学科领域进入ESI排行世界前1%,其中遥感科学与技术学科名列世界第一.在教育部第三轮学科评估中,武汉大学4个一级学科排名全国第一；9个学科排名全国前三,14个学科排名全国前五,23个学科排名全国前十.";
    String fd = "复旦大学(FudanUniversity),简称“复旦”,位于中国上海,由中华人民共和国教育部直属,中央直管副部级建制,位列985工程、211工程、双一流A类,入选“珠峰计划”、“111计划”、“2011计划”、“卓越医生教育培养计划”,为“九校联盟”(C9)、中国大学校长联谊会、东亚研究型大学协会、环太平洋大学协会的重要成员,是一所世界知名、国内顶尖的全国重点大学.复旦大学创建于1905年,原名复旦公学,是中国人自主创办的第一所高等院校,创始人为中国近代知名教育家马相伯,首任校董为国父孙中山.校名“复旦”二字选自《尚书大传·虞夏传》名句“日月光华,旦复旦兮”,意在自强不息,寄托当时中国知识分子自主办学、教育强国的希望.1917年复旦公学改名为私立复旦大学；1937年抗战爆发后,学校内迁重庆北碚,并于1941年改为“国立”；1946年迁回上海江湾原址；1952年全国高等学校院系调整后,复旦大学成为以文理科为基础的综合性大学；1959年成为全国重点大学.2000年,原复旦大学与原上海医科大学合并成新的复旦大学.截至2017年5月,学校占地面积244.99万平方米,建筑面积200.20万平方米.复旦师生谨记“博学而笃志,切问而近思”的校训,严守“文明、健康、团结、奋发”的校风,力行“刻苦、严谨、求实、创新”的学风,发扬“爱国奉献、学术独立、海纳百川、追求卓越”的复旦精神,以服务国家为己任,以培养人才为根本,以改革开放为动力,为实现中国梦作出新贡献.";
    CenterDialog centerDialog;
    private ViewFlipper viewfli;
    Handler mHandler;
    int page = 0;
    private CustomBanner<String> mBanner;

    protected int setMainLayout() {
        return R.layout.fragment_home;
    }

    private UpdateVersionController controller = null;
    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {

        view = getView();
        aCache = ACache.get(getActivity());
        StatusBarUtil.setTransparentForWindow(getActivity());
        context = getActivity();
        mRandom = new Random();       //相应的点击事件
        mHandler = new Handler();

        WebSettings settings = webviewHome.getSettings();
        setWebViewSettings(settings);
        settings.setJavaScriptEnabled(true);
        webviewHome.loadUrl("file:///android_asset/circular/home.html?url=" + url);
        Log.i("url", url);
        text();

        if (null == controller) {
            controller = UpdateVersionController.getInstance(getActivity());
        }
        schoolvideo();

        middleViewpager.setAdapter(new ViewPagerAdapter(getFragmentManager(), showView()));
        try {
            p.functionMultiRequests(Api.URLs + "ideal.htm?", getActivity(), Api.ARTICLE(UtilFileDB.Tel(),
                    UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123),String.valueOf(1)), 2);
        } catch (Exception e) {
            onError();
        }

        try {
            p.functionMultiRequests(Api.URLs + "ideal.htm?", getActivity(), Api.showDataVersion(UtilFileDB.Tel(),
                    UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123)), 4);
        } catch (Exception e) {
            onError();
        }

        try {
            p.functionMultiRequests(Api.URLs + "ideal.htm?", getActivity(), Api.lunbo(UtilFileDB.Tel(),
                    UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123)), 5);
        } catch (Exception e) {
            onError();
        }

    }


    @Override
    protected void initBeforeData() {
        ArrayList<HomeSchoolInfo> infosList = new ArrayList() {{
            add(new HomeSchoolInfo("2535", "清华大学", "北京市", R.mipmap.qh_log));
            add(new HomeSchoolInfo("2", "北京大学", "北京市", R.mipmap.bd_log));
            add(new HomeSchoolInfo("208", "人民大学", "北京市", R.mipmap.rm_log));
            add(new HomeSchoolInfo("218", "中国科大", "合肥市", R.mipmap.kd_log));
            add(new HomeSchoolInfo("2513", "上海交大", "上海市", R.mipmap.sh_log));
            add(new HomeSchoolInfo("273", "南京大学", "南京市", R.mipmap.nj_log));
            add(new HomeSchoolInfo("2604", "武汉大学", "武汉市", R.mipmap.wh_log));
            add(new HomeSchoolInfo("2520", "复旦大学", "上海市", R.mipmap.fd_log));
        }};

        gridView.setAdapter(new HorizontalListViewAdapter(getContext(), infosList));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(context, SchoolDetails.class);
                intent.putExtra("id", infosList.get(position).getId());
                startActivity(intent);
            }
        });


        listviewHome.setPullLoadEnable(true);
        listviewHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(context, ArticleDetails.class);
                intent.putExtra("articleid", datas.get(position - 1).getId());
                startActivity(intent);
            }
        });


    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        if (position == 2) {
            ArticleInfo Info = JSON.parseObject(topContributor, ArticleInfo.class);
            Log.e("文章",topContributor);
            if (Info.getRet() == 2001) {
                openActivity(LoginActivity.class);
            }else {
                if (Info.getData() != null && Info.getData().size() > 0 && datas.size() == 0) {
                    datas.addAll(Info.getData());
                    page = Info.getPage();
                    listviewAdp = new ListviewAdp(getActivity(), datas);
                    listviewHome.setAdapter(listviewAdp);
                    listviewHome.setXListViewListener(this);
                } else if (Info.getData() != null && Info.getData().size() > 0 && datas.size() > 0){
                    datas.addAll(Info.getData());
                    listviewAdp.notifyDataSetChanged();
                }else {
                    showToastShort("亲！！！数据加载完毕");
                }
            }

        }
        if (position == 4) {
            try {
                VersionInfo info = JSON.parseObject(topContributor, VersionInfo.class);
                if (info.getRet() == 0) {
/*                    Double serverVersion = ConvertUtil.convertToDouble(info.getData().getNumber(),2015);
                    Double clientVersion = ConvertUtil.convertToDouble(EventBusUtil.getVersion(getActivity()),2015);
                    if (info.getData().getVType().equals("0")) {
                        boolean sss = true;
                        mUpdateManager = new UpdateManager(getActivity());
                        mUpdateManager.showNoticeDialog(serverVersion, clientVersion, sss);
                    }else {
                        boolean bbb = false;
                        mUpdateManager = new UpdateManager(getActivity());
                        mUpdateManager.showNoticeDialog(serverVersion, clientVersion, bbb);
                    }*/
                    Log.e("对比",info.getData().getNumber()+"==="+EventBusUtil.getVersion(getActivity()));
                    if (!info.getData().getNumber().equals(EventBusUtil.getVersion(getActivity()))){
                        if (info.getData().getVType().equals("0")) {
                            controller.normalCheckUpdateInfo(info.getData().getBody());
                            //这里来检测版本是否需要更新
                        }else{
                            controller.forceCheckUpdateInfo(info.getData().getBody());
                        }
                    }

/*                    if (!info.getData().getNumber().equals(EventBusUtil.getVersion(getActivity()))) {
                        centerDialog = new CenterDialog(getActivity(),
                                R.layout.activity_center, new int[]{R.id.quxiao, R.id.dialog_sure},
                                "http://helloyun.cn:8080/apk/aizy.apk");
                        centerDialog.setOnCenterItemClickListener(this);
                        centerDialog.show();

                    }*/


                }
                if (info.getRet() == 2001) {
                    openActivity(LoginActivity.class);
                }
            } catch (Exception e) {
            }
        }


        if (position == 5) {
            ArticleInfo info = JSON.parseObject(topContributor, ArticleInfo.class);
            lists = info.getData();
            ArrayList<String> images = new ArrayList<>();
            try {
                for (int i=0;i<info.getData().size();i++){
                    String url=info.getData().get(i).getTitleImg();
                    images.add(Api.URLs+url);
                }
            }catch (Exception E){
                onError();
            }

            mBanner = (CustomBanner) view.findViewById(R.id.banner);
            setBean(images);
            mBanner.setOnPageClickListener(new CustomBanner.OnPageClickListener() {
                @Override
                public void onPageClick(int i, Object o) {
                    intent = new Intent(getContext(), ArticleDetails.class);
                    intent.putExtra("articleid", lists.get(i).getId());
                    startActivity(intent);
                }
            });

        }
    }


    //设置普通指示器
    private void setBean(final ArrayList<String> beans) {
        mBanner.setPages(new CustomBanner.ViewCreator<String>() {
            @Override
            public View createView(Context context, int position) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }

            @Override
            public void updateUI(Context context, View view, int position, String entity) {
                Glide.with(context).load(entity).transform(new GlideRoundTransform(context, 4)).into((ImageView) view);
            }
        }, beans)
                .startTurning(5000);
    }


    @Override
    public void onNetWork() {
        showToastShort("网络连接失败");
    }

    @Override
    public void onError() {

    }


    @Override
    public void onLoadContributorStart() {
    }

    private List<Fragment> showView() {
        List<Fragment> list = new ArrayList<Fragment>();
        FragmentPage fragmentPage1 = new FragmentPage();
        Bundle bundle1 = new Bundle();
        bundle1.putString("index", "1");
        fragmentPage1.setArguments(bundle1);
        list.add(fragmentPage1);
        return list;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            try {
                if (resultCode == 2) {
                    score = UtilFileDB.LOGINSCORE(aCache);
                    webviewHome.loadUrl("file:///android_asset/circular/home.html?url=" + url);
                }
            } catch (Exception e) {

            }
            try {
                if (resultCode == 3) {
                    score = UtilFileDB.LOGINSCORE(aCache);
                    webviewHome.loadUrl("file:///android_asset/circular/home.html?url=" + url);
                }
            } catch (Exception e) {
            }
        }
    }


    @OnClick({R.id.ry_main1,R.id.update_score})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ry_main1:
                openActivity(GoSchoolActivity.class);
                break;
            case R.id.update_score:
                openActivity(MyScore.class);
                break;

        }
    }

    @Override
    public void OnCenterItemClick(CenterDialog dialog, View view, String url) {
        switch (view.getId()) {
            case R.id.dialog_sure:
                p.showDownloadAPK(getActivity(), url);
                centerDialog.cancel();
                break;
            default:
                break;
        }
    }

    private void text() {
        viewfli = (ViewFlipper) view.findViewById(R.id.viewfli);
        // 为ViewFlipper设置内容
        List<TextView> list = new ArrayList<TextView>();
        TextView tv = (TextView) new TextView(getActivity());
        tv.setText("热烈祝贺祝贺!都匀二中王梓淇同学被北京大学录取！");
        tv.setTextSize(11);
        viewfli.addView(tv);

        tv = new TextView(getContext());
        tv.setText("来自黔南州考生：186****5622开通了会员！");
        tv.setTextSize(11);
        viewfli.addView(tv);

        tv = new TextView(getContext());
        tv.setText("来自贵阳考生：187****5641开通了会员！");
        tv.setTextSize(11);
        viewfli.addView(tv);

        tv = new TextView(getContext());
        tv.setText("来自贵阳考生：187****5201开通了会员！");
        tv.setTextSize(11);
        viewfli.addView(tv);

        tv = new TextView(getContext());
        tv.setText("来自铜仁考生：186***3251开通了会员！");
        tv.setTextSize(11);
        tv = new TextView(getContext());
        tv.setText("来自贵阳考生：151****5201开通了会员！");
        tv.setTextSize(11);
        viewfli.addView(tv);

        tv = new TextView(getContext());
        tv.setText("来自毕节考生：187****9480开通了会员！");
        tv.setTextSize(11);
        viewfli.addView(tv);

        tv = new TextView(getContext());
        tv.setText("来自兴义考生：131****5500开通了会员！");
        tv.setTextSize(11);
        viewfli.addView(tv);

        tv = new TextView(getContext());
        tv.setText("来自安顺考生：151****9852开通了会员！");
        tv.setTextSize(11);
        viewfli.addView(tv);

        // 设置文字in/out的动画效果
        viewfli.setInAnimation(getActivity(), R.anim.push_up_in);
        viewfli.setOutAnimation(getActivity(), R.anim.push_up_out);
        viewfli.startFlipping();

    }


    private void schoolvideo() {
        ArrayList<VideoInfo> videoInfos = new ArrayList() {{

            add(new VideoInfo("1", "清华大学", qh, R.mipmap.qhdx, "https://vd3.bdstatic.com/mda-jfavj6qssmp0mq2f/mda-jfavj6qssmp0mq2f.mp4"));
            add(new VideoInfo("2", "北京大学", bd, R.mipmap.bd, "https://qiniu-xpc10.xpccdn.com/5cb54c001ef2b.mp4"));
            add(new VideoInfo("3", "贵州大学", gd, R.mipmap.gd, "https://qiniu-xpc10.xpccdn.com/5bd2b686ce850.mp4"));
            add(new VideoInfo("4", "华中科技大学", hz, R.mipmap.hz, "https://qiniu-xpc4.xpccdn.com/5d12cc02e9ad2.mp4"));
            add(new VideoInfo("5", "武汉大学", wh, R.mipmap.wh, "http://www.gsao.fudan.edu.cn/_upload/article/videos/28/a9/3fcf110f4174bc3e4af72fe39449/51b393e7-6b52-4b52-99c4-042d3ef4e4f1.mp4"));
            add(new VideoInfo("6", "复旦大学", fd, R.mipmap.fd, "http://www.gsao.fudan.edu.cn/_upload/article/videos/28/a9/3fcf110f4174bc3e4af72fe39449/51b393e7-6b52-4b52-99c4-042d3ef4e4f1.mp4"));

        }};

        LinearLayoutManager m=new LinearLayoutManager(getActivity());
        m.setOrientation(LinearLayoutManager.HORIZONTAL);
        homeListVideo.setLayoutManager(m);
        recycAdapter = new RecycAdapter(context, videoInfos);
        homeListVideo.setAdapter(recycAdapter);
        recycAdapter.setOnItemClickListenter(new OnItemClickListenter() {
            @Override
            public void onItemClick(View view, int position) {
                intent = new Intent(context, HomeVideoContentActivity.class);
                intent.putExtra("id", videoInfos.get(position).getId());
                intent.putExtra("URL", videoInfos.get(position).getUrl());
                intent.putExtra("name", videoInfos.get(position).getName());
                intent.putExtra("contens", videoInfos.get(position).getContents());
                startActivity(intent);
            }
        });
    }



    private void simulateLoadMoreData() {
        try {
            p.functionMultiRequests(Api.URLs + "ideal.htm?", getActivity(), Api.ARTICLE(UtilFileDB.Tel(),
                    UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123),String.valueOf(page)), 2);
        } catch (Exception e) {

        }
    }


    private void onLoad() {
        listviewHome.stopRefresh();
        listviewHome.stopLoadMore();
    }

    @Override
    public void onPause() {
        p.detachView();
        super.onPause();
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page += 10;
                simulateLoadMoreData();
                onLoad();
            }
        }, 1500);
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoad();
                page = 0;
                datas.clear();
                simulateLoadMoreData();
            }
        }, 2000);
    }




}


