package com.example.mypage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bean.LoginReg.LoginInfo;
import com.example.bean.Result;
import com.example.core.DataCall;
import com.example.core.exception.ApiException;
import com.example.dao.DaoMaster;
import com.example.dao.DaoSession;
import com.example.dao.LoginInfoDao;
import com.example.myfirstp.R;
import com.example.presenter.mypage.AddAddressPresenter;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAddress extends AppCompatActivity {

    @BindView(R.id.address_name)
    EditText addressName;
    @BindView(R.id.address_phone)
    EditText addressPhone;
    @BindView(R.id.address_ads)
    EditText addressAds;
    @BindView(R.id.address_iv)
    ImageView addressIv;
    @BindView(R.id.address_xiangxi)
    EditText addressXiangxi;
    @BindView(R.id.address_youbian)
    EditText addressYoubian;
    @BindView(R.id.button_save)
    Button buttonSave;
    private AddAddressPresenter addAddressPresenter;
    private List<LoginInfo> loginInfos;
    private LoginInfoDao loginInfoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);

        DaoSession daoSession = DaoMaster.newDevSession(AddAddress.this, LoginInfoDao.TABLENAME);
        loginInfoDao = daoSession.getLoginInfoDao();

        addAddressPresenter = new AddAddressPresenter(new getData());

    }

    //点击选择 省 市
    @OnClick(R.id.address_iv)
    public void onViewClicked() {
        CityPicker cityPicker = new CityPicker.Builder(AddAddress.this)
                .textSize(20)//地址选择
                .title("地址选择")
                .backgroundPop(0xa0000000)
                //文字的颜色
                .titleBackgroundColor("#0CB6CA")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                //滑轮文字的颜色
                .textColor(Color.parseColor("#000000"))
                //省滑轮是否循环显示
                .provinceCyclic(true)
                //市滑轮是否循环显示
                .cityCyclic(false)
                //地区（县）滑轮是否循环显示
                .districtCyclic(false)
                //滑轮显示的item个数
                .visibleItemsCount(7)
                //滑轮item间距
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();

        cityPicker.show();

        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省
                String province = citySelected[0];
                //市
                String city = citySelected[1];
                //区。县。（两级联动，必须返回空）
                String district = citySelected[2];
                //邮证编码
                String code = citySelected[3];
                addressAds.setText(province + city + district);
            }

            @Override
            public void onCancel() {

            }
        });
    }

    /**
     * 点击添加
     */
    @OnClick(R.id.button_save)
    public void add() {

        String realname = addressName.getText().toString();
        String realphone = addressPhone.getText().toString();
        String ads = addressAds.getText().toString();
        String youbian = addressYoubian.getText().toString();

        loginInfos = loginInfoDao.loadAll();
        int userId = (int) loginInfos.get(0).getUserId();
        String sessionId = loginInfos.get(0).getSessionId();


        addAddressPresenter.reqeust(userId,sessionId,realname,realphone,ads,youbian);

    }

    private class getData implements DataCall<Result> {
        @Override
        public void success(Result data) {
            if (data.getStatus().equals("0000")) {
                Toast.makeText(AddAddress.this, "添加成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddAddress.this,MyAddressActivity.class));
                finish();
            }
            else {
                Toast.makeText(AddAddress.this, "添加失败"+data.getMessage(), Toast.LENGTH_SHORT).show();
            }
            }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(AddAddress.this, "失败"+e.getCode(), Toast.LENGTH_SHORT).show();
        }
    }
}
