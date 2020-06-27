package com.study.u.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.study.u.data.request.LoginRequest
import com.study.u.data.request.ModifyPasswordRequest
import com.study.u.exception.APIException
import com.study.u.network.ApiError
import com.study.u.network.HttpRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception

class AccountViewModel(application: Application) : AndroidViewModel(application) {

    private val failLiveData by lazy {
        MutableLiveData<ApiError>()
    }

    private val loginLiveData by lazy {
        MutableLiveData<String>()
    }

    private val modifyPasswordLiveData by lazy {
        MutableLiveData<String>()
    }

    fun fail(): MutableLiveData<ApiError> = failLiveData

    fun subscribeLogin(): MutableLiveData<String> = loginLiveData

    fun subscribeModifyPassword(): MutableLiveData<String> = modifyPasswordLiveData

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val token = withContext(Dispatchers.IO) {
                    HttpRepository.userLogin(LoginRequest(username, password))
                }
                Timber.i("token:${token}")
                loginLiveData.value = token
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

    fun register(username: String, password: String) {
        viewModelScope.launch {
            try {
                val token = withContext(Dispatchers.IO) {
                    HttpRepository.userRegister(LoginRequest(username, password))
                }
                Timber.i("token:${token}")
                loginLiveData.value = token
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

    fun modifyPassword(oldPassword: String, newPassword: String) {
        viewModelScope.launch {
            try {
                val message = withContext(Dispatchers.IO) {
                    HttpRepository.userModifyPassword(ModifyPasswordRequest(oldPassword, newPassword))
                }
                modifyPasswordLiveData.value = message
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