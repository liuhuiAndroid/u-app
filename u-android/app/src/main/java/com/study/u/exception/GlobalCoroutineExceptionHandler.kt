package com.study.u.exception

import com.study.u.utilities.toast
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

/**
 * Coroutine 异常全局处理类
 */
class GlobalCoroutineExceptionHandler : CoroutineExceptionHandler {

    override val key = CoroutineExceptionHandler

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        Timber.e(exception, "Unhandled Coroutine Exception : ${exception.message}")
        if (exception is APIException) {
            exception.message?.let { toast(it) }
            exception.printStackTrace()
        }
    }

}