package com.stone.news.domain.datasource.remote

import com.stone.news.data.local.database.entity.NewsVO
import com.stone.news.data.remote.model.category.CategoryResponseRemoteVO
import com.stone.news.data.remote.model.news.NewsResponseRemoteVO
import com.stone.news.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface NewsRemoteDataSource {
    fun getNews(categoryId: String): Flow<NetworkResult<NewsResponseRemoteVO>>

}