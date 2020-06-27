package com.study.u.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    var id: Int,
    /** 期限 单位天. */
    var time: Int,
    /** 期限中文描述.  */
    var time_desc: String,
    /** 收益.  */
    var gain: Int,
    /** 条件.  */
    var gainCondition: Int
)