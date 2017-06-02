package com.loonggg.snaphelperdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private List<Integer> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
        mDataList = new ArrayList<>();
        mDataList.add(R.mipmap.pic_1);
        mDataList.add(R.mipmap.pic_2);
        mDataList.add(R.mipmap.pic_3);
        mDataList.add(R.mipmap.pic_4);
        mDataList.add(R.mipmap.pic_5);
        mDataList.add(R.mipmap.pic_6);
        rv.setAdapter(new CardRvAdapter(this, mDataList));
        PagerSnapHelper mMySnapHelper = new PagerSnapHelper();
        mMySnapHelper.attachToRecyclerView(rv);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager.scrollToPositionWithOffset((mDataList.size()-2) *100,(getScreenWidth(MainActivity.this)-PixelUtil.dp2px(240,this))/2);
        rv.setLayoutManager(linearLayoutManager);
        rv.addItemDecoration(new SpacesItemDecoration(5));
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    int position = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    Log.i("onScrollStateChanged",""+position);
                    if(position>0){
                        linearLayoutManager.findViewByPosition(position-1).setAlpha(0.5f);
                        linearLayoutManager.findViewByPosition(position+1).setAlpha(0.5f);
                        linearLayoutManager.findViewByPosition(position).setAlpha(1.0f);
                    }
                }
            }
        });
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }




}
