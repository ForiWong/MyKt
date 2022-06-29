package com.wlp.mykt.data

import com.wlp.mykt.data.remote.ApiService
import com.wlp.mykt.data.repository.ExampleRepositoryImpl
import com.wlp.mykt.data.repository.ExampleRepository

object RepositoryFactory {

    //这里获取到仓库供ViewModel使用 private val ExampleRepository: Repository
    fun getExampleRepository(api: ApiService/*, db: DbManager*/): ExampleRepository =
        ExampleRepositoryImpl(api)

}