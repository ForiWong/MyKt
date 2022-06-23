package com.wlp.mykt.example

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner

private const val TAG = "SecondViewModel"
class SecondViewModel : AndroidViewModel, IBaseViewModel {
    var mText: ObservableField<String> = ObservableField<String>("第二个文本")

    constructor(application: Application) : super(application) {

    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "lifecycle onCleared()")
    }

    override fun onCreate(owner: LifecycleOwner) {
        Log.d(TAG, "lifecycle onCreate()")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Log.d(TAG, "lifecycle onDestroy()")
    }

    override fun onStart(owner: LifecycleOwner) {
        Log.d(TAG, "lifecycle onStart()")
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.d(TAG, "lifecycle onStop()")
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.d(TAG, "lifecycle onResume()")
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.d(TAG, "lifecycle onPause()")
    }
}
