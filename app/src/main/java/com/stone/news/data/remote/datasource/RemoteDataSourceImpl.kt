package com.stone.news.data.remote.datasource

import com.stone.news.data.remote.ApiService
import com.stone.news.data.remote.model.category.CategoryResponseRemoteVO
import com.stone.news.data.remote.model.news.NewsResponseRemoteVO
import com.stone.news.domain.datasource.remote.NewsRemoteDataSource
import com.stone.news.domain.datasource.remote.RemoteDataSource
import com.stone.news.utils.NetworkResult
import com.stone.news.utils.Status
import com.stone.news.utils.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {

    override fun getCategories() = flow {
        emit(ViewState.loading())
        val response = apiService.getCategories()
        emit(ViewState.success(response))
    }.flowOn(Dispatchers.IO)
    override fun getNews(categoryId: String)  = flow {
        emit(ViewState.loading())
        val response = apiService.getTopHeadlines(sources = categoryId)
        emit(ViewState.success(response))
    }.flowOn(Dispatchers.IO)

}