package com.stone.news.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.stone.news.data.remote.model.category.CategoryRemoteVO

@Entity(tableName = "Category")
data class CategoryVO(
    @PrimaryKey()
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "language")
    val language: String,
    @ColumnInfo(name = "country")
    val country: String
) {
    constructor(
        vo: CategoryRemoteVO
    ) : this(
        id = vo.id,
        name = vo.name,
        description = vo.description,
        category = vo.category,
        language = vo.language,
        country = vo.country
    )
}