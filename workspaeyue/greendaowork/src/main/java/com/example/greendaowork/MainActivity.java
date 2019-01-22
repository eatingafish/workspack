package com.example.greendaowork;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.but_add)
    Button butAdd;
    @BindView(R.id.listview)
    ListView listview;
    private DayAdapter dayAdapter;
    ArrayList<dayStep> list = new ArrayList<>();
    private com.example.greendaowork.dayStep dayStep;
    private com.example.greendaowork.dayStepDao dayStepDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //得到数据表（userinfo）的daosession
        DaoSession daoSession = DaoMaster.newDevSession(this, dayStepDao.TABLENAME);
        //得到dao对象进行操作
        dayStepDao = daoSession.getDayStepDao();
         dayAdapter = new DayAdapter(this,list);
         dayAdapter.addList(dayStepDao.loadAll());

         listview.setAdapter(dayAdapter);
        //点击删除
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dayStep item = (com.example.greendaowork.dayStep) dayAdapter.getItem(position);
                dayStepDao.delete(item);
                dayAdapter.removeday(item);
                dayAdapter.notifyDataSetChanged();
            }
        });

        //长按跳转
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                com.example.greendaowork.dayStep item = dayAdapter.getItem(position);
                Message message = new Message();
                message.what = 100;
                message.obj = item;
                EventBus.getDefault().postSticky(message);
                startActivity(new Intent(MainActivity.this,TowActivity.class));
                finish();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dayAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.but_add)
    public void onViewClicked() {
        dayStep daysstep = new dayStep(System.currentTimeMillis(),"司腾师猪","pig");

        dayStepDao.insertOrReplace(daysstep);//添加到数据库
        dayAdapter.addUser(daysstep);
        dayAdapter.notifyDataSetChanged();
    }


}
