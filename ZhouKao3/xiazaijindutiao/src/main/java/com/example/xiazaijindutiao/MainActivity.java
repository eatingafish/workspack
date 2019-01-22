package com.example.xiazaijindutiao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {


	private DownloadUtils downloadUtils;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.btnDown).setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		downloadUtils = new DownloadUtils(MainActivity.this);
		downloadUtils.downloadAPK("http://gdown.baidu.com/data/wisegame/55dc62995fe9ba82/jinritoutiao_448.apk", "jinritoutiao_448.apk");
	}
}
