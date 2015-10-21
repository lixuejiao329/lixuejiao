package com.jabony.swiperefreshlayou;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.jabony.swiperefreshlayout.R;

@SuppressLint("InlinedApi")
public class SwipRefreshLayoutActivity extends Activity implements
		SwipeRefreshLayout.OnRefreshListener,CustomLayout.OnLoadListener {
	private CustomLayout swipeLayout;
	private ListView listView;
	private ListViewAdapter adapter;
	private ArrayList<SoftwareClassificationInfo> list;
	
	private int count=0;
	private View footer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.swipe_refresh_layout);
		swipeLayout = (CustomLayout) findViewById(R.id.swipe_container);
		swipeLayout.setOnRefreshListener(this);
		swipeLayout.setOnLoadListener(this);
		//加载颜色是循环播放的，只要没有完成刷新就会一直循环，color1>color2>color3>color4
		swipeLayout.setColorScheme(android.R.color.white,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);
		
		footer = findViewById(R.layout.listview_footer);  
//		
//		footer = LayoutInflater.from(this).inflate(R.layout.listview_footer, null,  
//				false);  

		list = new ArrayList<SoftwareClassificationInfo>();
		list.add(new SoftwareClassificationInfo(1, "asdas"));
		listView = (ListView) findViewById(R.id.list);
		
		adapter = new ListViewAdapter(this, list);
		listView.setAdapter(adapter);
		
		
		
	}

	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {
			public void run() {
				swipeLayout.setRefreshing(false);
				list.clear();
				list.add(new SoftwareClassificationInfo(2, "assdadadasdasd"));
				adapter.notifyDataSetChanged();
			}
		}, 3000);
	}

	@Override
	public void onLoad()
	{
		count++;
//		footer.setVisibility(View.VISIBLE);
		new Handler().postDelayed(new Runnable() {
			public void run() {
				list.add(new SoftwareClassificationInfo(2, "ass"+count));
				adapter.notifyDataSetChanged();
				swipeLayout.setLoading(false);
//				footer.setVisibility(View.GONE);
			}
		}, 3000);
	}
}
