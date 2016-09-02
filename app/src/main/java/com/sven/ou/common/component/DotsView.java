package com.sven.ou.common.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.sven.ou.R;

/**
 * 小圆点
 */
public class DotsView extends View {

	private int totalCount = 5;
	private int currentPage = 0;
	private int thisWidth = 0;
	private int thisHeight = 0;
	private double blankWidth = 15;
	private int pointHeight = 30;
	private int pointWidth = 30;

	private boolean isDynamic = false;// 是否根据宽度动态计算每个点的大小,注意。PagingView设宽度

	private final int dotColor = R.color.icons;
	private final int dotHighlightColor = R.color.divider;

	public DotsView(Context context) {
		super(context);
	}

	public DotsView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DotsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		thisHeight = getHeight();
		thisWidth = getWidth();

		if (isDynamic) {
			pointWidth = thisWidth / (totalCount + totalCount / 3);
			pointHeight = pointWidth;
			blankWidth = (thisWidth - totalCount * pointWidth)
					/ (totalCount - 1);
		}
		super.onDraw(canvas);
		canvas.drawColor(Color.TRANSPARENT);
		Paint paint = new Paint();
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(getCompatColor(dotColor));
		paint.setStyle(Style.FILL);

		if (isDynamic) {
			for (int i = 0; i < totalCount; i++) {
				if (i == currentPage) {
					paint.setColor(getCompatColor(dotHighlightColor));
					canvas.drawCircle((float) (i * (blankWidth + pointWidth)
							* 1.0 + pointWidth / 2.0),
							(float) (thisHeight / 2.0),
							(float) (pointWidth / 2.0), paint);
					paint.setColor(getCompatColor(dotColor));
				} else {
					canvas.drawCircle((float) (i * (blankWidth + pointWidth)
							* 1.0 + pointWidth / 2.0),
							(float) (thisHeight / 2.0),
							(float) (pointWidth / 2.0), paint);
				}
			}
		}else{
			for (int i = 0; i < totalCount; i++) {
				float cx = (float) ((float) (i * (blankWidth + pointWidth)* 1.0 + pointWidth / 2.0)+
						(thisWidth - totalCount * (pointWidth+blankWidth))/ 2.0);
				if (i == currentPage) {
					paint.setColor(getCompatColor(dotHighlightColor));
					canvas.drawCircle(cx,
							(float) (thisHeight / 2.0),
							(float) (pointWidth / 2.0), paint);
					paint.setColor(getCompatColor(dotColor));
				} else {
					canvas.drawCircle(cx,
							(float) (thisHeight / 2.0),
							(float) (pointWidth / 2.0), paint);
				}
			}
		}
	}

	public boolean updaetpage(int totalCount, int currentPage) {
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.invalidate();
		return false;
	}

	private int getCompatColor(int resId){
		return ContextCompat.getColor(getContext(), resId);
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getThisWidth() {
		return thisWidth;
	}

	public void setThisWidth(int thisWidth) {
		this.thisWidth = thisWidth;
	}

	public int getThisHeight() {
		return thisHeight;
	}

	public void setThisHeight(int thisHeight) {
		this.thisHeight = thisHeight;
	}

	public boolean isDynamic() {
		return isDynamic;
	}

	public void setDynamic(boolean isDynamic) {
		this.isDynamic = isDynamic;
	}

	public void setPointHeight(int pointHeight) {
		this.pointHeight = pointHeight;
	}

	public void setPointWidth(int pointWidth) {
		this.pointWidth = pointWidth;
	}

}
