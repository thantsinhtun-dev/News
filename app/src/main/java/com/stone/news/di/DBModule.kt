package com.stone.news.di

import android.content.Context
import androidx.room.Room
import com.stone.news.data.local.NewsDatabase
import com.stone.news.data.local.database.dao.CategoryDao
import com.stone.news.data.local.database.dao.NewsDao
import com.stone.news.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Provides
    fun providesMoodDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(context, NewsDatabase::class.java, DATABASE_NAME)
            .build()
    }

    @Provides
    fun providesCategoryDao(db:NewsDatabase) : CategoryDao{
        return  db.getCategoryDao()
    }

    @Provides
    fun providesNewsDao(db:NewsDatabase) : NewsDao{
        return  db.getNewsDao()
    }
}