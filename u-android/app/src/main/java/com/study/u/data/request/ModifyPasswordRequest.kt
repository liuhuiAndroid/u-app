package com.study.u.data.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ModifyPasswordRequest(val oldPassword: String, val newPassword: String)