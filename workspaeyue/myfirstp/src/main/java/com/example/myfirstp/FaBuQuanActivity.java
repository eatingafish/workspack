package com.example.myfirstp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.adapter.AddFlyAdapter;
import com.example.bean.LoginReg.LoginInfo;
import com.example.bean.Result;
import com.example.core.DataCall;
import com.example.core.exception.ApiException;
import com.example.dao.DaoMaster;
import com.example.dao.DaoSession;
import com.example.dao.LoginInfoDao;
import com.example.presenter.circle.FbQzPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FaBuQuanActivity extends AppCompatActivity {

    @BindView(R.id.miv_back)
    ImageView mivBack;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.miv_send)
    ImageView mivSend;
    @BindView(R.id.relativeLayout1)
    RelativeLayout relativeLayout1;
    @BindView(R.id.met_text)
    EditText metText;
    @BindView(R.id.mrv_image)
    RecyclerView mrvImage;
    @BindView(R.id.bo_address)
    TextView boAddress;
    List<Object> objects = new ArrayList<>();
    String sessionId;
    long userId;
    private FbQzPresenter fbQzPresenter;
    private AddFlyAdapter addFlyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_bu_quan);
        ButterKnife.bind(this);
        objects.add(R.drawable.qiu);
        DaoSession daoSession = DaoMaster.newDevSession(this, LoginInfoDao.TABLENAME);
        LoginInfoDao loginInfoDao = daoSession.getLoginInfoDao();
        List<LoginInfo> users = loginInfoDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            sessionId = users.get(i).getSessionId();
            userId = users.get(i).getUserId();
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4 );
        mrvImage.setLayoutManager(gridLayoutManager);
        fbQzPresenter = new FbQzPresenter(new SuccDataCall());

        addFlyAdapter = new AddFlyAdapter(objects, this, new AddFlyAdapter.Open() {
            @Override
            public void onDakaiXiangCe() {
                Intent intent1 = new Intent(Intent.ACTION_PICK);
                intent1.setType("image/*");
                startActivityForResult(intent1,0);
            }
        });
        mrvImage.setAdapter(addFlyAdapter);

    }

    @OnClick({R.id.miv_back, R.id.miv_send, R.id.mrv_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.miv_back:
                finish();

                break;
            case R.id.miv_send:

                String trim = metText.getText().toString().trim();
                fbQzPresenter.reqeust((int)userId,sessionId,1,trim,objects);
                finish();


                break;
            case R.id.mrv_image:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            return;
        }
        if(requestCode==0){
            String filePath = getFilePath(null,requestCode,data);
            objects.add(filePath);
            addFlyAdapter.notifyDataSetChanged();
        }
    }

    private class SuccDataCall implements DataCall<Result> {
        @Override
        public void success(Result data) {
            if(data.getStatus().equals("0000")){

            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    /**
     * 得到图片的路径
     *
     * @param fileName
     * @param requestCode
     * @param data
     * @return
     */
    public String getFilePath(String fileName, int requestCode, Intent data) {
        if (requestCode == 1) {
            return fileName;
        } else if (requestCode == 0) {
            Uri uri = data.getData();
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor actualimagecursor = managedQuery(uri, proj, null, null, null);
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            String img_path = actualimagecursor
                    .getString(actual_image_column_index);
            // 4.0以上平台会自动关闭cursor,所以加上版本判断,OK
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH)
                actualimagecursor.close();
            return img_path;
        }
        return null;
    }
}
