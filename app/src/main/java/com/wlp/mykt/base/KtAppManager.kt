package com.wlp.mykt.base

import android.app.Activity
import androidx.fragment.app.Fragment
import java.lang.Exception
import java.util.*

/**
 * Description:activity、fragment堆栈式管理
 */
class KtAppManager private constructor() {

    companion object {
        private var activityStack: Stack<Activity?>? = null
            private set

        private var fragmentStack: Stack<Fragment>? = null
            private set

        private var instance: KtAppManager? = null

        /**
         * 单例模式
         *
         * @return AppManager
         */
        val appManager: KtAppManager?
            get() {
                if (instance == null) {
                    synchronized(KtAppManager::class.java) {
                        if (instance == null) {
                            instance = KtAppManager()
                        }
                    }
                }
                return instance
            }
    }

    var splashCls //启动页
            : Class<*>? = null

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity?) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    /**
     * 移除指定的Activity
     */
    fun removeActivity(activity: Activity?) {
        if (activity != null) {
            activityStack!!.remove(activity)
        }
    }

    /**
     * 是否有activity
     */
    val isActivity: Boolean
        get() = if (activityStack != null) {
            !activityStack!!.isEmpty()
        } else false

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity? {
        return activityStack!!.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity = activityStack!!.lastElement()
        finishActivity(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        if (activity != null) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        for (activity in activityStack!!) {
            if (activity!!.javaClass == cls) {
                finishActivity(activity)
                break
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        var i = 0
        val size = activityStack!!.size
        while (i < size) {
            if (null != activityStack!![i]) {
                finishActivity(activityStack!![i])
            }
            i++
        }
        activityStack!!.clear()
    }

    /**
     * 除了cls，其他都关闭；
     */
    fun finishAllExceptActivity(cls: Class<*>) {
        var i = 0
        val size = activityStack!!.size
        while (i < size) {
            if (null != activityStack!![i] && activityStack!![i]!!.javaClass != cls) {
                finishActivity(activityStack!![i])
            }
            i++
        }
    }

    /**
     * 栈顶是否是指定的Activity
     */
    fun isCurrentActivity(cls: Class<*>?): Boolean {
        return activityStack!!.lastElement()!!.javaClass == cls
    }

    /**
     * 栈顶是否是指定的Activity
     */
    val isSplashActivity: Boolean
        get() = isCurrentActivity(splashCls)

    /**
     * 获取指定的Activity
     */
    fun getActivity(cls: Class<*>): Activity? {
        if (activityStack != null) for (activity in activityStack!!) {
            if (activity!!.javaClass == cls) {
                return activity
            }
        }
        return null
    }

    /**
     * 添加Fragment到堆栈
     */
    fun addFragment(fragment: Fragment) {
        if (fragmentStack == null) {
            fragmentStack = Stack()
        }
        fragmentStack!!.add(fragment)
    }

    /**
     * 移除指定的Fragment
     */
    fun removeFragment(fragment: Fragment?) {
        if (fragment != null) {
            fragmentStack!!.remove(fragment)
        }
    }

    /**
     * 是否有Fragment
     */
    val isFragment: Boolean
        get() = if (fragmentStack != null) {
            !fragmentStack!!.isEmpty()
        } else false

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentFragment(): Fragment? {
        return if (fragmentStack != null) {
            fragmentStack!!.lastElement()
        } else null
    }

    /**
     * 退出应用程序
     */
    fun AppExit() {
        try {
            finishAllActivity()
        } catch (e: Exception) {
            activityStack!!.clear()
            e.printStackTrace()
        }
    }
}