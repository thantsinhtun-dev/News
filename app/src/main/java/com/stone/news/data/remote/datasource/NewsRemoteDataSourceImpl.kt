package com.stone.news.data.remote.datasource

import com.stone.news.data.remote.ApiService
import com.stone.news.data.remote.model.news.NewsResponseRemoteVO
import com.stone.news.domain.datasource.remote.CategoryRemoteDataSource
import com.stone.news.domain.datasource.remote.NewsRemoteDataSource
import com.stone.news.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRemoteDataSourceImpl  @Inject constructor(
    private val apiService: ApiService
) : NewsRemoteDataSource {


    override fun getNews(categoryId: String)  = flow {
        emit(NetworkResult.Loading(isLoading = true))
        val response = apiService.getTopHeadlines(sources = categoryId)
        emit(NetworkResult.Success(response))
    }.flowOn(Dispatchers.IO)

}