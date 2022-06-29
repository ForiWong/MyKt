package com.wlp.mykt.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

/**
 * Description:用于工具类中获取上下文环境ApplicationContext
 */
@SuppressLint("StaticFieldLeak")
object Utils {

    lateinit var mContext: Context

    fun init(context: Context) {
        this.mContext = context.applicationContext
    }

    /**
     * 获取string资源
     */
    fun getStringRes(res: Int): String {
        return mContext.resources.getString(res)
    }

    /**c
     * 获取color资源
     */
    fun getColor(colorResId: Int): Int {
        return ContextCompat.getColor(mContext, colorResId)
    }

    /**c
     * 获取drawable资源
     */
    fun getDrawable(drawableId: Int): Drawable? {
        return ContextCompat.getDrawable(mContext, drawableId)
    }
}