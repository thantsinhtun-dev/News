package com.stone.news.data.remote.datasource

import com.stone.news.data.remote.ApiService
import com.stone.news.data.remote.model.category.CategoryResponseRemoteVO
import com.stone.news.domain.datasource.remote.CategoryRemoteDataSource
import com.stone.news.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CategoryRemoteDataSourceImpl  @Inject constructor(
    private val apiService: ApiService
) : CategoryRemoteDataSource{
    override fun getCategories() = flow {
        emit(NetworkResult.Loading(isLoading = true))
        val response = apiService.getCategories()
        emit(NetworkResult.Success(response))
    }.flowOn(Dispatchers.IO)

}