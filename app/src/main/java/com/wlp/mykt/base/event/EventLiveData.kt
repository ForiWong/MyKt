package com.wlp.mykt.base.event

import com.kunminx.architecture.ui.callback.UnPeekLiveData

/**
 * 最新版的发送消息LiveData使用 https://github.com/KunMinX/UnPeek-LiveData
 * 具体写法请参考 https://github.com/KunMinX/UnPeek-LiveData的示例
 */
class EventLiveData<T> : UnPeekLiveData<T>() {

    fun call() {
        super.postValue(null)
    }
}