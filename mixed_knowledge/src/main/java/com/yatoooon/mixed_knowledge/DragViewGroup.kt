package com.yatoooon.mixed_knowledge

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.customview.widget.ViewDragHelper

class DragViewGroup(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private val viewDragHelper by lazy {
        ViewDragHelper.create(this, object : ViewDragHelper.Callback() {
            // 决定了是否需要捕获这个 child，只有捕获了才能进行下面的拖拽行为  clampViewPositionHorizontal() 和 clampViewPositionVertical() 中处理 child 拖拽时的位置坐标。
            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                return true
            }

            // 手指释放时的回调
            override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
                super.onViewReleased(releasedChild, xvel, yvel)
            }

            // 修整 child 水平方向上的坐标，left 指 child 要移动到的坐标，dx 相对上次的偏移量
            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                return left
            }

            // 修整 child 垂直方向上的坐标，top 指 child 要移动到的坐标，dy 相对上次的偏移量
            override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                return top
            }

        })
    }
    ///是否应该拦截 children 的触摸事件，
    //只有拦截了 ViewDragHelper 才能进行后续的动作
    override fun onInterceptHoverEvent(event: MotionEvent): Boolean {
        return viewDragHelper.shouldInterceptTouchEvent(event)
    }

    //处理 ViewGroup 中传递过来的触摸事件序列
    //在 ViewGroup 中的 onTouchEvent() 方法中处理
    override fun onTouchEvent(event: MotionEvent): Boolean {
        viewDragHelper.processTouchEvent(event)
        return true
    }
}