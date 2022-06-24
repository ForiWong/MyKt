package com.wlp.mykt.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.wlp.mykt.util.LogUtils
import com.wlp.mykt.util.Utils

/**
 * TODO 可以写一个全局的ViewModel
 * 单例、静态的属性
 */
open class BaseApplication : Application() {

    companion object {
        @JvmStatic
        lateinit var instance: BaseApplication
            private set

        private var mCount = 0

        @JvmStatic
        fun isBackStage(): Boolean {
            return mCount == 0
        }
    }

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        registerCallBack()
    }

    private fun registerCallBack() {
        registerActivityLifecycleCallbacks(callback)
    }

    private val callback = object : ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            LogUtils.d("onActivityCreated ${activity.localClassName}")
            KtAppManager.appManager?.addActivity(activity)
        }

        override fun onActivityStarted(activity: Activity) {
            mCount++
        }

        override fun onActivityResumed(activity: Activity) = Unit
        override fun onActivityPaused(activity: Activity) = Unit
        override fun onActivityStopped(activity: Activity) {
            mCount--
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit
        override fun onActivityDestroyed(activity: Activity) {
            LogUtils.d("onActivityCreated ${activity.localClassName}")
            KtAppManager.appManager?.removeActivity(activity)
        }
    }

}