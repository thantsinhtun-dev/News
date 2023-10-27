package com.stone.news.di

import com.stone.news.data.local.datasource.CategoryLocalDataSourceImpl
import com.stone.news.data.local.datasource.NewsLocalDataSourceImpl
import com.stone.news.domain.datasource.local.CategoryLocalDataSource
import com.stone.news.domain.datasource.local.NewsLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {

    @Binds
    fun bindCategoryLocalDataSource(categoryLocalDataSource: CategoryLocalDataSourceImpl): CategoryLocalDataSource

    @Binds
    fun bindNewsLocalDataSource(newsLocalDataSource: NewsLocalDataSourceImpl): NewsLocalDataSource

}