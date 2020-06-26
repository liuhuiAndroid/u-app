package com.study.u.exception

open class APIException(val code: Int, override val message: String?) : Exception()