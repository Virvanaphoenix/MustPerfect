/**
 *
 */
package com.feng.mustwin.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * 自定义的View,绘制几何图形
 *
 * @author yinjie 2015年6月22日 下午6:31:55
 *
 */
@SuppressLint("DrawAllocation")
public class DrawView extends ImageView {
	Paint redPaint = new Paint(); // 红色画笔
	Path path = new Path();
	/**
	 *
	 * @param context
	 * @param attrs
	 */
	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	/**
	 *
	 * @param context
	 */
	public DrawView(Context context) {
		super(context);
		init();
	}
	private void init(){
		redPaint.setAntiAlias(true);// 抗锯齿效果,显得绘图平滑
		redPaint.setDither(true);
		redPaint.setColor(Color.RED); // 设置画笔颜色
		redPaint.setStrokeWidth(15);// 设置笔触宽度
		redPaint.setStyle(Paint.Style.STROKE);// 设置画笔的填充类型(完全填充)
		redPaint.setStrokeJoin(Paint.Join.ROUND);
		redPaint.setStrokeCap(Paint.Cap.ROUND);
		redPaint.setFilterBitmap(true);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawPath(path, redPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_UP){
		}else if(event.getAction() == MotionEvent.ACTION_DOWN){
			mX = event.getX();
			mY = event.getY();
			path.moveTo(event.getX(), event.getY());
		}else if(event.getAction() == MotionEvent.ACTION_MOVE){
//				path.lineTo(event.getX(), event.getY());
			final float x = event.getX();
			final float y = event.getY();

			final float previousX = mX;
			final float previousY = mY;

			final float dx = Math.abs(x - previousX);
			final float dy = Math.abs(y - previousY);

			//两点之间的距离大于等于3时，生成贝塞尔绘制曲线
			if (dx >= 3 || dy >= 3)
			{
				//设置贝塞尔曲线的操作点为起点和终点的一半
				float cX = (x + previousX) / 2;
				float cY = (y + previousY) / 2;

				//二次贝塞尔，实现平滑曲线；previousX, previousY为操作点，cX, cY为终点
				path.quadTo(previousX, previousY, cX, cY);

				//第二次执行时，第一次结束调用的坐标值将作为第二次调用的初始坐标值
				mX = x;
				mY = y;
			}
		}
		invalidate();
		return true;
	}
	private float mX,mY;

	public static Bitmap loadBitmapFromView(View v, boolean isParemt) {
		if (v == null) {
			return null;
		}
		Bitmap screenshot;
		screenshot = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(screenshot);
		v.draw(c);
		return screenshot;
	}
	public String save(){
		Bitmap bmp = loadBitmapFromView(this, false);
		File file = new File("/sdcard/akai/");
		if(!file.exists())
			file.mkdirs();
		try {
			FileOutputStream fos = new FileOutputStream(file.getPath() + "/5.png");
			bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file.getPath() + "/5.png";
	}
}
