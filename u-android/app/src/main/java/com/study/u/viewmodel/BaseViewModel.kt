package com.study.u.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.study.u.exception.APIException
import com.study.u.utilities.toast
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    fun launch(
        block: suspend CoroutineScope.() -> Unit,
        onError: (error: Throwable) -> Unit = {}
    ) {
        // 统一异常处理
        fun errorHandler(onError: (error: Throwable) -> Unit = {}): CoroutineExceptionHandler {
            return CoroutineExceptionHandler { _, error ->
                onError(error)
                error.printStackTrace()
                if (error is APIException) {
                    error.message?.let { toast(it) }
                }
            }
        }

        viewModelScope.launch(errorHandler(onError)) {
            block.invoke(this)
        }
    }

}