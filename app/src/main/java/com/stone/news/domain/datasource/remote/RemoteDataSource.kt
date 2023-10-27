package com.stone.news.domain.datasource.remote

import android.view.View
import com.stone.news.data.remote.model.category.CategoryResponseRemoteVO
import com.stone.news.data.remote.model.news.NewsResponseRemoteVO
import com.stone.news.utils.NetworkResult
import com.stone.news.utils.ViewState
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getNews(categoryId: String): Flow<ViewState<NewsResponseRemoteVO>>
    fun getCategories(): Flow<ViewState<CategoryResponseRemoteVO>>

}