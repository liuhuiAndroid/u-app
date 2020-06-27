package com.study.u.data.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WithdrawRequest(val address: String, val number: Int)