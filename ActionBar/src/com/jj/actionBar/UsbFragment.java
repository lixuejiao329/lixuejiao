package com.jj.actionBar;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UsbFragment extends Fragment{
	private View view;

	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view=inflater.inflate(R.layout.lay4, container, false);
		return view;
	}

	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}   
}
