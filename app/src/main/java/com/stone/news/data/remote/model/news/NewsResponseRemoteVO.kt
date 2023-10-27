package com.stone.news.data.remote.model.news

import com.squareup.moshi.Json

data class NewsResponseRemoteVO(
    @Json(name = "status")
    val status: String,
    @Json(name = "message")
    val message: String?,
    @Json(name = "articles")
    val data: List<NewsRemoteVO>?,

    )
