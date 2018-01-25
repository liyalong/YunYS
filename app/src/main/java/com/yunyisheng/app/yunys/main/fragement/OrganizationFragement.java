package com.yunyisheng.app.yunys.main.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.main.adapter.SelectPeopleExpenableAdapter;
import com.yunyisheng.app.yunys.main.model.WorkerBean;
import com.yunyisheng.app.yunys.main.model.WorkerListBean;
import com.yunyisheng.app.yunys.main.present.SelectPeoplePresent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：fuduo on 2018/1/12 10:15
 * 邮箱：duoendeavor@163.com
 * 用途：选人fragement
 */

public class OrganizationFragement extends BaseFragement<SelectPeoplePresent> {

    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.ck_allworker)
    CheckBox ckAllworker;
    @BindView(R.id.elv_framework)
    ExpandableListView elv_framework;
    Unbinder unbinder;
    @BindView(R.id.line_selecall)
    LinearLayout lineSelecall;
    @BindView(R.id.btn_queren)
    Button btnQueren;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    @BindView(R.id.scro_all)
    HorizontalScrollView scroAll;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    private int tabindex;
    private List<WorkerBean> selectlist = new ArrayList<>();
    private Map<Integer, WorkerBean> workerBeanMaplist = new HashMap<>();
    private List<WorkerListBean> workerbeanlist = new ArrayList<>();
    private SelectPeopleExpenableAdapter adapter;
    //子集合的item的数量
    private int chlidListSize = 0;

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
        btnQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectlist.size() > 0) {
                    JSONObject object = new JSONObject();
                    JSONArray array = new JSONArray();
                    for (int i = 0; i < selectlist.size(); i++) {
                        WorkerBean workerBean = selectlist.get(i);
                        String userid= workerBean.getUserId()+"";
                        array.put(userid);
                    }
                    try {
                        object.put("1", array);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String str = object.toString();
                    Log.i("stridnifn", str);
                    Intent intent=getActivity().getIntent();
                    intent.putExtra("size",selectlist.size());
                    intent.putExtra("selectjson",str);
                    getActivity().setResult(8,intent);
                    getActivity().finish();
                }
            }
        });
        imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edSearch.setText("");
            }
        });

        ckAllworker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                adapter.setAllCheckandNocheck(isChecked);
                if (isChecked) {
                    rlBottom.setVisibility(View.VISIBLE);
                } else {
                    rlBottom.setVisibility(View.GONE);
                }
                expListExpand();
                setSelectView();
            }
        });
        elv_framework.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                WorkerBean workerBean = workerbeanlist.get(groupPosition).getWorkerBeanList().get(childPosition);
                if (workerBean.isIscheckchild()) {
                    workerBean.setIscheckchild(false);
                    boolean nohavecheck = checkListIsHaveIsCheck(false);
                    if (nohavecheck) {
                        rlBottom.setVisibility(View.GONE);
                    }
                    adapter.notifyDataSetChanged();
                    // workerBeanMaplist.remove(childPosition);
                } else {
                    workerBean.setIscheckchild(true);
                    adapter.notifyDataSetChanged();
                    //workerBeanMaplist.put(childPosition,workerBean);
                    boolean haveischeck = checkListIsHaveIsCheck(true);
                }

                // expListExpand();
                setSelectView();
                return true;
            }
        });
    }

    @Override
    public void initAfter() {
        getP().getMaillist();
    }

    /**
     * @author fuduo
     * @time 2018/1/25  11:29
     * @describe 选中的人添加到下面布局中
     */
    private void setSelectView() {
        if (selectlist.size() > 0) {
            selectlist.clear();
            if (lineSelecall != null) {
                lineSelecall.removeAllViews();
            }
        }
        selectlist = adapter.getSelectPeopleList();
        if (selectlist.size() > 0) {
            rlBottom.setVisibility(View.VISIBLE);
            for (int i = 0; i < selectlist.size(); i++) {
                WorkerBean workerBean = selectlist.get(i);
                addChildViewLineLayout(workerBean.getName());
            }
        } else {
            rlBottom.setVisibility(View.GONE);
        }
    }

    /**
     * @param
     * @name 全部展开
     */
    public void expListExpand() {
        int count = workerbeanlist.size();
        for (int i = 0; i < count; i++) {
            if (!elv_framework.isGroupExpanded(i)) {
                elv_framework.expandGroup(i);
            }
        }
    }

    /**
     * @param checkHaveOrNo 检测有还是检测没有
     * @name 检测是否还有未选中的或者是否已经全选
     */
    private boolean checkListIsHaveIsCheck(boolean checkHaveOrNo) {
        //检测是否全部选中
        int numHave = 0;
        int numNoHave = chlidListSize;
        if (checkHaveOrNo) {
            //多套课程的判断
            if (workerbeanlist.size() > 1) {
                for (int i = 0; i < workerbeanlist.size(); i++) {
                    List<WorkerBean> childList = workerbeanlist.get(i).getWorkerBeanList();
                    if (childList!=null&&childList.size()>0) {
                        for (int j = 0; j < childList.size(); j++) {
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

        }
        //检测是否全部未选中
        else {
            //多套课程的判断
            if (workerbeanlist.size() > 1) {
                for (int i = 0; i < workerbeanlist.size(); i++) {
                    List<WorkerBean> childList = workerbeanlist.get(i).getWorkerBeanList();
                    if (childList.size()>0) {
                        for (int j = 0; j < childList.size(); j++) {
                            if (!childList.get(j).isIscheckchild()) {
                                numNoHave--;
                            }
                        }
                    }
                }
            }

            if (numHave != chlidListSize) {
                return true;
            } else {
                return false;
            }
        }
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
                public void Onclicklistener() {
                    setSelectView();
                }
            });
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

    private void addChildViewLineLayout(String str) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.maillist_selectpeople_item, null);
        TextView te_select_name = (TextView) view.findViewById(R.id.te_select_name);
        te_select_name.setText(str);
//        view.setTag(infoBean.getDescription());
        lineSelecall.addView(view);
    }

}
