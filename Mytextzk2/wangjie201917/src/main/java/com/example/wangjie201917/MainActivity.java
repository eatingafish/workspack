package com.example.wangjie201917;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bean.DataInfo;
import com.example.bean.Result;
import com.example.presenter.LoginPresenter;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editphone)
    EditText editphone;
    @BindView(R.id.editpwd)
    EditText editpwd;
    @BindView(R.id.but_login)
    Button butLogin;
    @BindView(R.id.check)
    CheckBox check;
    @BindView(R.id.textzc)
    TextView textzc;
    @BindView(R.id.checkeye)
    CheckBox checkeye;
    private LoginPresenter loginPresenter;
    private String phoness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        phoness = editphone.getText().toString().trim();
        loginPresenter = new LoginPresenter(new getData());
        //点击切换密码
        checkeye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    editpwd.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                }
                else {
                    editpwd.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                }
            }
        });
    }

    /**
     * 点击登录
     */
    @OnClick(R.id.but_login)
    public void onViewClicked() {
        String editphones = editphone.getText().toString().trim();
        String editpwds = editpwd.getText().toString().trim();
        if (editphones.equals("") && editpwds.equals("")) {
            Toast.makeText(this, "请填写信息", Toast.LENGTH_SHORT).show();
            return;
        } else if (editphones.equals("")) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        } else if (editpwds.equals("")) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        loginPresenter.request(editphones, editpwds);
        startActivity(new Intent(MainActivity.this, ShowActivity.class));

    }

    //点击跳转到注册页面
    @OnClick(R.id.textzc)
    public void onViewC() {
        startActivity(new Intent(MainActivity.this, RegActivity.class));
        finish();
    }

    //正则表达式
    public static boolean checkphone(String phoness)
    {
        String regex = "(\\+\\d+)?1[3458]\\d{9}";
        return Pattern.matches(regex,phoness);
    }

    private class getData implements Consumer<Result<DataInfo>> {
        @Override
        public void accept(Result<DataInfo> dataInfoResult) throws Exception {
            Toast.makeText(MainActivity.this, "" + dataInfoResult.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.unBind();
    }
}
