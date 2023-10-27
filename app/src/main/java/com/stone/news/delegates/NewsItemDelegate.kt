package com.stone.movieapp.delegates

import com.stone.news.data.local.database.entity.NewsVO

interface NewsItemDelegate {
    fun onClickNews(vo: NewsVO)
    fun onClickBookMark( vo:NewsVO)
}