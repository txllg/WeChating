package com.example.wechating.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

import com.example.wechating.R;

public class MessageSlideLayout extends FrameLayout {

    private View contentView;
    private View menuView;

    private int viewHeight; //高是相同的
    private int contentWidth;
    private int menuWidth;
    private int catlogWidth;

    //滑动器
    private Scroller scroller;

    public MessageSlideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
    }

    /**
     * 布局文件加载完成时被调用
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = findViewById(R.id.content);
        menuView = findViewById(R.id.message_menu);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        viewHeight = getMeasuredHeight();

        contentWidth = contentView.getMeasuredWidth();
        menuWidth = menuView.getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        menuView.layout(contentWidth, 0, contentWidth+menuWidth, viewHeight);
    }


    private float startX;
    private float startY;

    private float downX;
    private float downY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                downX = startX = event.getX();
                downY = startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = event.getX();
                float endY = event.getY();

                //计算偏移量
                float distanceX = endX - startX;

                int toScrollX = (int) (getScrollX()-distanceX);
                //屏蔽非法值
                if (toScrollX < 0 )
                {
                    toScrollX = 0;
                }
                if (toScrollX > menuWidth)
                {
                    toScrollX = menuWidth;
                }
                System.out.println("toScroll-->"+toScrollX+"-->"+getScrollX());
                scrollTo(toScrollX,getScrollY());

                startX = event.getX();

                float dx = Math.abs(event.getX()-downX);
                float dy = Math.abs(event.getY()-downY);
                if (dx > dy && dx > 6)
                {
                    //事件反拦截，使父ListView的事件传递到自身SlideLayout
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                break;
            case MotionEvent.ACTION_UP:

                if (getScrollX() > menuWidth/2)
                {
                    //打开menu
                    openMenu();
                }else {
                    closeMenu();
                }

                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                downX = startX = event.getX();
                downY = startY = event.getY();
                if (onStateChangeListener != null)
                {
                    onStateChangeListener.onMove(this);
                }
                break;
            case MotionEvent.ACTION_MOVE:

                float dx = Math.abs(event.getX()-downX);
                float dy = Math.abs(event.getY()-downY);
                if (dx > dy && dx > 6)
                {
                    //拦截事件
                    return true;
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(event);
    }

    /**
     * 打开menu菜单
     */
    public void openMenu() {
        int dx = menuWidth-getScrollX();
        scroller.startScroll(getScrollX(), getScrollY(),dx, getScrollY());
        invalidate();
        if (onStateChangeListener != null)
        {
            onStateChangeListener.onOpen(this);
        }
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        //0表示menu移动到的目标距离
        int dx = 0-getScrollX();
        scroller.startScroll(getScrollX(), getScrollY(),dx, getScrollY());
        invalidate();
        if (onStateChangeListener != null)
        {
            onStateChangeListener.onClose(this);
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset())
        {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }

    public interface OnStateChangeListener
    {
        void onOpen(MessageSlideLayout slideLayout);
        void onMove(MessageSlideLayout slideLayout);
        void onClose(MessageSlideLayout slideLayout);
    }

    public OnStateChangeListener onStateChangeListener;

    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.onStateChangeListener = onStateChangeListener;
    }
}

