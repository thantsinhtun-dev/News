package com.stone.news.ui.savenews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stone.news.data.local.database.entity.NewsVO
import com.stone.news.domain.datasource.local.CategoryLocalDataSource
import com.stone.news.domain.datasource.local.NewsLocalDataSource
import com.stone.news.domain.datasource.remote.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveNewsVM @Inject constructor(
    val remoteDataSource: RemoteDataSource,
    val newsLocalDataSource: NewsLocalDataSource,
    val categoryLocalDataSource: CategoryLocalDataSource
) : ViewModel() {
    private val _newsList = MutableLiveData<List<NewsVO>>()
    val newsList: LiveData<List<NewsVO>> = _newsList

    init {
        fetchSavedNews()
    }

    fun fetchSavedNews(){
        viewModelScope.launch {
            newsLocalDataSource.getAllSavedNews()
                .catch {


                }
                .collect{
                    Log.i("Test Fire in save vm","${  it.toString() }")

                    _newsList.value = it
                }
        }
    }

    fun updateNews(vo: NewsVO) {
        viewModelScope.launch {
            newsLocalDataSource.updateNews(vo)
        }
    }
}
