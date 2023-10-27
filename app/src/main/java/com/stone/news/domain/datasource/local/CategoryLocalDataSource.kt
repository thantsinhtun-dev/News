package com.stone.news.domain.datasource.local

import com.stone.news.data.local.database.entity.CategoryVO
import com.stone.news.data.remote.model.category.CategoryRemoteVO
import kotlinx.coroutines.flow.Flow

interface CategoryLocalDataSource {

    suspend fun saveCategoryList(categoryVO: List<CategoryVO>)
    fun getAllCategories() : Flow<List<CategoryVO>>

 }