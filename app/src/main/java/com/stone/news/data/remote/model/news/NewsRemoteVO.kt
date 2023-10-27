package com.stone.news.data.remote.model.news

import com.squareup.moshi.Json

data class NewsRemoteVO(
    @Json(name = "author")
    val author: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "urlToImage")
    val urlToImage: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "publishedAt")
    val publishedAt: String?,
    @Json(name = "content")
    val content: String?,
    @Json(name = "source")
    val sources: SourceRemoteVO

)
data class SourceRemoteVO (
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
)
