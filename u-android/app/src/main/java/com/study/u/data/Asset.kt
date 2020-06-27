package com.study.u.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Asset(

    var id: String,

    var username: String,

    /** 投资资产. */
    var invest: Int,

    /** 推广资产.  */
    var extend: Int,

    /** 总资产.  */
    var allUsdt: Int

)