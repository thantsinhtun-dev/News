package com.stone.news.di

import com.stone.news.data.local.datasource.CategoryLocalDataSourceImpl
import com.stone.news.data.local.datasource.NewsLocalDataSourceImpl
import com.stone.news.data.remote.datasource.RemoteDataSourceImpl
import com.stone.news.domain.datasource.local.CategoryLocalDataSource
import com.stone.news.domain.datasource.local.NewsLocalDataSource
import com.stone.news.domain.datasource.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {

    @Binds
    fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource


}