package com.study.u.network

import com.study.u.data.Product
import com.study.u.data.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    /**
     * 登录
     */
    @POST("user/login")
    suspend fun userLogin(
        @Body loginRequest: LoginRequest
    ): ApiResponse<String>

    /**
     * 注册
     */
    @POST("user/register")
    suspend fun userRegister(
        @Body loginRequest: LoginRequest
    ): ApiResponse<String>

    /**
     * 商品列表
     */
    @POST("product/list")
    suspend fun productList(): ApiResponse<List<Product>?>

}