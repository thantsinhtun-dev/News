package com.stone.news.data.remote

import com.stone.news.data.remote.model.category.CategoryResponseRemoteVO
import com.stone.news.data.remote.model.news.NewsResponseRemoteVO
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v2/sources")
    suspend fun getCategories(
        @Query("country") country: String = "us",
        @Query("language") language: String = "en"
    ): CategoryResponseRemoteVO

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("sources") sources: String,
    ): NewsResponseRemoteVO

}