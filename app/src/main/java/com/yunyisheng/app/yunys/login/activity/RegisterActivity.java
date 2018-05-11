package com.yunyisheng.app.yunys.login.activity;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.airsaid.pickerviewlibrary.CityPickerView;
import com.airsaid.pickerviewlibrary.listener.OnSimpleCitySelectListener;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.login.model.CityModel;
import com.yunyisheng.app.yunys.login.present.RegisterPresent;
import com.yunyisheng.app.yunys.utils.RegularUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.addressPicker.AddressSelector;
import com.yunyisheng.app.yunys.utils.addressPicker.CityInterface;
import com.yunyisheng.app.yunys.utils.addressPicker.OnItemClickListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2018/1/5.
 */

public class RegisterActivity extends BaseActivity<RegisterPresent> {

    @BindView(R.id.company_name)
    EditText companyName;
    @BindView(R.id.company_lianxi_name)
    EditText companyLianxiName;
    @BindView(R.id.phone_num)
    EditText phoneNum;
    @BindView(R.id.ed_company_email)
    EditText edCompanyEmail;
    @BindView(R.id.ed_company_address_detail)
    EditText edCompanyaddressdetail;
    @BindView(R.id.ed_company_description)
    TextView edCompanyDescription;
    @BindView(R.id.ed_company_address)
    TextView edCompanyAddress;
    @BindView(R.id.line_address)
    LinearLayout lineAddress;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.toLogin)
    TextView toLogin;
    @BindView(R.id.xy)
    RadioButton xy;
    private String enterpriseAddressProvince;
    private String enterpriseAddressDistrict;
    private String enterpriseAddressCity;

    private ArrayList<CityModel.City> cities1 = new ArrayList<>();
    private ArrayList<CityModel.City> cities2 = new ArrayList<>();
    private ArrayList<CityModel.City> cities3 = new ArrayList<>();
    private AddressSelector addressSelector;
    private ArrayList<String> address = new ArrayList<>();
    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initAfter() {
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_register;
    }

    @Override
    public RegisterPresent bindPresent() {
        return new RegisterPresent();
    }

    @Override
    public void setListener() {
        toLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        lineAddress.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.line_address:
//                selectAddress();
                showAddressDialog(RegisterActivity.this);
                break;
            case R.id.toLogin:
                toLoginView();
                break;
            case R.id.btn_register:
                register_company();
                break;
        }
    }
    /**
     * 显示地址选择的dialog
     */
    private void showAddressDialog(final Activity activity) {
        final Dialog addressSelect = new Dialog(activity,R.style.dialog_bottom_full);
        addressSelect.setCanceledOnTouchOutside(true);
        addressSelect.setCancelable(true);
        Window window = addressSelect.getWindow();
        window.setGravity(Gravity.BOTTOM);
        View rootView = View.inflate(activity, R.layout.pop_address_picker, null);
        addressSelector = rootView.findViewById(R.id.apvAddress);
        addressSelector.setTabAmount(3);
        getP().getAddressByPid(0,0);
        addressSelector.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void itemClick(AddressSelector addressSelector, CityInterface city, int tabPosition) {
                switch (tabPosition){
                    case 0:
                        Integer pid = city.getCityPid();
                        getP().getAddressByPid(1,pid);
                        address.clear();
                        address.add(city.getCityName());
                        enterpriseAddressProvince = String.valueOf(city.getCityPid());
                        break;
                    case 1:
                        Integer pid2 = city.getCityPid();
                        getP().getAddressByPid(2,pid2);
                        if (address.size() > 1){
                            address.remove(1);
                        }
                        address.add(city.getCityName());
                        enterpriseAddressCity = String.valueOf(city.getCityPid());
                        break;
                    case 2:
                        if (address.size() > 2){
                            address.remove(2);
                        }
                        address.add(city.getCityName());
//                        ToastUtils.showToast(String.valueOf(address));
                        String allAddress = "";
                        if (address.get(0).equals(address.get(1))){
                            allAddress = address.get(0) + address.get(2);
                        }else{
                            allAddress = address.get(0) + address.get(1) + address.get(2);
                        }
                        edCompanyAddress.setText(allAddress);
                        enterpriseAddressDistrict = String.valueOf(city.getCityPid());
                        addressSelect.hide();
                        break;
                }
            }
        });
        addressSelector.setOnTabSelectedListener(new AddressSelector.OnTabSelectedListener() {
            @Override
            public void onTabSelected(AddressSelector addressSelector, AddressSelector.Tab tab) {
                switch (tab.getIndex()){
                    case 0:
                        addressSelector.setCities(cities1);
                        break;
                    case 1:
                        addressSelector.setCities(cities2);
                        break;
                    case 2:
                        addressSelector.setCities(cities3);
                        break;
                }
            }

            @Override
            public void onTabReselected(AddressSelector addressSelector, AddressSelector.Tab tab) {

            }
        });
        window.setContentView(rootView);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
        addressSelect.show();
    }
    private void selectAddress() {
        CityPickerView mCityPickerView = new CityPickerView(this);
        // 设置点击外部是否消失
        mCityPickerView.setCancelable(true);
        // 设置滚轮字体大小
//        mCityPickerView.setTextSize(18f);
        // 设置标题
//        mCityPickerView.setTitle("我是标题");
        // 设置取消文字
//        mCityPickerView.setCancelText("我是取消文字");
        // 设置取消文字颜色
//        mCityPickerView.setCancelTextColor(Color.GRAY);
        // 设置取消文字大小
//        mCityPickerView.setCancelTextSize(14f);
        // 设置确定文字
//        mCityPickerView.setSubmitText("我是确定文字");
        // 设置确定文字颜色
//        mCityPickerView.setSubmitTextColor(Color.BLACK);
        // 设置确定文字大小
//        mCityPickerView.setSubmitTextSize(14f);
        // 设置头部背景
//        mCityPickerView.setHeadBackgroundColor(Color.RED);
        mCityPickerView.setOnCitySelectListener(new OnSimpleCitySelectListener() {

            @Override
            public void onCitySelect(String prov, String city, String area) {
                // 省、市、区 分开获取
                Log.e(TAG, "省: " + prov + " 市: " + city + " 区: " + area);
                enterpriseAddressProvince = prov;
                if (city==null||city.equals("")){
                    enterpriseAddressCity =enterpriseAddressProvince;
                }else {
                    enterpriseAddressCity = city;
                }
                enterpriseAddressDistrict = area;
            }

            @Override
            public void onCitySelect(String str) {
                edCompanyAddress.setTextColor(getResources().getColor(R.color.color_333));
                edCompanyAddress.setText(str);
            }
        });
        mCityPickerView.show();
    }

    private void register_company() {
        String company_name = companyName.getText().toString().trim();
        String companyAddress = edCompanyAddress.getText().toString().trim();
        String phone = phoneNum.getText().toString().trim();
        String companyDescription = edCompanyDescription.getText().toString().trim();
        String companyEmail = edCompanyEmail.getText().toString().trim();
        String companylianxiName = companyLianxiName.getText().toString().trim();
        String companyaddressdetail = edCompanyaddressdetail.getText().toString().trim();
        if (company_name.isEmpty()) {
            ToastUtils.showToast("公司名称不能为空！");
            return;
        }
        if (companyAddress.equals("请选择企业省市")) {
            ToastUtils.showToast("公司省市不能为空！");
            return;
        }
        if (companyaddressdetail.isEmpty()) {
            ToastUtils.showToast("公司详细地址不能为空！");
            return;
        }
        if (companylianxiName.isEmpty()) {
            ToastUtils.showToast("公司联系人不能为空！");
            return;
        }
        if (phone.isEmpty()) {
            ToastUtils.showToast("手机号不能为空!");
            return;
        }
        if (!RegularUtil.isPhone(phone)) {
            ToastUtils.showToast("手机号格式错误！");
            return;
        }
        if (companyEmail.isEmpty()) {
            ToastUtils.showToast("邮箱不能为空！");
            return;
        }
        if (!RegularUtil.isEmail(companyEmail)) {
            ToastUtils.showToast("邮箱格式错误！");
            return;
        }
        if (companyDescription.isEmpty()) {
            ToastUtils.showToast("请填写公司需求！");
            return;
        }
        if (!xy.isChecked()){
            ToastUtils.showToast("请同意用户协议");
            return;
        }
        getP().registerCompany(company_name, enterpriseAddressProvince, enterpriseAddressCity,
                enterpriseAddressDistrict, companyaddressdetail, companylianxiName, phone, companyEmail, companyDescription);
    }

    private void toLoginView() {
        Router.newIntent(context)
                .to(LoginActivity.class)
                .launch();
        this.finish();
    }

    public void checkRegiterInfo(BaseModel baseStatusModel) {
        if (baseStatusModel.getRespCode() != 0) {
            ToastUtils.showToast(baseStatusModel.getRespMsg());
            return;
        } else {
            ToastUtils.showToast("注册成功,请耐心等待审核！");
            toLoginView();
        }
    }


    public void setCities(int level, CityModel cityModel) {
        switch (level){
            case 0:
                cities1 = (ArrayList<CityModel.City>) cityModel.getRespBody();
                addressSelector.setCities(cities1);
                break;
            case 1:
                cities2 = (ArrayList<CityModel.City>) cityModel.getRespBody();
                addressSelector.setCities(cities2);
                break;
            case 2:
                cities3 = (ArrayList<CityModel.City>) cityModel.getRespBody();
                addressSelector.setCities(cities3);
                break;
        }
    }
}
