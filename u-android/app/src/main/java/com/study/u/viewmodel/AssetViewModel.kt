package com.study.u.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.u.data.Asset
import com.study.u.data.request.WithdrawRequest
import com.study.u.exception.APIException
import com.study.u.network.ApiError
import com.study.u.network.HttpRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class AssetViewModel : ViewModel() {

    private val liveData: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    fun subscribe() = liveData

    fun setValue() {
        liveData.value = 1
    }

    private val assetLiveData by lazy {
        MutableLiveData<Asset>()
    }

    private val withdrawLiveData by lazy {
        MutableLiveData<String>()
    }

    private val failLiveData by lazy {
        MutableLiveData<ApiError>()
    }

    fun subscribeAsset(): MutableLiveData<Asset> {
        this.findAsset()
        return assetLiveData
    }

    fun subscribeWithdraw(): MutableLiveData<String> = withdrawLiveData

    fun fail(): MutableLiveData<ApiError> = failLiveData

    private fun findAsset() {
        viewModelScope.launch {
            try {
                val asset = withContext(Dispatchers.IO) {
                    HttpRepository.assetFind()
                }
                assetLiveData.value = asset
            } catch (e: Exception) {
                e.printStackTrace()
                if (e is APIException) {
                    failLiveData.value = ApiError(e.code, e.message)
                } else {
                    failLiveData.value = ApiError(0, e.message)
                }
            }
        }
    }

    fun withdraw(address: String, number: Int) {
        viewModelScope.launch {
            try {
                val string = withContext(Dispatchers.IO) {
                    HttpRepository.assetWithdraw(WithdrawRequest(address, number))
                }
                withdrawLiveData.value = string
            } catch (e: Exception) {
                e.printStackTrace()
                if (e is APIException) {
                    failLiveData.value = ApiError(e.code, e.message)
                } else {
                    failLiveData.value = ApiError(0, e.message)
                }
            }
        }
    }
}