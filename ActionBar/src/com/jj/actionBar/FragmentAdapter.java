package com.jj.actionBar;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter{

	private ArrayList<Fragment> fragmentsList;
	public FragmentAdapter(FragmentManager fm,ArrayList<Fragment> fragments) {
		super(fm);
		this.fragmentsList = fragments;
		// TODO Auto-generated constructor stub
	}
	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return fragmentsList.get(arg0);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragmentsList.size();
	}


}
