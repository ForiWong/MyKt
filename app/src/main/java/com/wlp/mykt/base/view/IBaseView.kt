package com.wlp.mykt.base.view

import android.os.Bundle

/**
 * Description:View接口
 */
interface IBaseView {
    /**
     * 初始化数据
     */
    fun initData(savedInstanceState: Bundle?)

    /**
     * 初始化界面观察者的监听
     */
    fun initViewObservable()
}