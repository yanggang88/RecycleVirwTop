package com.xhs.recycleviewtop.util

import android.content.Context

/**
 * 时间：2021-05-11 14:07
 * 作者：yanggang
 * 邮箱：1334045135@qq.com
 */
object DensityUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Float {
        val scale: Float = context.getResources().getDisplayMetrics().density
        return (dpValue * scale + 0.5f)
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Float {
        val scale: Float = context.getResources().getDisplayMetrics().density
        return (pxValue / scale + 0.5f)
    }
}