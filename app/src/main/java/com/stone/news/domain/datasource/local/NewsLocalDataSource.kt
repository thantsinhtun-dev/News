package com.stone.news.domain.datasource.local

import com.stone.news.data.local.database.entity.NewsVO
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {

    suspend fun saveNewsList(newsVOList: List<NewsVO>)
    suspend fun getAllNews() : Flow<List<NewsVO>>

    suspend fun getNewsByCategory(id:String) :  Flow<List<NewsVO>>

    suspend fun getAllSavedNews() :  Flow<List<NewsVO>>

    suspend fun updateNews(vo: NewsVO)

}