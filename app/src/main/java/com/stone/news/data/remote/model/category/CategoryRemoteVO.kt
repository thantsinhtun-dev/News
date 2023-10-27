package com.stone.news.data.remote.model.category

import com.squareup.moshi.Json

data class CategoryRemoteVO(


    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "category")
    val category: String,
    @Json(name = "language")
    val language: String,
    @Json(name = "country")
    val country: String,
)
