package com.bhyoo.mvvm_sample.livedata_viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataCounterViewModel : ViewModel() {
    var count: MutableLiveData<Int> = MutableLiveData()

    init {
        count.value = 0
    }

    fun increase() {
        count.value = count.value?.plus(1)
    }

    fun decrease() {
        count.value = count.value?.minus(1)
    }

}