package com.stone.news.ui.newsdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stone.news.data.local.database.entity.NewsVO
import com.stone.news.domain.datasource.local.NewsLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailVM @Inject constructor(private val newsLocalDataSource: NewsLocalDataSource): ViewModel() {

    val isAddedFavorite: MutableLiveData<Boolean> = MutableLiveData()

    fun addFavoriteArticle(vo: NewsVO) = viewModelScope.launch {
        vo.bookmark = !vo.bookmark
        isAddedFavorite.value =  vo.bookmark
        vo.saveFromDetail = vo.bookmark
        vo.bookMarkTime = System.currentTimeMillis()
        newsLocalDataSource.updateNews(vo)
    }
}