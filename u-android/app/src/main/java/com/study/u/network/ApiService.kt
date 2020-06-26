package com.study.u.network

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

}