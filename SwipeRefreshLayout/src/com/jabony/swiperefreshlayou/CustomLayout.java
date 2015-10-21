package com.jabony.swiperefreshlayou;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.jabony.swiperefreshlayout.R;

public class CustomLayout extends SwipeRefreshLayout implements OnScrollListener
{

	/** 
     * 滑动到最下面时的上拉操作 
     */  
  
    private int mTouchSlop;  
    /** 
     * listview实例 
     */  
    private ListView mListView;  
  
    /** 
     * 上拉监听器, 到了最底部的上拉加载操作 
     */  
    private OnLoadListener mOnLoadListener;  
  
    /** 
     * ListView的加载中footer 
     */  
    private View mListViewFooter;  
  
    /** 
     * 按下时的y坐标 
     */  
    private int mYDown;  
    /** 
     * 抬起时的y坐标, 与mYDown一起用于滑动到底部时判断是上拉还是下拉 
     */  
    private int mLastY;  
    /** 
     * 是否在加载中 ( 上拉加载更多 ) 
     */  
    private boolean isLoading = false;  
  
	
	
	
	public CustomLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();  
        
        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.listview_footer, null,  
                false);  
	}

	public CustomLayout(Context context)
	{
		super(context);
	}
	
	
	
    @Override  
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {  
        super.onLayout(changed, left, top, right, bottom);  
  
        Log.d("test", "onLayout"); 
        // 初始化ListView对象  
        if (mListView == null) {  
            getListView();  
            Log.d("test", "onLayout"); 
        }  
    }  
  
    /** 
     * 获取ListView对象 
     */  
    private void getListView() {  
        int childs = getChildCount();  
        if (childs > 0) {  
            View childView = getChildAt(0);  
            if (childView instanceof ListView) {  
                mListView = (ListView) childView;  
                // 设置滚动监听器给ListView, 使得滚动的情况下也可以自动加载  
                mListView.setOnScrollListener(this);  
                Log.d("test", "getListView()----找到listview");  
            }  
        }  
    }  
  
    /* 
     * (non-Javadoc) 
     * @see android.view.ViewGroup#dispatchTouchEvent(android.view.MotionEvent) 
     */  
    @Override  
    public boolean dispatchTouchEvent(MotionEvent event) {  
        final int action = event.getAction();  
  
        switch (action) {  
            case MotionEvent.ACTION_DOWN:  
                // 按下  
                mYDown = (int) event.getRawY();  
                Log.d("test", "ACTION_DOWN");  
                break;  
  
            case MotionEvent.ACTION_MOVE:  
                // 移动  
                mLastY = (int) event.getRawY();  
                Log.d("test", "ACTION_MOVE");  
                break;  
  
            case MotionEvent.ACTION_UP:  
            	Log.d("test", "ACTION_UP");  
                // 抬起  
                if (canLoad()) {  
                    loadData();  
                }  
                break;  
            default:  
                break;  
        }  
  
        return super.dispatchTouchEvent(event);  
    }  
  
    /** 
     * 是否可以加载更多, 条件是到了最底部, listview不在加载中, 且为上拉操作. 
     *  
     * @return 
     */  
    private boolean canLoad() {  
        return isBottom() && !isLoading && isPullUp();  
    }  
  
    /** 
     * 判断是否到了最底部 
     */  
    private boolean isBottom() {  
  
        if (mListView != null && mListView.getAdapter() != null) {  
            return mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);  
        }  
        return false;  
    }  
  
    /** 
     * 是否是上拉操作 
     *  
     * @return 
     */  
    private boolean isPullUp() {  
        return (mYDown - mLastY) >= mTouchSlop;  
    }  
  
    /** 
     * 如果到了最底部,而且是上拉操作.那么执行onLoad方法 
     */  
    private void loadData() {  
    	Log.d("test", "loadData()");  
        if (mOnLoadListener != null) {  
            // 设置状态  
            setLoading(true);  
            //  
            mOnLoadListener.onLoad();  
        }  
    }  
  
    /** 
     * @param loading 
     */  
    public void setLoading(boolean loading) {  
        isLoading = loading;  
        if (isLoading) {  
//            mListView.addFooterView(mListViewFooter);  
        } else {  
//            mListView.removeFooterView(mListViewFooter);  
            mYDown = 0;  
            mLastY = 0;  
        }  
    }  
  
    /** 
     * @param loadListener 
     */  
    public void setOnLoadListener(OnLoadListener loadListener) {  
        mOnLoadListener = loadListener;  
    }  
  
    //滑动时触发的方法
    @Override  
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,  
            int totalItemCount) {  
        // 滚动时到了最底部也可以加载更多  
        if (canLoad()) {  
            loadData();  
        }  
    }  
  
    /** 
     * 加载更多的监听器 
     *  
     * @author mrsimple 
     */  
    public static interface OnLoadListener {  
        public void onLoad();  
    }  
//
//	
//	@Override
//	protected void onScrollChanged(int l, int t, int oldl, int oldt)
//	{
//		super.onScrollChanged(l, t, oldl, oldt);
//		
//		
//		
//	}
//	
//	@Override
//	protected void onLayout(boolean changed, int left, int top, int right,
//			int bottom)
//	{
//		// TODO Auto-generated method stub
//		super.onLayout(changed, left, top, right, bottom);
//	}
//	
//	interface OnLoadListener{
//		void onLoad();
//	}
//	
//	interface OnScrollChanged{
//		 void onScrollChanged(int l, int t, int oldl, int oldt);
//	}
//
//	
//	@Override
//	public boolean dispatchTouchEvent(MotionEvent event)
//	{
//		switch (event.getAction())
//		{
//			case MotionEvent.ACTION_UP:
//				System.out.println(event.getX()+"----"+event.getY());
//				
//				break;
//			case MotionEvent.ACTION_DOWN:
//				System.out.println(event.getX()+"******"+event.getY());
//				break;
//			case MotionEvent.ACTION_MOVE:
//				System.out.println(event.getRawX()+"######"+event.getRawY());
//				break;
//
//			default:
//				break;
//		}
//		System.out.println("-----------------------");
//		return super.dispatchTouchEvent(event);
//	}
//	

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState)
	{
		// TODO Auto-generated method stub
		
	}
}
