package com.jj.actionBar;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.ImageView;

public class ActionBarActivity extends FragmentActivity implements OnFocusChangeListener {
	private ActionBar actionBar;
	public static ImageView tv1, tv2, tv3, tv4;
	//加载完成标志位，通过它控制onFocusChanged函数的调用时机（一开始直接调用onFocusChanged函数必崩溃，必须等加载完成）
	private boolean Loaded = false;
	private ViewPager viewPager;//页卡内容
	private ArrayList<Fragment> fragmentsList;// Tab页面列表

	/***
	 * init View
	 */
	public void InitView() {
		LayoutInflater.from(ActionBarActivity.this).inflate(
				R.layout.action_bar, null, true);
		tv1 = (ImageView) findViewById(R.id.tv1);
		tv2 = (ImageView) findViewById(R.id.tv2);
		tv3 = (ImageView) findViewById(R.id.tv3);
		tv4 = (ImageView) findViewById(R.id.tv4);
		
		tv1.setOnFocusChangeListener(this);
		tv2.setOnFocusChangeListener(this);
		tv3.setOnFocusChangeListener(this);
		tv4.setOnFocusChangeListener(this);
		

		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		actionBar = (ActionBar) findViewById(R.id.bottomBar1);
		InitView();
		InitViewPager();
	}
	
	
	private void InitViewPager() {
		viewPager=(ViewPager) findViewById(R.id.vPager);
		fragmentsList = new ArrayList<Fragment>();//添加Fragment到list
		TvFragment tvFragment=new TvFragment();
		AppFragment appFragment=new AppFragment();
		SetingFragment setingFragment=new SetingFragment();
		UsbFragment usbFragment=new UsbFragment();
		fragmentsList.add(tvFragment);
		fragmentsList.add(appFragment);
		fragmentsList.add(setingFragment);
		fragmentsList.add(usbFragment);
		viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),fragmentsList));
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}
	
	public class MyOnPageChangeListener implements OnPageChangeListener{

		public void onPageScrollStateChanged(int arg0) {


		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {


		}

		public void onPageSelected(int arg0) {
			if(arg0 == 0){
				actionBar.targetRect(tv1);
			}
			else if(arg0 == 1){
				actionBar.targetRect(tv2);
			}
			else if(arg0 == 2){
				actionBar.targetRect(tv3);
			}
			else{
				actionBar.targetRect(tv4);
			}
		}

	}


   public void onFocusChange(View v, boolean hasFocus){

	   if(v.hasFocus()){
	   switch (v.getId()) {
		case R.id.tv1:
			viewPager.setCurrentItem(0);
			break;
		case R.id.tv2:
			viewPager.setCurrentItem(1);
			break;
		case R.id.tv3:
			viewPager.setCurrentItem(2);
			break;

		case R.id.tv4:
			viewPager.setCurrentItem(3);
			break;
		default:
			break;
		}
	   }
	   if(v.hasFocus()){
		   
			if(Loaded){actionBar.targetRect(v);}
		}
   }
	
	//按键按下以后，将Loaded标志位设置为true，onFocusChange函数得以正常运行。
   public boolean dispatchKeyEvent(KeyEvent event) {
	   if(event.getAction() == KeyEvent.ACTION_DOWN){
		   Loaded = true;
	   }
         return super.dispatchKeyEvent(event);
   }
   

}