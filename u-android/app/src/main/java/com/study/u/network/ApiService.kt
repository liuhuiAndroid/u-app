package com.study.u.network

import com.study.u.data.Asset
import com.study.u.data.Product
import com.study.u.data.request.LoginRequest
import com.study.u.data.request.ModifyPasswordRequest
import com.study.u.data.request.WithdrawRequest
import retrofit2.http.*

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
     * 修改密码
     */
    @POST("user/modify/password")
    suspend fun userModifyPassword(
        @Body modifyPasswordRequest: ModifyPasswordRequest
    ): ApiResponse<String>

    /**
     * 商品列表
     */
    @POST("product/list")
    suspend fun productList(): ApiResponse<List<Product>?>

    /**
     * 下单
     */
    @FormUrlEncoded
    @POST("order/add")
    suspend fun orderAdd(
        @Field("productId") productId: Int
    ): ApiResponse<String>

    /**
     * 查询资产
     */
    @POST("asset/find")
    suspend fun assetFind(): ApiResponse<Asset>

    /**
     * 提现
     */
    @POST("asset/withdraw")
    suspend fun assetWithdraw(@Body withdrawRequest: WithdrawRequest): ApiResponse<String>
}