package com.wlp.mykt.data.repository

import com.wlp.mykt.data.entity.AppVersionInfo
import com.wlp.mykt.data.api.KtResult
import kotlinx.coroutines.flow.Flow

interface ExampleRepository {

    suspend fun checkVersion(version: String): Flow<KtResult<AppVersionInfo>>

}