package com.yunyisheng.app.yunys.main.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.adapter.MaillistExpenableAdapter;
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
    private String sousuo_neirong;
    private List<WorkerListBean> workerbeanlist = new ArrayList<>();


    @Override
    public void initView() {
        ButterKnife.bind(this);
        teTitle.setText(R.string.tongxunlu);
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

                    }
                }
                return false;
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
                    if (!subdivisionobject.isNull("subdivision")){
                        JSONArray subdivisionarray = subdivisionobject.getJSONArray("subdivision");
                        if (subdivisionarray.length()>0){
                            getChildBumen(subdivisionarray);
                        }
                    }
                }

            }
            MaillistExpenableAdapter adapter = new MaillistExpenableAdapter(MailListActivity.this, workerbeanlist);
            elvOrganizationframe.setAdapter(adapter);
            elvOrganizationframe.setGroupIndicator(null);
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
                    if (!subdivisionobject.isNull("subdivision")){
                        JSONArray subdivisionarray = subdivisionobject.getJSONArray("subdivision");
                        if (subdivisionarray.length()>0){
                            getChildBumen(subdivisionarray);
                        }
                    }
                    JSONArray users = subdivisionobject.getJSONArray("users");
                    if (users.length()>0){
                        WorkerBean workerBean = new WorkerBean();
                        JSONObject usersobject = new JSONObject(users.get(i).toString());
                        int userId = usersobject.getInt("userId");
                        String name = usersobject.getString("name");
                        String userMailbox = usersobject.getString("userMailbox");
                        String userPhone = usersobject.getString("userPhone");
                        String userSex = usersobject.getString("userSex");
                        String icon = usersobject.getString("icon");
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

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        rlOrganizationframe.setOnClickListener(this);
        rlProjectframe.setOnClickListener(this);
        rlArrangework.setOnClickListener(this);
        rlInvite.setOnClickListener(this);
        imgClear.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.rl_organizationframe:
                break;
            case R.id.rl_projectframe:
                break;
            case R.id.rl_arrangework:
                startActivity(new Intent(MailListActivity.this, SelectPeopleActivity.class));
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
