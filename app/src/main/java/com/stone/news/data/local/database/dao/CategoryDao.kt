package com.stone.news.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stone.news.data.local.database.entity.CategoryVO
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(activities: List<CategoryVO>)

    @Query("SELECT * FROM category")
    fun getAllCategory(): Flow<List<CategoryVO>>


}