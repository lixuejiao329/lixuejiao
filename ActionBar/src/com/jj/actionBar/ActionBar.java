package com.jj.actionBar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/***
 * 自定义控件
 * 
 * @author zhangjia
 * 
 *         在这里我要说明一点 我们在创建RectF矩形的时候，
 * 
 *         参照物原点是所在"父控件的左上角".
 * 
 */
public class ActionBar extends LinearLayout {

	private Paint paint;// 画笔
	private RectF curRectF;// draw当前bar
	private RectF tarRectF;// draw被点击bar

	private final int space_x = 5;// 相当于pading.
	private final int space_y = 3;// 相当于pading
	private final double step = 32;// 速度step.

	public ActionBar(Context context) {
		super(context);
	}

	/***
	 * 构造方法
	 * 
	 * @param context
	 * @param attrs
	 */
	public ActionBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		setWillNotDraw(false);
		LayoutInflater.from(context).inflate(R.layout.action_bar, this, true);
		paint = new Paint();
		paint.setAntiAlias(true);

		curRectF = null;
		tarRectF = null;
	}

	/***
	 * invalidate()：调用这个方法会执行onDraw()方法，但是前提是：自己把invalidate()方法执行结束在进行执行.
	 */

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		paint.setColor(Color.RED);
		// 如果当前curRectF=null，也就是第一次访问，则默认为draw第一个bar
		if (curRectF == null)
			curRectF = new RectF(ActionBarActivity.tv1.getLeft() + space_x, ActionBarActivity.tv1.getTop()
					+ space_y, ActionBarActivity.tv1.getRight() - space_x, ActionBarActivity.tv1.getBottom()
					- space_y);

		// 第一次方位tarRectF=null，默认为draw
		if (tarRectF == null)
			tarRectF = new RectF(ActionBarActivity.tv1.getLeft() + space_x, ActionBarActivity.tv1.getTop()
					+ space_y, ActionBarActivity.tv1.getRight() - space_x, ActionBarActivity.tv1.getBottom()
					- space_y);
		/***
		 * 作用：如果在这个范围内则，以这个为最终位置，（不明的白的话，你可以把这个注释运行下你就知道why了.）
		 */
		if (Math.abs(curRectF.left - tarRectF.left) < step) {
			curRectF.left = tarRectF.left;
			curRectF.right = tarRectF.right;
		}

		/***
		 * 说明目标在当前的左侧,需要向左移动（每次矩形移动step，则进行invalidate（）,从新进行移动...）
		 */
		if (curRectF.left > tarRectF.left) {
			curRectF.left -= step;
			curRectF.right -= step;
			invalidate();// 继续刷新，从而实现滑动效果，每次step32.
		}
		/***
		 * 说明目标在当前的右侧,需要向右移动（每次矩形移动step，则进行invalidate（）,从新进行移动...）
		 */
		else if (curRectF.left < tarRectF.left) {
			curRectF.left += step;
			curRectF.right += step;
			invalidate();
		}
		// 参数，矩形，弧度，画笔
		canvas.drawRoundRect(curRectF, 5, 5, paint);
	}
	
	/****
	 * 这里要记录目标矩形的坐标
	 * @param v 获取View的长度宽度，然后可以绘制移动红色光标
	 * 
	 */
	public void targetRect(final View v){
		tarRectF.left = v.getLeft() + space_x;
		tarRectF.right = v.getRight() - space_x;
		invalidate();// 刷新
	}

}
