package com.yunyisheng.app.yunys.main.fragement;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.main.activity.SelectPeopleActivity;
import com.yunyisheng.app.yunys.main.adapter.FromWorkListExpenableAdapter;
import com.yunyisheng.app.yunys.main.adapter.SelectFindProjectWorkerListAdapter;
import com.yunyisheng.app.yunys.main.adapter.SelectFindWorkerListAdapter;
import com.yunyisheng.app.yunys.main.adapter.SelectPeopleExpenableAdapter;
import com.yunyisheng.app.yunys.main.model.FindProjectWorkerBean;
import com.yunyisheng.app.yunys.main.model.FindWorkerBean;
import com.yunyisheng.app.yunys.main.model.ProjectFromWorkBean;
import com.yunyisheng.app.yunys.main.model.WorkerBean;
import com.yunyisheng.app.yunys.main.model.WorkerListBean;
import com.yunyisheng.app.yunys.main.present.SelectPeoplePresent;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * 作者：fuduo on 2018/1/12 10:15
 * 邮箱：duoendeavor@163.com
 * 用途：选人fragement
 */

public class OrganizationFragement extends BaseFragement<SelectPeoplePresent> {

    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.ck_allworker)
    ImageView ckAllworker;
    @BindView(R.id.elv_framework)
    ExpandableListView elv_framework;
    Unbinder unbinder;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    @BindView(R.id.scro_all)
    HorizontalScrollView scroAll;
    @BindView(R.id.lv_search)
    ListView lvSearch;
    @BindView(R.id.rl_bottom)
    public RelativeLayout rlBottom;
    @BindView(R.id.line_selecall)
    public LinearLayout lineSelecall;
    @BindView(R.id.btn_queren)
    Button btnQueren;

    private int tabindex;
    private boolean ischeck = false;

    private List<WorkerBean> selectlist = new ArrayList<>();//选中的人员
    private List<WorkerBean> findselectlist = new ArrayList<>();//通讯录搜索选中的人员
    private List<WorkerListBean> workerbeanlist = new ArrayList<>();//通讯录全部人员
    private SelectPeopleExpenableAdapter adapter;//通讯录适配器
    private List<FindWorkerBean.respBodyBean> findWorkerBeanList = new ArrayList<>();//通讯录搜索人员
    private SelectFindWorkerListAdapter selectFindWorkerListAdapter;//通讯录搜索适配器
    //子集合的item的数量
    private int projectchlidListSize = 0;

    private List<ProjectFromWorkBean.ListBean> projectFromWorkBeans = new ArrayList<>();//项目架构全部人员
    private FromWorkListExpenableAdapter fromWorkListExpenableAdapter;//项目架构适配器
    private List<FindProjectWorkerBean.RespBodyBean> findWorkerProjectBeanList = new ArrayList<>();//项目搜索人员
    private SelectFindProjectWorkerListAdapter selectFindProjectWorkerListAdapter;//项目搜索人员适配器
    private List<ProjectFromWorkBean.ListBean.UserListBean> selectprojectlist = new ArrayList<>();//项目选中的人员
    private List<ProjectFromWorkBean.ListBean.UserListBean> findprojectselectlist=new ArrayList<>();//项目搜索选中的人员

    private String sousuo_neirong;

    public static OrganizationFragement newInstance(int index) {
        OrganizationFragement fragement = new OrganizationFragement();
        Bundle bundle = new Bundle();
        bundle.putInt("tabIndex", index);
        fragement.setArguments(bundle);
        return fragement;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        tabindex = bundle.getInt("tabIndex");
    }

    @Override
    public void initView() {
        edSearch.addTextChangedListener(mTextWatcher);
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                sousuo_neirong = edSearch.getText().toString();
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    ((InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getActivity().getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    if (sousuo_neirong == null || sousuo_neirong.equals("")) {
                        ToastUtils.showToast("搜索内容不能为空");
                    } else {
                        if (tabindex == 0) {
                            getP().getFindList(sousuo_neirong);
                        } else {
                            getP().getFindProjectList(sousuo_neirong);
                        }

                    }
                }
                return false;
            }
        });

        btnQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectlist.size() > 0) {
                    JSONObject object = new JSONObject();
                    JSONArray array = new JSONArray();
                    for (int i = 0; i < selectlist.size(); i++) {
                        WorkerBean workerBean = selectlist.get(i);
                        String userid = workerBean.getUserId() + "";
                        array.put(userid);
                    }
                    try {
                        object.put("1", array);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String str = object.toString();
                    Log.i("stridnifn", str);

                    if (((SelectPeopleActivity)getActivity()).type==1){
                        createSelectTaskDialog(getActivity());
                    }else {
                        Intent intent = getActivity().getIntent();
                        intent.putExtra("size", selectlist.size());
                        intent.putExtra("selectjson", str);
                        getActivity().setResult(8, intent);
                        getActivity().finish();
                    }
                }
            }
        });


        imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edSearch.setText("");
            }
        });

        ckAllworker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lvSearch.getVisibility() == View.VISIBLE) {
                    if (tabindex == 0) {
                        if (ischeck) {
                            ischeck = false;
                            ckAllworker.setBackgroundResource(R.mipmap.select_no);
                            rlBottom.setVisibility(View.GONE);
                            selectFindWorkerListAdapter.setSelectall(false);
                        } else {
                            ischeck = true;
                            ckAllworker.setBackgroundResource(R.mipmap.select_yes);
                            rlBottom.setVisibility(View.VISIBLE);
                            selectFindWorkerListAdapter.setSelectall(true);
                        }
                        setViewList(selectFindWorkerListAdapter.getSelectlist());
                    } else {
                        if (ischeck) {
                            ischeck = false;
                            ckAllworker.setBackgroundResource(R.mipmap.select_no);
                            rlBottom.setVisibility(View.GONE);
                            selectFindProjectWorkerListAdapter.setSelectall(false);
                        } else {
                            ischeck = true;
                            ckAllworker.setBackgroundResource(R.mipmap.select_yes);
                            rlBottom.setVisibility(View.VISIBLE);
                            selectFindProjectWorkerListAdapter.setSelectall(true);
                        }
                        setProjectViewList(selectFindProjectWorkerListAdapter.getSelectlist());
                    }

                } else {
                    if (tabindex == 0) {
                        if (ischeck) {
                            ischeck = false;
                            ckAllworker.setBackgroundResource(R.mipmap.select_no);
                            rlBottom.setVisibility(View.VISIBLE);
                            adapter.setAllCheckandNocheck(false);
                        } else {
                            ischeck = true;
                            ckAllworker.setBackgroundResource(R.mipmap.select_yes);
                            rlBottom.setVisibility(View.GONE);
                            adapter.setAllCheckandNocheck(true);
                        }
                        expListExpand(1);
                        setSelectView(1, false);
                    } else {
                        if (ischeck) {
                            ischeck = false;
                            ckAllworker.setBackgroundResource(R.mipmap.select_no);
                            rlBottom.setVisibility(View.VISIBLE);
                            fromWorkListExpenableAdapter.setAllCheckandNocheck(false);
                        } else {
                            ischeck = true;
                            ckAllworker.setBackgroundResource(R.mipmap.select_yes);
                            rlBottom.setVisibility(View.GONE);
                            fromWorkListExpenableAdapter.setAllCheckandNocheck(true);
                        }
                        expListExpand(2);
                        setSelectView(2, false);
                    }

                }
            }
        });

        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (tabindex == 0) {
                    FindWorkerBean.respBodyBean respBodyBean = findWorkerBeanList.get(position);
                    if (respBodyBean.isIscheck()) {
                        respBodyBean.setIscheck(false);
                    } else {
                        respBodyBean.setIscheck(true);
                    }
                    selectFindWorkerListAdapter.notifyDataSetChanged();
                    setViewList(selectFindWorkerListAdapter.getSelectlist());
                } else {
                    FindProjectWorkerBean.RespBodyBean respBodyBean = findWorkerProjectBeanList.get(position);
                    if (respBodyBean.isIscheck()){
                        respBodyBean.setIscheck(false);
                    } else {
                        respBodyBean.setIscheck(true);
                    }
                    selectFindProjectWorkerListAdapter.notifyDataSetChanged();
                    setProjectViewList(selectFindProjectWorkerListAdapter.getSelectlist());
                }
            }
        });

        elv_framework.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (tabindex == 0) {
                    WorkerBean workerBean = workerbeanlist.get(groupPosition).getWorkerBeanList().get(childPosition);
                    if (workerBean.isIscheckchild()) {
                        workerBean.setIscheckchild(false);
                        boolean nohavecheck = checkListIsHaveIsCheck(1);
                        if (!nohavecheck) {
                            ckAllworker.setBackgroundResource(R.mipmap.select_no);
                            ischeck = false;
                        }
                        adapter.notifyDataSetChanged();
                        // workerBeanMaplist.remove(childPosition);
                    } else {
                        workerBean.setIscheckchild(true);
                        adapter.notifyDataSetChanged();
                        //workerBeanMaplist.put(childPosition,workerBean);
                        boolean haveischeck = checkListIsHaveIsCheck(1);
                        if (haveischeck) {
                            ckAllworker.setBackgroundResource(R.mipmap.select_yes);
                            ischeck = true;
                        }
                    }
                    setSelectView(1, false);
                } else {
                    ProjectFromWorkBean.ListBean.UserListBean userListBean = projectFromWorkBeans.get(groupPosition).getUserList().get(childPosition);
                    if (userListBean.isIscheckchild()) {
                        userListBean.setIscheckchild(false);
                        boolean nohavecheck = checkListIsHaveIsCheck(2);
                        if (!nohavecheck) {
                            ckAllworker.setBackgroundResource(R.mipmap.select_no);
                            ischeck = false;
                        }
                        fromWorkListExpenableAdapter.notifyDataSetChanged();
                        // workerBeanMaplist.remove(childPosition);
                    } else {
                        userListBean.setIscheckchild(true);
                        fromWorkListExpenableAdapter.notifyDataSetChanged();
                        //workerBeanMaplist.put(childPosition,workerBean);
                        boolean haveischeck = checkListIsHaveIsCheck(2);
                        if (haveischeck) {
                            ckAllworker.setBackgroundResource(R.mipmap.select_yes);
                            ischeck = true;
                        }
                    }
                    setSelectView(2, false);
                }
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
                elv_framework.setVisibility(View.VISIBLE);
                lvSearch.setVisibility(View.GONE);
                imgClear.setVisibility(View.GONE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    /**
     * 选择任务对话框
     *
     * @param activity
     * @return
     */
    public void createSelectTaskDialog(final Activity activity) {
        final Dialog mSelectTask = new Dialog(activity, R.style.dialog_bottom_full);
        mSelectTask.setCanceledOnTouchOutside(true);
        mSelectTask.setCancelable(true);
        Window window = mSelectTask.getWindow();
        window.setGravity(Gravity.BOTTOM);
        View view1 = View.inflate(activity, R.layout.dialog_select_task, null);
        RelativeLayout rl_shebei_task = (RelativeLayout) view1
                .findViewById(R.id.rl_shebei_task);
        RelativeLayout rl_wrongshebei_task = (RelativeLayout) view1
                .findViewById(R.id.rl_wrongshebei_task);

        RelativeLayout rl_liucheng_task = (RelativeLayout) view1
                .findViewById(R.id.rl_liucheng_task);
        RelativeLayout rl_close = (RelativeLayout) view1
                .findViewById(R.id.rl_close);
        if (tabindex == 0) {
            rl_liucheng_task.setVisibility(View.VISIBLE);
        } else {
            rl_liucheng_task.setVisibility(View.GONE);
        }
        rl_shebei_task.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


            }
        });
        rl_wrongshebei_task.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

            }
        });
        rl_liucheng_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rl_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectTask.dismiss();
            }
        });

        window.setContentView(view1);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
        mSelectTask.show();
    }

    @Override
    public void initAfter() {
        if (tabindex == 0) {
            getP().getMaillist();
        } else {
            getP().getFromworklist();
        }
    }

    /**
     * @author fuduo
     * @time 2018/1/27  16:50
     * @describe 点击group判断是否全选
     */
    public void groupisCheckall(int position) {
        if (tabindex == 0) {
            WorkerListBean workerListBean = workerbeanlist.get(position);
            if (workerListBean.isIscheckgroup()) {
                workerListBean.setIscheckgroup(false);
                boolean b = checkListIsHaveIsCheck(1);
                if (!b) {
                    ckAllworker.setBackgroundResource(R.mipmap.select_no);
                    ischeck = false;
                }
                adapter.notifyDataSetChanged();
            } else {
                workerListBean.setIscheckgroup(true);
                boolean b = checkListIsHaveIsCheck(1);
                if (b) {
                    ckAllworker.setBackgroundResource(R.mipmap.select_yes);
                    ischeck = true;
                }
                adapter.notifyDataSetChanged();
            }
            setSelectView(1, false);
        } else {
            ProjectFromWorkBean.ListBean listBean = projectFromWorkBeans.get(position);
            if (listBean.isIscheckgroup()) {
                listBean.setIscheckgroup(false);
                boolean nohavecheck = checkListIsHaveIsCheck(2);
                if (!nohavecheck) {
                    ckAllworker.setBackgroundResource(R.mipmap.select_no);
                    ischeck = false;
                }
                fromWorkListExpenableAdapter.notifyDataSetChanged();
            } else {
                listBean.setIscheckgroup(true);
                boolean haveischeck = checkListIsHaveIsCheck(2);
                if (haveischeck) {
                    ckAllworker.setBackgroundResource(R.mipmap.select_yes);
                    ischeck = true;
                }
                fromWorkListExpenableAdapter.notifyDataSetChanged();
            }
            setSelectView(2, false);
        }

    }

    /**
     * @author fuduo
     * @time 2018/1/28  15:20
     * @describe 设置通讯录搜索人员选中
     */
    public void setViewList(List<FindWorkerBean.respBodyBean> list) {
        if (list.size() >= 0) {
            if (findselectlist.size() > 0) {
                findselectlist.clear();
            }
            for (int i = 0; i < list.size(); i++) {
                FindWorkerBean.respBodyBean respBodyBean = list.get(i);
                WorkerBean workerBean = new WorkerBean();
                workerBean.setUserId(respBodyBean.getUserId());
                workerBean.setName(respBodyBean.getText());
                findselectlist.add(workerBean);
            }
            setSelectView(1, true);
        }
    }

    /**
     * @author fuduo
     * @time 2018/1/28  15:20
     * @describe 设置项目搜索人员选中
     */
    public void setProjectViewList(List<FindProjectWorkerBean.RespBodyBean> list) {
        if (list.size() >= 0) {
            if (findprojectselectlist.size() > 0) {
                findprojectselectlist.clear();
            }
            for (int i = 0; i < list.size(); i++) {
                FindProjectWorkerBean.RespBodyBean respBodyBean = list.get(i);
                ProjectFromWorkBean.ListBean.UserListBean listBean=new ProjectFromWorkBean.ListBean.UserListBean();
                listBean.setUserId(respBodyBean.getUserId());
                listBean.setUserName(respBodyBean.getUserName());
                findprojectselectlist.add(listBean);
            }
            setSelectView(2, true);
        }
    }

    /**
     * @author fuduo
     * @time 2018/1/25  11:29
     * @describe 选中的人添加到下面布局中
     */
    private void setSelectView(int m, boolean isfind) {
        if (m == 1) {
            if (selectlist.size() > 0) {
                selectlist.clear();
                if (lineSelecall != null) {
                    lineSelecall.removeAllViews();
                }
            }
            selectlist = adapter.getSelectPeopleList();
            if (isfind) {
                selectlist.addAll(findselectlist);
            }else {
                if (findselectlist.size()>0){
                    selectlist.addAll(findselectlist);
                }
            }

            if (selectlist.size() > 0) {
                rlBottom.setVisibility(View.VISIBLE);
                for (int i = 0; i < selectlist.size(); i++) {
                    WorkerBean workerBean = selectlist.get(i);
                    addChildViewLineLayout(workerBean.getName());
                }
            } else {
                rlBottom.setVisibility(View.GONE);
            }
        } else {
            if (selectprojectlist.size() > 0) {
                selectprojectlist.clear();
                if (lineSelecall != null) {
                    lineSelecall.removeAllViews();
                }
            }
            selectprojectlist = fromWorkListExpenableAdapter.getSelectPeopleList();
            if (isfind) {
                selectprojectlist.addAll(findprojectselectlist);
            }else {
                if (findprojectselectlist.size()>0){
                    selectprojectlist.addAll(findprojectselectlist);
                }
            }
            if (selectprojectlist.size() > 0) {
                rlBottom.setVisibility(View.VISIBLE);
                if (selectlist.size()>0){//清空要加入的list
                    selectlist.clear();
                }
                for (int i = 0; i < selectprojectlist.size(); i++) {
                    //改变选中布局人员
                    ProjectFromWorkBean.ListBean.UserListBean userListBean = selectprojectlist.get(i);
                    WorkerBean workerBean = new WorkerBean();//转为selectlist
                    workerBean.setName(userListBean.getUserName());
                    workerBean.setUserId(userListBean.getUserId());
                    selectlist.add(workerBean);
                    addChildViewLineLayout(userListBean.getUserName());
                }
            } else {
                rlBottom.setVisibility(View.GONE);
            }
        }
    }

    /**
     * @param
     * @name 全部展开
     */
    public void expListExpand(int m) {
        if (m == 1) {
            int count = workerbeanlist.size();
            for (int i = 0; i < count; i++) {
                if (!elv_framework.isGroupExpanded(i)) {
                    elv_framework.expandGroup(i);
                }
            }
        } else {
            int count = projectFromWorkBeans.size();
            for (int i = 0; i < count; i++) {
                if (!elv_framework.isGroupExpanded(i)) {
                    elv_framework.expandGroup(i);
                }
            }
        }

    }


    /**
     * @name 检测是否还有未选中的或者是否已经全选
     */
    private boolean checkListIsHaveIsCheck(int m) {
        if (m == 1) {
            //检测是否全部选中
            int numHave = 0;
            int chlidListSize = 0;
//            int numNoHave = ;
            //           if (checkHaveOrNo) {
            //多套课程的判断
            if (workerbeanlist.size() >= 1) {
                for (int i = 0; i < workerbeanlist.size(); i++) {
                    List<WorkerBean> childList = workerbeanlist.get(i).getWorkerBeanList();
                    if (childList != null && childList.size() > 0) {
                        for (int j = 0; j < childList.size(); j++) {
                            chlidListSize++;
                            if (childList.get(j).isIscheckchild()) {
                                numHave++;
                            }
                        }
                    }
                }
            }

            if (numHave == chlidListSize) {
                return true;
            } else {
                return false;
            }
        } else {
            //检测是否全部选中
            int numHave = 0;
            int chlidListSize = 0;
            //多套课程的判断
            if (projectFromWorkBeans.size() >= 1) {
                for (int i = 0; i < projectFromWorkBeans.size(); i++) {
                    List<ProjectFromWorkBean.ListBean.UserListBean> userList = projectFromWorkBeans.get(i).getUserList();
                    if (userList != null && userList.size() > 0) {
                        for (int j = 0; j < userList.size(); j++) {
                            chlidListSize++;
                            if (userList.get(j).isIscheckchild()) {
                                numHave++;
                            }
                        }
                    }
                }
            }

            if (numHave == chlidListSize) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * @author fuduo
     * @time 2018/1/26  20:39
     * @describe 获取项目架构
     */
    public void getResult(ProjectFromWorkBean projectFromWorkBean) {
        List<ProjectFromWorkBean.ListBean> list = projectFromWorkBean.getList();
        projectFromWorkBeans.addAll(list);
        fromWorkListExpenableAdapter = new FromWorkListExpenableAdapter(getContext(), projectFromWorkBeans);
        elv_framework.setAdapter(fromWorkListExpenableAdapter);
        elv_framework.setGroupIndicator(null);
        fromWorkListExpenableAdapter.setMyOnclicklisttener(new FromWorkListExpenableAdapter.proOnclicklisttener() {
            @Override
            public void Onclicklistener(int position) {
//                setSelectView(2,false);
                groupisCheckall(position);
            }
        });

    }

    /**
     * @author fuduo
     * @time 2018/1/27  21:13
     * @describe 获取搜索通讯录人员
     */
    public void getFindList(FindWorkerBean findWorkerBean) {
        if (findWorkerBean.getRespBodyBeanList().size() > 0) {
            findWorkerBeanList.clear();
            elv_framework.setVisibility(View.GONE);
            lvSearch.setVisibility(View.VISIBLE);
            List<FindWorkerBean.respBodyBean> respBodyBeanList = findWorkerBean.getRespBodyBeanList();
            findWorkerBeanList.addAll(respBodyBeanList);
            selectFindWorkerListAdapter = new SelectFindWorkerListAdapter(getContext(), findWorkerBeanList);
            lvSearch.setAdapter(selectFindWorkerListAdapter);
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
            elv_framework.setVisibility(View.GONE);
            lvSearch.setVisibility(View.VISIBLE);
            List<FindProjectWorkerBean.RespBodyBean> respBodyBeanList = findProjectWorkerBean.getRespBody();
            findWorkerProjectBeanList.addAll(respBodyBeanList);
            selectFindProjectWorkerListAdapter = new SelectFindProjectWorkerListAdapter(getContext(), findWorkerProjectBeanList);
            lvSearch.setAdapter(selectFindProjectWorkerListAdapter);
        }
    }

    /**
     * @author fuduo
     * @time 2018/1/26  20:39
     * @describe 获取通讯录
     */
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
            adapter = new SelectPeopleExpenableAdapter(getContext(), workerbeanlist);
            elv_framework.setAdapter(adapter);
            elv_framework.setGroupIndicator(null);
            adapter.setMyOnclicklisttener(new SelectPeopleExpenableAdapter.myOnclicklisttener() {
                @Override
                public void Onclicklistener(int position) {
//                    setSelectView(1,false);
                    groupisCheckall(position);
                }
            });
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

    @Override
    public int bindLayout() {
        return R.layout.fragement_organization;
    }

    @Override
    public SelectPeoplePresent bindPresent() {
        return new SelectPeoplePresent();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void addChildViewLineLayout(String str) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.maillist_selectpeople_item, null);
        TextView te_select_name = (TextView) view.findViewById(R.id.te_select_name);
        te_select_name.setText(str);
//        view.setTag(infoBean.getDescription());
        lineSelecall.addView(view);
    }
    
}
