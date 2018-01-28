package com.yunyisheng.app.yunys.main.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.alibaba.fastjson.JSON;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.adapter.AddedReportformListAdapter;
import com.yunyisheng.app.yunys.main.adapter.CanAddReportformListAdapter;
import com.yunyisheng.app.yunys.main.model.ReportFormBean;
import com.yunyisheng.app.yunys.main.present.ReportFormPresent;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;

/**
 * @author fuduo
 * @time 2018/1/10  18:11
 * @describe 报表activity
 */
public class ReportformActivity extends BaseActivity<ReportFormPresent> {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_add)
    ImageView imgAdd;
    private boolean isshowmenu;
    private SlidingMenu menu;
    private MyGridView gvadded;
    private MyGridView gvadd;
    private Button btn_reset;
    private Button btn_queren;
    private List<ReportFormBean.ListBean> list = new ArrayList<>();
    private List<ReportFormBean.ListBean> addedlist = new ArrayList<>();
    private AddedReportformListAdapter adapter;

    @Override
    public void initView() {
        // configure the SlidingMenu
        ButterKnife.bind(this);
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.RIGHT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.distance_2);
        menu.setShadowDrawable(R.color.color_e7);

        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.distance_60);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        /**
         * SLIDING_WINDOW will include the Title/ActionBar in the content
         * section of the SlidingMenu, while SLIDING_CONTENT does not.
         */
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

        View view = LayoutInflater.from(mContext).inflate(R.layout.right_menu, null);
        gvadded = (MyGridView) view.findViewById(R.id.gv_added);
        gvadd = (MyGridView) view.findViewById(R.id.gv_add);
        btn_reset = (Button) view.findViewById(R.id.btn_reset);
        btn_queren = (Button) view.findViewById(R.id.btn_queren);

        //为侧滑菜单设置布局
        menu.setMenu(view);
        menu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
            @Override
            public void onOpened() {
                isshowmenu = true;
            }
        });

        gvadd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ReportFormBean.ListBean listBean = list.get(position);
                addedlist.add(listBean);
//                calGridViewWidthAndHeigh(2,gvadded);
                adapter = new AddedReportformListAdapter(ReportformActivity.this, addedlist);
                gvadded.setAdapter(adapter);
            }
        });

        gvadded.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addedlist.remove(position);
                adapter = new AddedReportformListAdapter(ReportformActivity.this, addedlist);
                gvadded.setAdapter(adapter);
            }
        });

    }

    @Override
    public void initAfter() {
        String addedReformString = SharedPref.getInstance(ReportformActivity.this).getString("AddedReformString", "");
        if (addedReformString != null && !addedReformString.equals("")) {
            ReportFormBean bean = JSON.parseObject(addedReformString, ReportFormBean.class);
            setAddedlist(bean);
        }
        getP().getBaobiaoList(1, 0);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_reportform;
    }

    @Override
    public ReportFormPresent bindPresent() {
        return new ReportFormPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
        btn_queren.setOnClickListener(this);
    }

    public void setAddedlist(ReportFormBean bean) {
        addedlist.addAll(bean.getList());
//        calGridViewWidthAndHeigh(2,gvadded);
        adapter = new AddedReportformListAdapter(ReportformActivity.this, addedlist);
        gvadded.setAdapter(adapter);

    }

    public void getResultList(ReportFormBean reportFormBean) {
        if (reportFormBean.getList().size() > 0) {
            list.addAll(reportFormBean.getList());
            CanAddReportformListAdapter addReportformListAdapter = new CanAddReportformListAdapter(ReportformActivity.this, list);
            gvadd.setAdapter(addReportformListAdapter);
        }
    }

    /**
     * 计算GridView宽高
     * @param gridView
     */
    public static void calGridViewWidthAndHeigh(int numColumns ,GridView gridView) {

        // 获取GridView对应的Adapter
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, gridView);
            listItem.measure(0, 0); // 计算子项View 的宽高

            if ((i+1)%numColumns == 0) {
                totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
            }

            if ((i+1) == len && (i+1)%numColumns != 0) {
                totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
            }
        }

        totalHeight += 40;

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        gridView.setLayoutParams(params);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_add:
                if (!isshowmenu) {
                    menu.showMenu();
                } else {
                    menu.toggle();
                }
                break;
            case R.id.btn_reset:
                addedlist.clear();
                adapter = new AddedReportformListAdapter(ReportformActivity.this, addedlist);
                gvadded.setAdapter(adapter);
                SharedPref.getInstance(ReportformActivity.this).putString("AddedReformString", "");
                break;
            case R.id.btn_queren:
                if (addedlist != null && addedlist.size() > 0) {
                    String json = JSON.toJSONString(addedlist);
                    SharedPref.getInstance(ReportformActivity.this).putString("AddedReformString", "{\"list\":" + json + "}");
                    LogUtils.i("string", "{\"list\":" + json + "}");
                }
                menu.toggle();
                break;
        }
    }
}
