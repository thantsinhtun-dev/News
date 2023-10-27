package com.stone.news.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.stone.news.data.remote.model.news.NewsRemoteVO
import java.io.Serializable
import java.util.function.BiPredicate

@Entity(tableName = "News")
data class NewsVO(
    @PrimaryKey()
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "categoryId")
    val categoryId: String,

    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "urlToImage")
    val urlToImage: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String,
    @ColumnInfo(name = "content")
    val content: String?,
    @ColumnInfo(name = "saveFromDetail")
    var saveFromDetail : Boolean,
    @ColumnInfo(name = "bookMarkTime")
    var bookMarkTime : Long,
    @ColumnInfo(name = "bookmark")
    var bookmark: Boolean = false
) : Serializable {
    constructor(vo:NewsRemoteVO) : this(
        title = vo.title ?: "",
        author = vo.author ?: "",
        categoryId = vo.sources.id ?: "",
        description = vo.description ?: "",
        urlToImage = vo.urlToImage ?: "",
        url = vo.url ?: "",
        publishedAt = vo.publishedAt ?: "",
        content = vo.content ?: "",
        saveFromDetail = false,
        bookMarkTime = System.currentTimeMillis(),
    )
}
