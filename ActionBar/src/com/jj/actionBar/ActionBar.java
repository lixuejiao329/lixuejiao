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
 * �Զ���ؼ�
 * 
 * @author zhangjia
 * 
 *         ��������Ҫ˵��һ�� �����ڴ���RectF���ε�ʱ��
 * 
 *         ������ԭ��������"���ؼ������Ͻ�".
 * 
 */
public class ActionBar extends LinearLayout {

	private Paint paint;// ����
	private RectF curRectF;// draw��ǰbar
	private RectF tarRectF;// draw�����bar

	private final int space_x = 5;// �൱��pading.
	private final int space_y = 3;// �൱��pading
	private final double step = 32;// �ٶ�step.

	public ActionBar(Context context) {
		super(context);
	}

	/***
	 * ���췽��
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
	 * invalidate()���������������ִ��onDraw()����������ǰ���ǣ��Լ���invalidate()����ִ�н����ڽ���ִ��.
	 */

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		paint.setColor(Color.RED);
		// �����ǰcurRectF=null��Ҳ���ǵ�һ�η��ʣ���Ĭ��Ϊdraw��һ��bar
		if (curRectF == null)
			curRectF = new RectF(ActionBarActivity.tv1.getLeft() + space_x, ActionBarActivity.tv1.getTop()
					+ space_y, ActionBarActivity.tv1.getRight() - space_x, ActionBarActivity.tv1.getBottom()
					- space_y);

		// ��һ�η�λtarRectF=null��Ĭ��Ϊdraw
		if (tarRectF == null)
			tarRectF = new RectF(ActionBarActivity.tv1.getLeft() + space_x, ActionBarActivity.tv1.getTop()
					+ space_y, ActionBarActivity.tv1.getRight() - space_x, ActionBarActivity.tv1.getBottom()
					- space_y);
		/***
		 * ���ã�����������Χ���������Ϊ����λ�ã��������İ׵Ļ�������԰����ע�����������֪��why��.��
		 */
		if (Math.abs(curRectF.left - tarRectF.left) < step) {
			curRectF.left = tarRectF.left;
			curRectF.right = tarRectF.right;
		}

		/***
		 * ˵��Ŀ���ڵ�ǰ�����,��Ҫ�����ƶ���ÿ�ξ����ƶ�step�������invalidate����,���½����ƶ�...��
		 */
		if (curRectF.left > tarRectF.left) {
			curRectF.left -= step;
			curRectF.right -= step;
			invalidate();// ����ˢ�£��Ӷ�ʵ�ֻ���Ч����ÿ��step32.
		}
		/***
		 * ˵��Ŀ���ڵ�ǰ���Ҳ�,��Ҫ�����ƶ���ÿ�ξ����ƶ�step�������invalidate����,���½����ƶ�...��
		 */
		else if (curRectF.left < tarRectF.left) {
			curRectF.left += step;
			curRectF.right += step;
			invalidate();
		}
		// ���������Σ����ȣ�����
		canvas.drawRoundRect(curRectF, 5, 5, paint);
	}
	
	/****
	 * ����Ҫ��¼Ŀ����ε�����
	 * @param v ��ȡView�ĳ��ȿ�ȣ�Ȼ����Ի����ƶ���ɫ���
	 * 
	 */
	public void targetRect(final View v){
		tarRectF.left = v.getLeft() + space_x;
		tarRectF.right = v.getRight() - space_x;
		invalidate();// ˢ��
	}

}
