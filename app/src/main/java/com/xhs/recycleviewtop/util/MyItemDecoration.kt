package com.xhs.recycleviewtop.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xhs.recycleviewtop.R
import com.xhs.recycleviewtop.adapter.MyAdapter
import com.xhs.recycleviewtop.bean.UserInfoBean

/**
 * 时间：2021-05-11 09:26
 * 作者：yanggang
 * 邮箱：1334045135@qq.com
 */
class MyItemDecoration(var context: Context) :
    RecyclerView.ItemDecoration() {

    // 这里的绘制顺序是 getItemOffsets  onDraw  onDrawOver
    // getItemOffsets 中获取不到view的高毒，次数item还没有绘制，在ondraw中可以获取高度，所以次数item已绘制

    // 线条画笔
    var paint: Paint? = null;

    // 文字画笔
    var txtPaint: Paint? = null

    // 获取文字宽高
    var rectTxt: Rect? = null

    init {
        paint = Paint()
        paint?.color = ContextCompat.getColor(context, R.color.colorRend)
        paint?.style = Paint.Style.FILL

        txtPaint = Paint()
        txtPaint?.color = ContextCompat.getColor(context, R.color.colorWhite)
        txtPaint?.style = Paint.Style.FILL
        txtPaint?.textSize = 28f

        rectTxt = Rect()
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        // 获取屏幕中 view 的总数
        var count = parent.childCount;
        // item距离左边的距离
        var left = parent.paddingLeft;
        // item最右边的内容距离左边的距离
        var right = parent.width - parent.paddingRight
        // 获取适配器
        val adapter = parent.adapter as MyAdapter
        // 获取数据
        val dataList = adapter.dataList
        // 获取顶部显示view的position
        var linearLayoutManager: LinearLayoutManager = parent.layoutManager as LinearLayoutManager
        val findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()

        // 判断下一个是否为头
        var head = isHead(dataList, findFirstVisibleItemPosition + 1)

        // 获取 第一个可见中第二个视图
        var newxView = parent.getChildAt(1)
        var bgBottom = DensityUtil.dip2px(context, 50f);
        var txtBottom = (DensityUtil.dip2px(context, 50f)) / 2 + (rectTxt?.height()
            ?: 0) / 2 - (txtPaint?.getFontMetrics()?.descent ?: 0f) / 2

        if (head && newxView != null) {
            bgBottom = Math.min(
                DensityUtil.dip2px(context, 50f),
                newxView.top.toFloat() - DensityUtil.dip2px(context, 50f)
            )
            txtBottom = Math.min(
                (DensityUtil.dip2px(context, 50f)) / 2 + (rectTxt?.height()
                    ?: 0) / 2 - (txtPaint?.getFontMetrics()?.descent ?: 0f) / 2,
                (newxView.top.toFloat() - DensityUtil.dip2px(context, 50f)) / 2 + (rectTxt?.height()
                    ?: 0) / 2 - (txtPaint?.getFontMetrics()?.descent ?: 0f) / 2
            )
        }
        // 背景
        paint?.let {
            c?.drawRect(
                left.toFloat(),
                0f,
                right.toFloat(),
                bgBottom,
                it
            )
        }

        // 绘制文字
        txtPaint?.let {
            var groupName =
                dataList?.get(findFirstVisibleItemPosition)?.className
                    ?: ""
            it.getTextBounds(groupName, 0, groupName.length, rectTxt)
            c?.drawText(
                groupName,
                DensityUtil.dip2px(context, 15f),
                txtBottom,
                it
            )
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        // 获取屏幕中 view 的总数
        var count = parent.childCount;
        // item距离左边的距离
        var left = parent.paddingLeft;
        // item最右边的内容距离左边的距离
        var right = parent.width - parent.paddingRight
        // 获取适配器
        val adapter = parent.adapter as MyAdapter
        // 获取数据
        val dataList = adapter.dataList
        // 获取顶部显示view的position
        var linearLayoutManager: LinearLayoutManager = parent.layoutManager as LinearLayoutManager
        val findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
        for (i in 0 until count) {
            var mview = parent.getChildAt(i)
            mview ?: break
            // 绘制矩形 距离顶部的距离
            var top = mview.top - 10
            // 绘制矩形距离顶部的距离·
            var bottom = mview.top
            paint?.let {
                val head = isHead(dataList, i + findFirstVisibleItemPosition)
                if (head) {
                    // 上下左右实际上就是左上角和右下角的坐标点    类似于 自定义view中的   getTop  getLeft  getRight  getBottom 方法获取到的值
                    c?.drawRect(
                        left.toFloat(),
                        mview.top - DensityUtil.dip2px(context, 50f),
                        right.toFloat(),
                        mview.top.toFloat(),
                        it
                    )
                    // 绘制文字
                    txtPaint?.let {
                        var groupName =
                            dataList?.get(i + findFirstVisibleItemPosition)?.className ?: ""
                        it.getTextBounds(groupName, 0, groupName.length, rectTxt)
                        c?.drawText(
                            groupName,
                            DensityUtil.dip2px(context, 15f),
                            mview.top - DensityUtil.dip2px(context, 50f) / 2 + (rectTxt?.height()
                                ?: 0) / 2 - (txtPaint?.getFontMetrics()?.descent ?: 0f) / 2,
                            it
                        )
                    }
                } else {
                    // 上下左右实际上就是左上角和右下角的坐标点    类似于 自定义view中的   getTop  getLeft  getRight  getBottom 方法获取到的值
                    c?.drawRect(
                        left.toFloat(),
                        top.toFloat(),
                        right.toFloat(),
                        bottom.toFloat(),
                        it
                    )
                }
            }
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        // 获取适配器
        val adapter = parent.adapter as MyAdapter
        // 获取数据
        val dataList = adapter.dataList
        // 当前view所对应的position
        var currPosition = parent.getChildAdapterPosition(view)
        // 判断是否是头部， 类似 ExpandListview中的group
        val head = isHead(dataList, currPosition)
        if (head) {
            // 在itemview 中的上下左右  绘制指定像素大小的背景色区域
            outRect.set(0, DensityUtil.dip2px(context, 50f).toInt(), 0, 0)
        } else {
            // 在itemview 中的上下左右  绘制指定像素大小的背景色区域
            outRect.set(0, 10, 0, 0)
        }
    }


    fun isHead(dataList: ArrayList<UserInfoBean>?, currPosition: Int): Boolean {
        if (currPosition == 0) return true
        return dataList?.get(currPosition - 1)?.className != dataList?.get(currPosition)?.className
    }
}