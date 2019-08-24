package com.example.example3nd;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    Handler handler;
    int mScrollY;
    int mNewState = 0, updatedY = 0;
    SearchView searchView;
    NestedScrollView nestedScrollView;
    int scrollHeight = 260;
    LinearLayoutManager mLinearLayoutManager;
    RecyclerView.SmoothScroller smoothScroller;
    boolean isUp = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"WrongConstant", "ClickableViewAccessibility"})
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(new RecyclerViewAdapter(getApplicationContext()));
        handler = new Handler();
        //nestedScrollView = findViewById(R.id.nestedScrollView);

        smoothScroller = new LinearSmoothScroller(getApplication()) {
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("newState", "" + newState + ", scrollY: " + mScrollY);
                mNewState = newState;
                if (newState == 0)
                    RecyclerScrollControl();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull final RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                mScrollY = recyclerView.computeVerticalScrollOffset();

                Log.d("Case", "scrollY: " + recyclerView.computeVerticalScrollOffset() + " # mNewState:" + mNewState);


                /*handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updatedScrollY = recyclerView.computeVerticalScrollOffset();

                    }
                }, 50);*/
            }
        });


        recyclerView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                MainActivity.super.onTouchEvent(event);

                switch (event.getAction()) {

                    case MotionEvent.ACTION_UP:
                        isUp = true;
                        if (mScrollY == updatedY) {
                            //  NestedScrollControl();
                            isUp = false;
                        }
                        break;
                }
                return false;
            }
        });

/*
        nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, final int scrollX, final int scrollY, int oldScrollX, final int oldScrollY) {

                mScrollY = scrollY;

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updatedY = scrollY;

                        if (mScrollY == updatedY && isUp) {
                           // NestedScrollControl();
                            Log.d("Case", "scrollY: " + scrollY + " updatedY: " + updatedY);
                            handler.removeCallbacks(this);
                            isUp = false;

                        }
                    }
                }, 80);

            }
        });
*/

    }

    public void NestedScrollControl() {


        if (mScrollY < (scrollHeight / 2)) { //Kullanıcı, parmağını searchView boyutunun üst kısmının yarısından daha yukarda bıraktıysa.

            smoothScroller.setTargetPosition(0);
            mLinearLayoutManager.startSmoothScroll(smoothScroller);
            Log.d("TEST-smoothNested", "1- mNewState=" + mNewState + ", scrollHeight=" + scrollHeight + ", mScrollY: " + mScrollY);

        } else if (mScrollY > (scrollHeight / 2) && mScrollY < scrollHeight) { //Kullanıcı, parmağını searchView yarı boyutu ile tam searchView boyutu arasında bıraktıysa

            //recyclerView.scrollToPosition(1);
            smoothScroller.setTargetPosition(1);
            mLinearLayoutManager.startSmoothScroll(smoothScroller);

            //mLinearLayoutManager.scrollToPositionWithOffset(1, 0);
            // mLinearLayoutManager.scrollToPosition(1);
            //recyclerView.smoothScrollToPosition(10);


            Log.d("TEST-smoothNested", "2- mNewState=" + mNewState + ", scrollHeight=" + scrollHeight + ", mScrollY: " + mScrollY);
        }

    }

    public void RecyclerScrollControl() {

        View v = LayoutInflater.from(this).inflate(R.layout.header, null);
        searchView = v.findViewById(R.id.searchView);
        int deger = searchView.getMinimumHeight();
        //Log.d("TEST", "Test # searchView.getHeight:"+searchView.getHeight()+" gitminheight:"+searchView.getMinimumHeight());


        if (mScrollY < (scrollHeight / 2)) { //Kullanıcı, parmağını searchView boyutunun üst kısmının yarısından daha yukarda bıraktıysa.

            //nestedScrollView.smoothScrollTo(0, 1);

            smoothScroller.setTargetPosition(0);
            mLinearLayoutManager.startSmoothScroll(smoothScroller);

            //recyclerView.smoothScrollToPosition(0);
            //mLinearLayoutManager.scrollToPositionWithOffset(0, 0);

            Log.d("TEST-smooth", "1- mNewState=" + mNewState + ", scrollHeight=" + scrollHeight + ", mScrollY: " + mScrollY);

        } else if (mScrollY > (scrollHeight / 2) && mScrollY < scrollHeight) { //Kullanıcı, parmağını searchView yarı boyutu ile tam searchView boyutu arasında bıraktıysa
            //recyclerView.scrollToPosition(1);
            //nestedScrollView.smoothScrollTo(0, scrollHeight);


            smoothScroller.setTargetPosition(1);
            mLinearLayoutManager.startSmoothScroll(smoothScroller);

            //mLinearLayoutManager.scrollToPositionWithOffset(1, 0);
            // mLinearLayoutManager.scrollToPosition(1);
            //recyclerView.smoothScrollToPosition(10);


            Log.d("TEST-smooth", "2- mNewState=" + mNewState + ", scrollHeight=" + scrollHeight + ", mScrollY: " + mScrollY);
        }

    }

    /*
    public void ScrollTo() {

        if (scrollY == updatedScrollY && isUp) {
            ScrollControl();
            isUp = false;
        }
    }



    private void ScrollTo() {

        View v = LayoutInflater.from(this).inflate(R.layout.header, null);
        searchView = v.findViewById(R.id.searchView);
        int deger = searchView.getMinimumHeight();
        scrollHeight = 270;
        Log.d("TEST", "#ScrollTo - mScrollY:"+mScrollY+" scrollHeight:"+scrollHeight);

        if (mScrollY <= (scrollHeight / 2)) {
            //Kullanıcı, parmağını searchView boyutunun üst kısmının yarısından daha yukarda bıraktıysa.
            nestedScrollView.smoothScrollTo(0, 0);
            Log.d("TEST", "1- mScrollY=" + mScrollY + ", updateY=" + updatedY + "searchView" + scrollHeight);
        }

        if (mScrollY > (scrollHeight / 2) && mScrollY < scrollHeight) {
            //Kullanıcı, parmağını searchView yarı boyutu ile tam searchView boyutu arasında bıraktıysa
            nestedScrollView.smoothScrollTo(0, scrollHeight);
            Log.d("TEST", "2- mScrollY=" + mScrollY + ", updateY=" + updatedY + "searchView" + scrollHeight);
        }

    }
*/
}
