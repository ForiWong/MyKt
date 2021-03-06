package com.wlp.mykt.example

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import com.wlp.mykt.util.LogUtils

private const val TAG = "SecondViewModel"
class SecondViewModel : AndroidViewModel, IBaseViewModel {
    var mText: ObservableField<String> = ObservableField<String>("第二个文本")

    constructor(application: Application) : super(application) {

    }

    override fun onCleared() {
        super.onCleared()
        LogUtils.d(TAG, "lifecycle onCleared()")
    }

    override fun onCreate(owner: LifecycleOwner) {
        LogUtils.d(TAG, "lifecycle onCreate()")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        LogUtils.d(TAG, "lifecycle onDestroy()")
    }

    override fun onStart(owner: LifecycleOwner) {
        LogUtils.d(TAG, "lifecycle onStart()")
    }

    override fun onStop(owner: LifecycleOwner) {
        LogUtils.d(TAG, "lifecycle onStop()")
    }

    override fun onResume(owner: LifecycleOwner) {
        LogUtils.d(TAG, "lifecycle onResume()")
    }

    override fun onPause(owner: LifecycleOwner) {
        LogUtils.d(TAG, "lifecycle onPause()")
    }
}
