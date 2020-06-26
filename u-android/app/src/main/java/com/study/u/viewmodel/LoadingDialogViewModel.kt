package com.study.u.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoadingDialogViewModel : ViewModel() {

    private val liveData: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    fun subscribe() = liveData

    fun setValue() {
        liveData.value = 1
    }

    fun setValue2() {
        liveData.value = 2
    }
}