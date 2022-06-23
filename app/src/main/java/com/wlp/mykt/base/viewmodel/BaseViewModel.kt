package com.wlp.mykt.base.viewmodel

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.*
import com.wlp.mykt.base.event.EventLiveData
import com.wlp.mykt.util.LogUtils
import com.wlp.mykt.util.Utils

/**
 * ViewModel基类继承AndroidViewModel，并且默认是是生命周期观察者DefaultLifecycleObserver
 * 在View中addObserver(it)/removeObserver(it)
 */
open class BaseViewModel : AndroidViewModel, DefaultLifecycleObserver {

    constructor(application: Application) : super(application) {
        LogUtils.d("onCreate():${this.javaClass.simpleName}")
        init()
    }

    /**
     * 做一些初始化操作
     */
    fun init() {

    }

    val uiChangeLiveData: UIChangeLiveData by lazy { UIChangeLiveData() }

    /**
     * 加载提示框
     */
    fun showDialog(resId: Int) {
        uiChangeLiveData.showDialogEvent.postValue(Utils.getStringRes(resId))
    }

    /**
     * 加载提示框
     */
    fun showDialog(title: String? = "请稍后...") {
        uiChangeLiveData.showDialogEvent.postValue(title)
    }

    /**
     * 隐藏加载提示框
     */
    fun dismissDialog() {
        uiChangeLiveData.dismissDialogEvent.call()
    }

    /**
     * 提示确认框
     */
    fun showRemindDialog(remind: String?) {
        uiChangeLiveData.remindDialogEvent.postValue(remind)
    }

    /**
     * 隐藏提示确认框
     */
    fun dismissRemindDialog() {
        uiChangeLiveData.dismissRemindDialogEvent.call()
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    @JvmOverloads
    fun startActivity(clz: Class<*>, bundle: Bundle? = null) {
        val params: MutableMap<String, Any> = HashMap()
        params[ParameterField.CLASS] = clz
        if (bundle != null) {
            params[ParameterField.BUNDLE] = bundle
        }
        uiChangeLiveData.startActivityEvent.postValue(params)
    }

    /**
     * 关闭界面
     */
    fun finish() {
        uiChangeLiveData.finishEvent.call()
    }

    /**
     * 返回上一层
     */
    fun onBackPressed() {
        uiChangeLiveData.onBackPressedEvent.call()
    }

    inner class UIChangeLiveData {
        val showDialogEvent by lazy { EventLiveData<String>() }

        val dismissDialogEvent by lazy { EventLiveData<String>() }

        val remindDialogEvent by lazy { EventLiveData<String>() }

        val dismissRemindDialogEvent by lazy { EventLiveData<Void>() }

        val startActivityEvent by lazy { EventLiveData<Map<String, Any>>() }

        val finishEvent by lazy { EventLiveData<Void>() }

        val onBackPressedEvent by lazy { EventLiveData<Void>() }
    }

    object ParameterField {
        var CLASS = "CLASS"
        var BUNDLE = "BUNDLE"
    }

}