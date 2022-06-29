package com.wlp.mykt.data.entity

/**
 * Description:app版本信息
 */
data class AppVersionInfo(
    var createTime: String,//创建时间 "2021-02-24 10:53:35"
    var id: Int, //版本号 "0.0.1"
    var version: String, //下载地址
    var url: String, //更新说明
    var description: String,
    var status: Int //是否强制更新标识
)