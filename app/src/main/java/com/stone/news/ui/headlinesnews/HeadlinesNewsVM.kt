package com.stone.news.ui.headlinesnews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stone.news.data.local.database.entity.CategoryVO
import com.stone.news.data.local.database.entity.NewsVO
import com.stone.news.domain.datasource.local.CategoryLocalDataSource
import com.stone.news.domain.datasource.local.NewsLocalDataSource
import com.stone.news.domain.datasource.remote.RemoteDataSource
import com.stone.news.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeadlinesNewsVM @Inject constructor(
    val remoteDataSource: RemoteDataSource, val newsLocalDataSource: NewsLocalDataSource, val categoryLocalDataSource: CategoryLocalDataSource
) : ViewModel() {

    private val _categoryList = MutableLiveData<List<CategoryVO>>()
    val categoryList: LiveData<List<CategoryVO>> = _categoryList

    private val _newsList = MutableLiveData<List<NewsVO>>()
    val newsList: LiveData<List<NewsVO>> = _newsList


    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private var selectedCategory: String = ""

    var selectedTabPosition: Int = 0

    init {
        fetchCategory()
    }

    fun loadNewsByCategory(position: Int) {
        val id = categoryList.value?.getOrNull(position)?.id
        fetchNews(id)
    }

    fun updateNews(vo: NewsVO) {
        viewModelScope.launch {
            newsLocalDataSource.updateNews(vo)
        }
    }

    private fun fetchCategory() {
        viewModelScope.launch {
            //load data from Database
            categoryLocalDataSource.getAllCategories().collect {

                _categoryList.value = it

                fetchNews(it.firstOrNull()?.id)

            }
        }
        viewModelScope.launch {
            //load data from Network and save it to database
            remoteDataSource.getCategories().catch {

                _error.value = it.localizedMessage
            }.collect {

                when (it) {
                    is NetworkResult.Loading -> {

                    }

                    is NetworkResult.Success -> {
                        it.data.data.let { data ->
                            data?.map { vo ->
                                CategoryVO(vo)
                            }?.let { list ->
                                categoryLocalDataSource.saveCategoryList(list)
                            }
                        }
                    }

                    is NetworkResult.Failure -> {

                    }
                }
            }

        }


    }

    fun reloadNews() {
        viewModelScope.launch {

            newsLocalDataSource.getAllNews()
                .catch {

                }
                .collectLatest {


                    it.filter { newsVO ->
                        newsVO.categoryId == selectedCategory
                    }.let { data ->
                        _newsList.postValue(data)
                    }
                }


        }
    }

    private fun fetchNews(category: String?) {
        category?.let { id ->

            selectedCategory = id
            viewModelScope.launch {

                newsLocalDataSource.getAllNews()
                    .catch {

                    }
                    .collectLatest {


                        it.filter { newsVO ->
                            newsVO.categoryId == selectedCategory
                        }.let {
                            _newsList.value = it
                        }
                    }


            }

            viewModelScope.launch {
                remoteDataSource.getNews(categoryId = id).catch {
                    _error.value = it.localizedMessage
                }.collect {
                    when (it){
                        is NetworkResult.Loading -> {

                        }
                        is NetworkResult.Success -> {
                            it.data.data.let { data ->
                                data?.map { vo ->
                                    NewsVO(vo)
                                }?.let { list ->
                                    newsLocalDataSource.saveNewsList(list)
                                }
                            }
                        }
                        is NetworkResult.Failure -> {

                        }
                    }
                }
            }


        }

    }


}