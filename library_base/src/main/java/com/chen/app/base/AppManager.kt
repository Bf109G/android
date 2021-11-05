package com.chen.app.base

import android.app.Activity
import androidx.fragment.app.Fragment
import java.util.*

/**
 * Author by chennan
 * Date on 2021/11/2
 * Description
 */
class AppManager() {
    companion object {
        var activityStack: Stack<Activity> = Stack()
        var fragmentStack: Stack<Fragment> = Stack()

        private var instance: AppManager? = null
            get() {
                if (field == null) {
                    field = AppManager()
                }
                return field
            }

        fun get(): AppManager {
            return instance!!
        }
    }

    val isActivity: Boolean
        get() = activityStack.isEmpty()


    fun addActivity(activity: Activity?) {
        activityStack.add(activity)
    }

    fun removeActivity(activity: Activity?) {
        if (activity != null) {
            activityStack.remove(activity)
        }
    }

    fun finishActivity() {
        finishActivity(activityStack.lastElement())
    }

    fun finishActivity(cls: Class<*>) {
        for (activity in activityStack) {
            if (activity.javaClass == cls) {
                finishActivity(activity)
                break
            }
        }
    }

    private fun finishActivity(activity: Activity) {
        if (activity.isFinishing) {
            activity.finish()
        }
    }

    fun finishAllActivity() {
        for (activity in activityStack) {
            finishActivity(activity)
        }
        activityStack.clear()
    }

    fun currentActivity(): Activity {
        return activityStack.lastElement()
    }

    fun getActivity(cls: Class<*>): Activity? {
        for (activity in activityStack) {
            if (activity.javaClass == cls) {
                return activity
            }
        }
        return null
    }

    val isFragment: Boolean
        get() = fragmentStack.isEmpty()

    fun addFragment(fragment: Fragment){
        fragmentStack.add(fragment)
    }

    fun removeFragment(fragment: Fragment?){
        if (fragment != null) {
            fragmentStack.remove(fragment)
        }
    }

    fun currentFragment(): Fragment {
        return fragmentStack.lastElement()
    }

    fun appExit(){
        try {
            finishAllActivity()
        }catch (e: Exception){
            activityStack.clear()
            e.printStackTrace()
        }
    }
}