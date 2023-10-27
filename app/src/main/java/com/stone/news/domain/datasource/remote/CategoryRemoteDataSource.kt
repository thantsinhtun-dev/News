package com.stone.news.domain.datasource.remote

import com.stone.news.data.remote.model.category.CategoryRemoteVO
import com.stone.news.data.remote.model.category.CategoryResponseRemoteVO
import com.stone.news.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CategoryRemoteDataSource  {


    fun getCategories(): Flow<NetworkResult<CategoryResponseRemoteVO>>

}