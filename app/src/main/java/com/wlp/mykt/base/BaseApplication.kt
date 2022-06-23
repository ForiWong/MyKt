package com.wlp.mykt.base

import android.app.Application
import com.wlp.mykt.util.Utils

/**
 * TODO 可以写一个全局的ViewModel
 */
open class BaseApplication : Application(){

    companion object{
        //获取单例
        @JvmStatic lateinit var instance: BaseApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }

}