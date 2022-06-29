package com.wlp.mykt.data.api

/**
 * Description: 密封类，处理成功或者失败的情况
 */
sealed class KtResult<out T> {
    data class Success<out T>(val value: T) : KtResult<T>()

    data class Failure(val throwable: Throwable?) : KtResult<Nothing>()
}

inline fun <reified T> KtResult<T>.doSuccess(success: (T) -> Unit) {
    if (this is KtResult.Success) {
        success(value)
    }
}

inline fun <reified T> KtResult<T>.doFailure(failure: (Throwable?) -> Unit) {
    if (this is KtResult.Failure) {
        failure(throwable)
    }
}
