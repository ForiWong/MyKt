package com.wlp.mykt.data.api

import com.wlp.mykt.data.entity.AppVersionInfo
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    /**
     * 检查app版本更新
     */
    @POST("app/v1/version/checkVersion")
    suspend fun checkVersion(@Query("currentVersion") version: String): AppVersionInfo
    //suspend fun checkVersion(@Query("currentVersion") version: String): BaseResponse<AppVersionInfo>

}