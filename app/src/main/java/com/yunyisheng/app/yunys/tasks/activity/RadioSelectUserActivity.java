package com.yunyisheng.app.yunys.tasks.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.model.WorkerBean;
import com.yunyisheng.app.yunys.tasks.adapter.RadioSelectUserAdapter;
import com.yunyisheng.app.yunys.tasks.bean.ProjectUserBean;
import com.yunyisheng.app.yunys.tasks.model.ProjectUserListModel;
import com.yunyisheng.app.yunys.tasks.present.RadioSelectUserPresent;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;

public class RadioSelectUserActivity extends BaseActivity<RadioSelectUserPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.select_check_user_list)
    ListView selectCheckUserList;
    @BindView(R.id.page_title)
    TextView pageTitle;

    RadioSelectUserAdapter adapter;
    List<ProjectUserBean> dataList = new ArrayList<>();

    private String projectId;
    private String fromPageTitle;
    private String selectUserId;
    private List<WorkerBean> workerlist;
    int thisUserid = SharedPref.getInstance(context).getInt("userid",0);
    private Integer createUserId;
    @Override
    public void initView() {
        ButterKnife.bind(this);
        projectId = getIntent().getStringExtra("projectId");
        fromPageTitle = getIntent().getStringExtra("fromPageTitle");
        selectUserId = getIntent().getStringExtra("selectUserId");
        createUserId = getIntent().getIntExtra("createUser",0);
        if (fromPageTitle != null){
            pageTitle.setText(fromPageTitle);
        }else {
            pageTitle.setText("请选择审批人员");
        }
    }

    @Override
    public void initAfter() {
        if (projectId != null){
            getP().getProjectUserList(projectId);
        }else {
            getP().getAllUserLists(createUserId);
        }

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_radio_select_user;
    }

    @Override
    public RadioSelectUserPresent bindPresent() {
        return new RadioSelectUserPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.submit:
                Intent intent = new Intent();
                int selectPosition = adapter.getSelectPosition();
                if (selectPosition == -1){
                    setResult(2,intent);
                }else {
                    ProjectUserBean selectUser = dataList.get(selectPosition);
                    String username = "";
                    if (selectUser.getName() != null){
                         username = selectUser.getName();
                    }
                    if (selectUser.getUserName() != null){
                        username = selectUser.getUserName();
                    }
                    intent.putExtra("selectUserId",selectUser.getUserId()+"");
                    intent.putExtra("selectUserName",username);
                    setResult(1,intent);
                }
                finish();
                break;
        }
    }

    public void setAdapterData(ProjectUserListModel projectUserListModel) {
        if (projectUserListModel.getRespBody().size() > 0){
            dataList.clear();
            List<ProjectUserBean> checkUserLists = new ArrayList<>();
            for (int i=0;i<projectUserListModel.getRespBody().size();i++){
                if (thisUserid != projectUserListModel.getRespBody().get(i).getUserId()){
                    checkUserLists.add(projectUserListModel.getRespBody().get(i));
                }
            }
            dataList.addAll(checkUserLists);
            adapter = new RadioSelectUserAdapter(context,dataList,selectUserId);
            selectCheckUserList.setAdapter(adapter);
        }else {
            ToastUtils.showToast("暂无人员！");
        }
    }

    public void getResultList(String body) {
        try {
            LogUtils.i("bodystrsf",body);
            workerlist = new ArrayList<>();
            JSONObject object = new JSONObject(body);
            JSONArray jsonArray = object.getJSONArray("list");
            JSONObject object1 = new JSONObject(jsonArray.get(0).toString());
            JSONArray users = object1.getJSONArray("users");
            if (users.length() > 0) {
                for (int i = 0; i < users.length(); i++) {
                    WorkerBean workerBean = new WorkerBean();
                    JSONObject usersobject = new JSONObject(users.get(i).toString());
                    int userId = usersobject.getInt("userId");
                    String name = usersobject.getString("name");
                    workerBean.setName(name);
                    workerBean.setUserId(userId);
                    workerlist.add(workerBean);
                }
            }
            JSONArray subdivision = object1.getJSONArray("subdivision");
            if (subdivision.length() > 0) {
                for (int j = 0; j < subdivision.length(); j++) {
                    JSONObject subdivisionobject = new JSONObject(subdivision.get(j).toString());
                    JSONArray users1 = subdivisionobject.getJSONArray("users");
                    if (users1.length() > 0) {
                        for (int i = 0; i < users1.length(); i++) {
                            WorkerBean workerBean = new WorkerBean();
                            JSONObject usersobject = new JSONObject(users1.get(i).toString());
                            int userId = usersobject.getInt("userId");
                            String name = usersobject.getString("name");
                            workerBean.setName(name);
                            workerBean.setUserId(userId);
                            workerlist.add(workerBean);
                        }
                    }
                    if (!subdivisionobject.isNull("subdivision")) {
                        JSONArray subdivisionarray = subdivisionobject.getJSONArray("subdivision");
                        if (subdivisionarray.length() > 0) {
                            getChildBumen(subdivisionarray);
                        }
                    }
                }
            }
            if (workerlist.size()>0) {
                for (int m = 0; m < workerlist.size(); m++) {
                    WorkerBean workerBean = workerlist.get(m);
                    ProjectUserBean projectUserBean = new ProjectUserBean();
                    projectUserBean.setUserId(workerBean.getUserId());
                    projectUserBean.setName(workerBean.getName());
                    dataList.add(projectUserBean);
                }
                adapter = new RadioSelectUserAdapter(context, dataList, selectUserId);
                selectCheckUserList.setAdapter(adapter);
            }else {
                ToastUtils.showToast("暂无人员！");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author fuduo
     * @time 2018/1/27  21:13
     * @describe 子部门
     */
    private void getChildBumen(JSONArray jsonArray) {
        try {
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject subdivisionobject = new JSONObject(jsonArray.get(i).toString());
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
                        workerBean.setName(name);
                        workerBean.setUserId(userId);
                        workerlist.add(workerBean);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
