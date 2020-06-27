package com.study.u.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.study.u.R
import com.study.u.widget.LoadingDialog

open class BaseActivity : AppCompatActivity() {

    private val loadingDialog by lazy {
        LoadingDialog()
    }

    protected fun cancelLoadingDialog() {
        loadingDialog.dismiss()
    }

    fun fullscreen() {
        // 使用全屏沉浸模式
        window.decorView.systemUiVisibility =
                // 避免某些用户交互造成系统自动清除全屏状态
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    // 隐藏系统NavigationBar
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    // 全屏状态
                    View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    protected fun initToolbar(@StringRes resId: Int = 0, showHomeAsUp: Boolean = true) {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar?.let {
            setSupportActionBar(it)
        }
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(showHomeAsUp)
            it.setHomeButtonEnabled(true)
            it.title = if (resId == 0) null else getString(resId)
        }
    }

    protected fun showLoadingDialog(cancelable: Boolean = true, message: Int = R.string.dialog_loading) {
        val bundle = Bundle().apply {
            putInt(LoadingDialog.MESSAGE, message)
        }
        loadingDialog.apply {
            arguments = bundle
            isCancelable = cancelable
            show(supportFragmentManager, LoadingDialog.TAG)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> {
            onBackPressedDispatcher.onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}