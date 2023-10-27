package com.stone.news.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.stone.news.data.local.database.entity.NewsVO
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(activities: List<NewsVO>)

    @Query("SELECT * FROM news")
    fun getAllNews(): Flow<List<NewsVO>>

    @Query("SELECT * FROM news where categoryId = :category")
    fun getNewsByCategory(category:String): Flow<List<NewsVO>>

    @Query("SELECT * FROM news where bookmark = 1 order by bookMarkTime")
    fun getAllSaveNews(): Flow<List<NewsVO>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNews(vo: NewsVO)


}