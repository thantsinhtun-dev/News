package com.stone.news.data.local.datasource

import androidx.annotation.WorkerThread
import com.stone.news.data.local.database.dao.NewsDao
import com.stone.news.data.local.database.entity.NewsVO
import com.stone.news.domain.datasource.local.NewsLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsLocalDataSourceImpl @Inject constructor(
    private val newsDao: NewsDao
) : NewsLocalDataSource{
    override suspend fun saveNewsList(newsVOList: List<NewsVO>) {
       newsDao.insertNews(newsVOList)
    }

    override suspend fun getAllNews(): Flow<List<NewsVO>> {
        return newsDao.getAllNews()
    }

    override suspend fun getNewsByCategory(id: String): Flow<List<NewsVO>> {

        return newsDao.getNewsByCategory(category = id)

    }

    override suspend fun getAllSavedNews(): Flow<List<NewsVO>> {
        return newsDao.getAllSaveNews()
    }

    override suspend fun updateNews(vo: NewsVO) {
            newsDao.updateNews(vo)

    }

}