package com.loonggg.snaphelperdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by kongnan on 17/2/22.
 */
public class RecyclerGallery extends RecyclerView {

    private static final String TAG = RecyclerGallery.class.getSimpleName();

    public static final int SCALE_PIVOT_CENTER = 0;
    public static final int SCALE_PIVOT_TOP = 1;
    public static final int SCALE_PIVOT_BOTTOM = 2;

    private int mScalePivot = SCALE_PIVOT_BOTTOM;
    private float mSelectedScale = 0.9f;

    private int[] viewLocation = new int[2];

    public RecyclerGallery(Context context) {
        super(context);
    }

    public RecyclerGallery(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerGallery(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean drawChild(Canvas canvas, View child, long drawingTime) {

        int x = child.getLeft();
        Log.i("scale","x: "+x);
        int vWidth = child.getWidth() - child.getPaddingLeft() - child.getPaddingRight();
        int vHeight = child.getHeight() - child.getPaddingTop() - child.getPaddingBottom();
        int dWidth = getResources().getDisplayMetrics().widthPixels;
        if (vWidth >= dWidth) {
            return super.drawChild(canvas, child, drawingTime);
        }
        float scale;
        int pivot = (dWidth - vWidth) / 2;

        Log.i("scale","pivot: "+x);

        if (x <= pivot) {
            scale = 2 * (1 - mSelectedScale) * (x + vWidth) / (dWidth + vWidth) + mSelectedScale;
        } else {
            scale = 2 * (1 - mSelectedScale) * (dWidth - x) / (dWidth + vWidth) + mSelectedScale;
        }
        child.setPivotX(vWidth / 2);
        child.setPivotY(vHeight / 2);
        Log.i("scale",scale+"");
//        if(scale>0.99){
//            child.setAlpha(1);
//        }else{
//            child.setAlpha(0.5f);
//        }

        child.setScaleX(scale);
        child.setScaleY(scale);
        return super.drawChild(canvas, child, drawingTime);
    }

}
