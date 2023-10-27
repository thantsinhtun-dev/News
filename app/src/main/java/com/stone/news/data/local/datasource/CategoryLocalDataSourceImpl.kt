package com.stone.news.data.local.datasource

import com.stone.news.data.local.database.dao.CategoryDao
import com.stone.news.data.local.database.entity.CategoryVO
import com.stone.news.data.remote.model.category.CategoryRemoteVO
import com.stone.news.domain.datasource.local.CategoryLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryLocalDataSourceImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryLocalDataSource{
    override suspend fun saveCategoryList(categoryVO: List<CategoryVO>) {
        categoryDao.insertCategory(categoryVO)
    }

    override fun getAllCategories(): Flow<List<CategoryVO>> {
        return categoryDao.getAllCategory()
    }
}