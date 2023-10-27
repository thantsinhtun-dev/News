package com.stone.news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stone.news.data.local.database.dao.CategoryDao
import com.stone.news.data.local.database.dao.NewsDao
import com.stone.news.data.local.database.entity.CategoryVO
import com.stone.news.data.local.database.entity.NewsVO

@Database(
    entities = [NewsVO::class, CategoryVO::class], version = 1, exportSchema = false
)
abstract  class NewsDatabase : RoomDatabase() {
    abstract fun getCategoryDao(): CategoryDao
    abstract fun getNewsDao(): NewsDao


}