package com.yunyisheng.app.yunys.main.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.adapter.ProjectFromWorkListExpenableAdapter;
import com.yunyisheng.app.yunys.main.adapter.ProjectWorkerFindListAdapter;
import com.yunyisheng.app.yunys.main.model.FindProjectWorkerBean;
import com.yunyisheng.app.yunys.main.model.ProjectFromWorkBean;
import com.yunyisheng.app.yunys.main.present.ProjectFromworkPresent;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectFromWorkActivity extends BaseActivity<ProjectFromworkPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    @BindView(R.id.elv_organizationframe)
    ExpandableListView elvOrganizationframe;
    @BindView(R.id.lv_search)
    ListView lvSearch;
    private List<ProjectFromWorkBean.ListBean> projectFromWorkBeans = new ArrayList<>();//项目架构全部人员
    private List<FindProjectWorkerBean.RespBodyBean> findWorkerProjectBeanList = new ArrayList<>();//项目搜索人员
    private String sousuo_neirong;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        teTitle.setText("项目架构");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //edSearch.setText("");
                sousuo_neirong = edSearch.getText().toString();
                if (sousuo_neirong == null || sousuo_neirong.equals("")) {
                    ToastUtils.showToast("搜索内容不能为空");
                } else {
                    getP().getFindProjectList(sousuo_neirong);
                }
            }
        });
        edSearch.addTextChangedListener(mTextWatcher);
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                sousuo_neirong = edSearch.getText().toString();
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    if (sousuo_neirong == null || sousuo_neirong.equals("")) {
                        ToastUtils.showToast("搜索内容不能为空");
                    } else {
                        getP().getFindProjectList(sousuo_neirong);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void initAfter() {
        getP().getFromworklist();
    }

    //监听是否输入
    TextWatcher mTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
        }

        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            if (s.length() > 0) {
                imgClear.setVisibility(View.VISIBLE);
            } else {
                elvOrganizationframe.setVisibility(View.VISIBLE);
                lvSearch.setVisibility(View.GONE);
                imgClear.setVisibility(View.GONE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public int bindLayout() {
        return R.layout.activity_project_from_work;
    }

    @Override
    public ProjectFromworkPresent bindPresent() {
        return new ProjectFromworkPresent();
    }

    /**
     * @author fuduo
     * @time 2018/1/26  20:39
     * @describe 获取项目架构
     */
    public void getResult(ProjectFromWorkBean projectFromWorkBean) {
        List<ProjectFromWorkBean.ListBean> list = projectFromWorkBean.getList();
        projectFromWorkBeans.addAll(list);
        ProjectFromWorkListExpenableAdapter adapter = new ProjectFromWorkListExpenableAdapter(this, projectFromWorkBeans);
        elvOrganizationframe.setAdapter(adapter);
        elvOrganizationframe.setGroupIndicator(null);
    }

    /**
     * @author fuduo
     * @time 2018/1/27  21:14
     * @describe 获取搜索项目人员
     */
    public void getFindProjectList(FindProjectWorkerBean findProjectWorkerBean) {
        if (findProjectWorkerBean.getRespBody().size() > 0) {
            findWorkerProjectBeanList.clear();
            elvOrganizationframe.setVisibility(View.GONE);
            lvSearch.setVisibility(View.VISIBLE);
            List<FindProjectWorkerBean.RespBodyBean> respBodyBeanList = findProjectWorkerBean.getRespBody();
            findWorkerProjectBeanList.addAll(respBodyBeanList);
            ProjectWorkerFindListAdapter adapter = new ProjectWorkerFindListAdapter(this, findWorkerProjectBeanList);
            lvSearch.setAdapter(adapter);
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

}
