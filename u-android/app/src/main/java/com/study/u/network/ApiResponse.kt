package com.study.u.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ApiResponse<T>(
    val code: Int = 0,
    val msg: String,
    val data: T?
)

@JsonClass(generateAdapter = true)
data class ApiError(val code: Int, val msg: String?)

