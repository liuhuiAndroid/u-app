package com.study.u.network

import com.study.u.BuildConfig
import com.study.u.data.Product
import com.study.u.data.request.LoginRequest
import com.study.u.exception.APIException
import com.study.u.ext.decode
import com.study.u.utilities.MMKVConstants
import com.study.u.utilities.ZY_SOURCE
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

object HttpRepository {

    /**
     * 数据脱壳与错误预处理
     */
    private fun <T> preProcessData(responseBody: ApiResponse<T>): T {
        return if (responseBody.code == 0 && responseBody.data != null) responseBody.data
        else throw APIException(responseBody.code, responseBody.msg)
    }

    private fun getApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
            .create(ApiService::class.java)
    }

    private fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().apply {
            connectTimeout(5, TimeUnit.SECONDS)
            writeTimeout(5, TimeUnit.MINUTES)
            readTimeout((60 * 3), TimeUnit.SECONDS)
            addInterceptor(provideLoggingInterceptor())
            addInterceptor(provideHeaderInterceptor())
        }.build()

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Timber.i(it)
        }).apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

    private fun provideHeaderInterceptor(): Interceptor =
        Interceptor {
            val timestamp = System.currentTimeMillis().toString()
            val token = decode(MMKVConstants.TOKEN, "")
            val request = it.request().newBuilder()
                .addHeader("Connection", "close")
                .addHeader("timestamp", timestamp)
                .addHeader("token", token)
                .addHeader("app_type", ZY_SOURCE)
                .build()
            it.proceed(request)
        }

    /**
     * 登录
     */
    suspend fun userLogin(
        loginRequest: LoginRequest
    ): String {
        val responseBody = getApiService().userLogin(loginRequest)
        return preProcessData(responseBody)
    }

    /**
     * 商品列表
     */
    suspend fun productList(): List<Product>? {
        val responseBody = getApiService().productList()
        return preProcessData(responseBody)
    }

}