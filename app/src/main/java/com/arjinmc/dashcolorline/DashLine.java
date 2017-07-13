package com.arjinmc.dashcolorline;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by arjinmc on 13/7/17.
 * e-mail:arjinmc@hotmail.com
 */

public class DashLine extends View {

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private int mDashWidth = 30;
    private int mDashGap = 10;
    private int mThickness = 10;
    private int[] mColors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
    private int mOrientation;

    private int mLineCount;
    private int mColorCount;
    private Paint mPaint;

    public DashLine(Context context) {
        super(context);
        init(null);
    }

    public DashLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DashLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DashLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.dashLine);
            mDashWidth = (int) typedArray.getDimension(R.styleable.dashLine_dashWidth, 30f);
            mDashGap = (int) typedArray.getDimension(R.styleable.dashLine_dashGap, 10f);
            mThickness = (int) typedArray.getDimension(R.styleable.dashLine_thickness, 10f);
            mOrientation = typedArray.getInt(R.styleable.dashLine_orientation, HORIZONTAL);
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mThickness);
    }

    public void setColors(@ColorInt int... colors) {
        mColors = new int[colors.length];
        mColors = colors;
        mColorCount = colors.length;
        postInvalidate();
    }

    public void setDashWidth(int dashWidth) {
        mDashWidth = dashWidth;
        postInvalidate();
    }

    public void setDashGap(int dashGap) {
        mDashGap = dashGap;
        postInvalidate();
    }

    public void setThickness(int thickness) {
        mThickness = thickness;
        postInvalidate();
    }

    public void setOrientation(int orientation) {
        mOrientation = orientation;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        PathEffect effects = new DashPathEffect(new float[]{0, 0, mDashWidth, mThickness}, mDashGap);
        mPaint.setPathEffect(effects);

        if (mLineCount == 0) {
            calCount();
        }

        if (mOrientation == VERTICAL) {
            int yWidth = mDashWidth + mDashGap;
            int x = mThickness / 2;
            int myWidth = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

            for (int i = 0; i < mLineCount; i++) {
                Path path = new Path();
                path.moveTo(x, i * yWidth);
                if (i == mLineCount - 1 && (mLineCount * yWidth > myWidth)) {
                    path.lineTo(x, myWidth);
                } else
                    path.lineTo(x, i * yWidth + mDashWidth);
                mPaint.setColor(mColors[i % mColorCount]);
                canvas.drawPath(path, mPaint);
            }
        } else {

            int xWidth = mDashWidth + mDashGap;
            int y = mThickness / 2;
            int myWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
            for (int i = 0; i < mLineCount; i++) {
                Path path = new Path();
                path.moveTo(i * xWidth, y);
                if (i == mLineCount - 1 && (mLineCount * xWidth > myWidth)) {
                    path.lineTo(myWidth, y);
                } else
                    path.lineTo(i * xWidth + mDashWidth, y);
                mPaint.setColor(mColors[i % mColorCount]);
                canvas.drawPath(path, mPaint);
            }
        }
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mOrientation == VERTICAL) {
            setMeasuredDimension(mThickness, heightMeasureSpec);
        } else {
            setMeasuredDimension(widthMeasureSpec, mThickness);
        }

    }

    private void calCount() {
        if (mOrientation == VERTICAL) {
            int myHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
            mLineCount = myHeight / (mDashWidth + mDashGap);
            if (mLineCount * (mDashWidth + mDashGap) < myHeight) {
                ++mLineCount;
            }
        } else {
            int myWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
            mLineCount = myWidth / (mDashWidth + mDashGap);
            if (mLineCount * (mDashWidth + mDashGap) < myWidth) {
                ++mLineCount;
            }
        }
        mColorCount = mColors.length;
    }

}
