package com.yunyisheng.app.yunys.main.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.base.PressionListener;
import com.yunyisheng.app.yunys.main.adapter.FindWorkerListAdapter;
import com.yunyisheng.app.yunys.main.adapter.MaillistExpenableAdapter;
import com.yunyisheng.app.yunys.main.model.FindWorkerBean;
import com.yunyisheng.app.yunys.main.model.WorkerBean;
import com.yunyisheng.app.yunys.main.model.WorkerListBean;
import com.yunyisheng.app.yunys.main.present.MaillistPresent;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author fuduo
 * @time 2018/1/16  21:24
 * @describe 通讯录
 */
public class MailListActivity extends BaseActivity<MaillistPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.rl_organizationframe)
    RelativeLayout rlOrganizationframe;
    @BindView(R.id.rl_projectframe)
    RelativeLayout rlProjectframe;
    @BindView(R.id.rl_arrangework)
    RelativeLayout rlArrangework;
    @BindView(R.id.rl_invite)
    RelativeLayout rlInvite;
    @BindView(R.id.elv_organizationframe)
    ExpandableListView elvOrganizationframe;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    @BindView(R.id.lv_search)
    ListView lvSearch;
    @BindView(R.id.img_quesheng)
    ImageView imgQuesheng;
    private String sousuo_neirong;
    private List<WorkerListBean> workerbeanlist = new ArrayList<>();
    private List<FindWorkerBean.respBodyBean> findWorkerBeanList = new ArrayList<>();

    @Override
    public void initView() {
        requestPermission();
        ButterKnife.bind(this);
        teTitle.setText(R.string.tongxunlu);
        edSearch.addTextChangedListener(mTextWatcher);
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                sousuo_neirong = edSearch.getText().toString();
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(MailListActivity.this.getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    if (sousuo_neirong == null || sousuo_neirong.equals("")) {
                        ToastUtils.showToast("搜索内容不能为空");
                    } else {
                        getP().getFindList(sousuo_neirong);
                    }
                }
                return false;
            }
        });

        elvOrganizationframe.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                WorkerBean workerBean = workerbeanlist.get(groupPosition).getWorkerBeanList().get(childPosition);
                Intent intent = new Intent(MailListActivity.this, WorkerDataActivity.class);
                intent.putExtra("workerhead", workerBean.getIcon());
                intent.putExtra("userid", workerBean.getUserId());
                startActivity(intent);
                return true;
            }
        });
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

    /**
     * 请求权限
     */
    private void requestPermission() {
        requestRunTimePression(this, new String[]{Manifest.permission.CALL_PHONE}, new PressionListener() {
            @Override
            public void onGranted() {

            }

            @Override
            public void onFailure(List<String> failurePression) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("提示")
                        .setMessage("请您去设置中授予通话的权限")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
                                ComponentName cName = new ComponentName("com.android.phone", "com.android.phone.Settings");
                                intent.setComponent(cName);
                                startActivity(intent);
                            }
                        });
            }
        });
    }

    @Override
    public void initAfter() {
        getP().getMaillist();
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_mail_list;
    }

    @Override
    public MaillistPresent bindPresent() {
        return new MaillistPresent();
    }

    public void getResultList(String fromworkBean) {
        try {
            workerbeanlist.clear();
            List<WorkerBean> workerlist = new ArrayList<>();
            JSONObject object = new JSONObject(fromworkBean);
            JSONArray jsonArray = object.getJSONArray("list");
            WorkerListBean listBean = new WorkerListBean();
            JSONObject object1 = new JSONObject(jsonArray.get(0).toString());
            String textname = object1.getString("text");
            listBean.setGroupname(textname);
            JSONArray users = object1.getJSONArray("users");
            if (users.length() > 0) {
                for (int i = 0; i < users.length(); i++) {
                    WorkerBean workerBean = new WorkerBean();
                    JSONObject usersobject = new JSONObject(users.get(i).toString());
                    int userId = usersobject.getInt("userId");
                    String name = usersobject.getString("name");
                    String userMailbox = usersobject.getString("userMailbox");
                    String userPhone = usersobject.getString("userPhone");
                    String userSex = usersobject.getString("userSex");
                    String icon = usersobject.getString("icon");
                    String userJobTitle = usersobject.getString("userJobTitle");
                    workerBean.setUserJobTitle(userJobTitle);
                    workerBean.setIcon(icon);
                    workerBean.setName(name);
                    workerBean.setUserId(userId);
                    workerBean.setEamil(userMailbox);
                    workerBean.setSex(userSex);
                    workerBean.setUserPhone(userPhone);
                    workerlist.add(workerBean);
                }
                listBean.setWorkerBeanList(workerlist);
                workerbeanlist.add(listBean);
            }
            JSONArray subdivision = object1.getJSONArray("subdivision");
            if (subdivision.length() > 0) {

                for (int j = 0; j < subdivision.length(); j++) {
                    List<WorkerBean> workerlist2 = new ArrayList<>();
                    WorkerListBean listBean2 = new WorkerListBean();
                    JSONObject subdivisionobject = new JSONObject(subdivision.get(j).toString());
                    String subdivisionname = subdivisionobject.getString("text");
                    listBean2.setGroupname(subdivisionname);
                    JSONArray users1 = subdivisionobject.getJSONArray("users");
                    if (users1.length() > 0) {
                        for (int i = 0; i < users1.length(); i++) {
                            WorkerBean workerBean = new WorkerBean();
                            JSONObject usersobject = new JSONObject(users1.get(i).toString());
                            int userId = usersobject.getInt("userId");
                            String name = usersobject.getString("name");
                            String userMailbox = usersobject.getString("userMailbox");
                            String userPhone = usersobject.getString("userPhone");
                            String userSex = usersobject.getString("userSex");
                            String icon = usersobject.getString("icon");
                            String userJobTitle = usersobject.getString("userJobTitle");
                            workerBean.setUserJobTitle(userJobTitle);
                            workerBean.setIcon(icon);
                            workerBean.setName(name);
                            workerBean.setUserId(userId);
                            workerBean.setEamil(userMailbox);
                            workerBean.setSex(userSex);
                            workerBean.setUserPhone(userPhone);
                            workerlist2.add(workerBean);
                        }
                        listBean2.setWorkerBeanList(workerlist2);
                    }
                    workerbeanlist.add(listBean2);
                    if (!subdivisionobject.isNull("subdivision")) {
                        JSONArray subdivisionarray = subdivisionobject.getJSONArray("subdivision");
                        if (subdivisionarray.length() > 0) {
                            getChildBumen(subdivisionarray);
                        }
                    }
                }

            }
            if (workerbeanlist.size()>0) {
                MaillistExpenableAdapter adapter = new MaillistExpenableAdapter(MailListActivity.this, workerbeanlist);
                elvOrganizationframe.setAdapter(adapter);
                elvOrganizationframe.setGroupIndicator(null);
                elvOrganizationframe.setVisibility(View.VISIBLE);
                imgQuesheng.setVisibility(View.GONE);
            }else {
                elvOrganizationframe.setVisibility(View.GONE);
                imgQuesheng.setVisibility(View.VISIBLE);
                imgQuesheng.setBackgroundResource(R.mipmap.no_data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getChildBumen(JSONArray jsonArray) {
        try {
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    List<WorkerBean> workerlist = new ArrayList<>();
                    WorkerListBean listBean = new WorkerListBean();
                    JSONObject subdivisionobject = new JSONObject(jsonArray.get(i).toString());
                    String subdivisionname = subdivisionobject.getString("text");
                    listBean.setGroupname(subdivisionname);
                    if (!subdivisionobject.isNull("subdivision")) {
                        JSONArray subdivisionarray = subdivisionobject.getJSONArray("subdivision");
                        if (subdivisionarray.length() > 0) {
                            getChildBumen(subdivisionarray);
                        }
                    }
                    JSONArray users = subdivisionobject.getJSONArray("users");
                    if (users.length() > 0) {
                        WorkerBean workerBean = new WorkerBean();
                        JSONObject usersobject = new JSONObject(users.get(i).toString());
                        int userId = usersobject.getInt("userId");
                        String name = usersobject.getString("name");
                        String userMailbox = usersobject.getString("userMailbox");
                        String userPhone = usersobject.getString("userPhone");
                        String userSex = usersobject.getString("userSex");
                        String icon = usersobject.getString("icon");
                        String userJobTitle = usersobject.getString("userJobTitle");
                        workerBean.setUserJobTitle(userJobTitle);
                        workerBean.setIcon(icon);
                        workerBean.setName(name);
                        workerBean.setUserId(userId);
                        workerBean.setEamil(userMailbox);
                        workerBean.setSex(userSex);
                        workerBean.setUserPhone(userPhone);
                        workerlist.add(workerBean);
                    }
                    listBean.setWorkerBeanList(workerlist);
                    workerbeanlist.add(listBean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getFindList(FindWorkerBean findWorkerBean) {
        if (findWorkerBean.getRespBodyBeanList().size() > 0) {
            findWorkerBeanList.clear();
            elvOrganizationframe.setVisibility(View.GONE);
            lvSearch.setVisibility(View.VISIBLE);
            List<FindWorkerBean.respBodyBean> respBodyBeanList = findWorkerBean.getRespBodyBeanList();
            findWorkerBeanList.addAll(respBodyBeanList);
            FindWorkerListAdapter adapter = new FindWorkerListAdapter(MailListActivity.this, findWorkerBeanList);
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
        imgBack.setOnClickListener(this);
        rlOrganizationframe.setOnClickListener(this);
        rlProjectframe.setOnClickListener(this);
        rlArrangework.setOnClickListener(this);
        rlInvite.setOnClickListener(this);
        imgClear.setOnClickListener(this);
        imgQuesheng.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_quesheng:
                getP().getMaillist();
                break;
            case R.id.rl_organizationframe:
                break;
            case R.id.rl_projectframe:
                startActivity(new Intent(MailListActivity.this, ProjectFromWorkActivity.class));
                break;
            case R.id.rl_arrangework:
//                Intent intent = new Intent(MailListActivity.this, SelectPeopleActivity.class);
//                intent.putExtra("type", 1);
//                startActivity(intent);
                break;
            case R.id.rl_invite:
               startActivity(new Intent(MailListActivity.this, InviteWorkerActivity.class));
                break;
            case R.id.img_clear:
                edSearch.setText("");
                break;
        }
    }
}
