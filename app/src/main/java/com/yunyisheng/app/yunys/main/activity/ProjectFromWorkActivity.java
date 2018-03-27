package com.yunyisheng.app.yunys.main.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
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
    @BindView(R.id.img_quesheng)
    ImageView imgQuesheng;
    private List<ProjectFromWorkBean.ListBean> projectFromWorkBeans = new ArrayList<>();//项目架构全部人员
    private List<FindProjectWorkerBean.RespBodyBean> findWorkerProjectBeanList = new ArrayList<>();//项目搜索人员
    private String sousuo_neirong;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        teTitle.setText("项目架构");
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

        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FindProjectWorkerBean.RespBodyBean respBodyBean = findWorkerProjectBeanList.get(position);
                Intent intent = new Intent(ProjectFromWorkActivity.this, WorkerDataActivity.class);
                intent.putExtra("userid", respBodyBean.getUserId());
                intent.putExtra("workerhead", respBodyBean.getUserPicture());
                startActivity(intent);
            }
        });

        elvOrganizationframe.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                ProjectFromWorkBean.ListBean.UserListBean userListBean = projectFromWorkBeans.get(groupPosition).getUserList().get(childPosition);
                Intent intent = new Intent(ProjectFromWorkActivity.this, WorkerDataActivity.class);
                intent.putExtra("userid", userListBean.getUserId());
                intent.putExtra("workerhead", userListBean.getUserPicture());
                startActivity(intent);
                return true;
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
        if (list.size() > 0) {
            projectFromWorkBeans.addAll(list);
            ProjectFromWorkListExpenableAdapter adapter = new ProjectFromWorkListExpenableAdapter(this, projectFromWorkBeans);
            elvOrganizationframe.setAdapter(adapter);
            elvOrganizationframe.setGroupIndicator(null);
        } else {
            elvOrganizationframe.setVisibility(View.GONE);
            imgQuesheng.setVisibility(View.VISIBLE);
        }
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

    public void setImgQueshengng(){
        elvOrganizationframe.setVisibility(View.GONE);
        imgQuesheng.setVisibility(View.VISIBLE);
        imgQuesheng.setBackgroundResource(R.mipmap.no_network);
    }

    @Override
    public void setListener() {
        imgQuesheng.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        imgClear.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_quesheng:
                getP().getFromworklist();
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.img_clear:
                edSearch.setText("");
                break;
        }
    }
}
