package com.wlp.mykt.data.repository

import com.wlp.mykt.data.entity.AppVersionInfo
import com.wlp.mykt.data.remote.ApiService
import com.wlp.mykt.data.remote.KtResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ExampleRepositoryImpl(
    private val api: ApiService/*, val db: DbManager*/
) : ExampleRepository {

    override suspend fun checkVersion(version: String): Flow<KtResult<AppVersionInfo>> {
        return flow {
            try {
                val currentVersion = api.checkVersion(version)
                emit(KtResult.Success(currentVersion))
            } catch (e: Exception) {
                emit(KtResult.Failure(e.cause))
            }
        }.flowOn(Dispatchers.IO) // 通过 flowOn 切换到 io 线程
    }
}