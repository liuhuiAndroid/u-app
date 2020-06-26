package com.study.u

import android.app.Application
import android.content.ComponentCallbacks2
import android.content.ContextWrapper
import com.bumptech.glide.Glide
import com.study.u.utilities.timber.LocalTree
import com.tencent.mmkv.MMKV
import timber.log.Timber

class UApplication : Application() {

    companion object {
        lateinit var INSTANCE: Application
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        if (BuildConfig.DEBUG) {
            Timber.plant(LocalTree())
        }

        MMKV.initialize(this)
    }

    /**
     * 指导应用程序在不同的情况下进行自身的内存释放，以避免被系统直接杀掉，提高应用程序的用户体验
     */
    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        // 表示应用程序的所有UI界面被隐藏了
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory()
        }
        Glide.get(this).onTrimMemory(level)
    }

    /**
     * OnLowMemory大概和 OnTrimMemory 中的 TRIM_MEMORY_COMPLETE 级别相同
     */
    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).onLowMemory()
    }

}

/**
 * 获取全局Context，在代码的任意位置都可以调用，随时都能获取到全局Context对象。
 *
 * @return 全局Context对象。
 */
object AppContext : ContextWrapper(UApplication.INSTANCE)