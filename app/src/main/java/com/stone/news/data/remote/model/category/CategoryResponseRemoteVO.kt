package com.stone.news.data.remote.model.category

import com.squareup.moshi.Json

data class CategoryResponseRemoteVO(
    @Json(name = "status")
    val status : String?,
    @Json(name = "sources")
    val data : List<CategoryRemoteVO>?,

    )