package com.example.greendaowork;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TowActivity extends AppCompatActivity {

    @BindView(R.id.back)
    Button back;
    @BindView(R.id.but_save)
    Button butSave;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    private dayStep daystep;
    private long id;
    private com.example.greendaowork.dayStepDao dayStepDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tow);
        ButterKnife.bind(this);
        EventBus.getDefault().register(TowActivity.this);

        DaoSession daoSession = DaoMaster.newDevSession(this, dayStepDao.TABLENAME);
        dayStepDao = daoSession.getDayStepDao();
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getEventBus(Message message){
        if (message.what == 100)
        {
            daystep = (dayStep) message.obj;
            editName.setText(daystep.getName());
            editPwd.setText(daystep.getAge());
        }

    }

    @OnClick({R.id.back, R.id.but_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                startActivity(new Intent(TowActivity.this,MainActivity.class));
                finish();
                break;
            case R.id.but_save:
                String editname = editName.getText().toString();
                String editpwd = editPwd.getText().toString();
                daystep.setName(editname);
                daystep.setAge(editpwd);
                Toast.makeText(this, daystep.getName()+"   "+daystep.getAge(), Toast.LENGTH_SHORT).show();
                dayStepDao.update(daystep);
                startActivity(new Intent(TowActivity.this,MainActivity.class));
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
