package com.wlp.mykt.example

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wlp.mykt.util.LogUtils

private const val TAG = "FirstViewModel"
class FirstViewModel : ViewModel() {
    var text: ObservableField<String> = ObservableField<String>("我的名字是发哥")
    var number: MutableLiveData<Int> = MutableLiveData(11)

    fun get(): MutableLiveData<Int> {
        return number
    }

    fun add(value: Int) {
        number.value = number.value?.plus(value)
    }

    fun set(value: Int) {
        number.value = value
    }

    override fun onCleared() {
        super.onCleared()
        LogUtils.d(TAG, "lifecycle onCleared()")
    }
}
