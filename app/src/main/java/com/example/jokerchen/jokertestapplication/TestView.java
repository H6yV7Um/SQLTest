package com.example.jokerchen.jokertestapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Created by jokerchen on 2018/1/18.
 */

public class TestView extends FrameLayout {

    Paint paint = new Paint();
    Paint paint1 = new Paint();

    public long getTotalProgress() {
        return totalProgress;
    }

    public void setTotalProgress(long totalProgress) {
        this.totalProgress = totalProgress;
    }

    public long getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(long currentProgress) {
        this.currentProgress = currentProgress;
    }

    public long getNextTargetProgress() {
        return nextTargetProgress;
    }

    public void setNextTargetProgress(long nextTargetProgress) {
        this.nextTargetProgress = nextTargetProgress;
    }

    private long totalProgress;
    private long currentProgress;
    private long nextTargetProgress;

    private int nextTargetProtgressPosition;

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.test,null,false);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(view,layoutParams);
    }
}
