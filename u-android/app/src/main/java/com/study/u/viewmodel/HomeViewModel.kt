package com.study.u.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.u.data.Product
import com.study.u.network.HttpRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class HomeViewModel : ViewModel() {

    private var productListLiveData: MutableLiveData<List<Product>?>? = null

    fun getWeatherLiveData(): LiveData<List<Product>?>? {
        if (productListLiveData == null) {
            productListLiveData = MutableLiveData<List<Product>?>()
            loadProductList()
        }
        return productListLiveData
    }

    private fun loadProductList() {
        viewModelScope.launch {
            try {
                val productList = withContext(Dispatchers.IO) {
                    HttpRepository.productList()
                }
                productListLiveData?.value = productList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}