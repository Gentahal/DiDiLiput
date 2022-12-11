package com.idn.diliput.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.idn.diliput.network.tabbar.ApiClient
import com.idn.diliput.response.ArticlesItem
import com.idn.diliput.response.DataResponse
import com.idn.diliput.response.ResultsItem
import com.idn.diliput.room.NewsDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TabBarViewModel(application: Application) : AndroidViewModel(application) {
    val listData = MutableLiveData<List<ArticlesItem>>()

    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()

    fun getDataNews(
        searchHeadLine : String?,
        responHandler: (List<ArticlesItem>) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        ApiClient().getApiService().getTopHeadLines(searchHeadLine).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                responHandler(it.articles as List<ArticlesItem>)
            },{
                errorHandler(it)
            })
    }


    fun getHeadlineNews(searchHeadline : String?) {
        isLoading.value = true
        getDataNews(
            searchHeadline,{
                isLoading.value = false
                listData.value = it
            }, {
                isLoading.value = false
                isError.value = it
            }
        )
    }

    //Room

    private val newsDao = NewsDatabase.getDatabase(application).newsDao()


    fun bookmarkNews(news: ArticlesItem) {
        viewModelScope.launch(Dispatchers.IO) {
            newsDao.insert(news.asNewsEntity())
        }
    }

    fun unBookmarkNews(news: ArticlesItem) {
        viewModelScope.launch(Dispatchers.IO) {
            newsDao.delete(news.asNewsEntity())
        }
    }

    fun isNewsBookmarked(news: ArticlesItem): LiveData<Boolean> {
        return Transformations.map(newsDao.isBookmark(news.title as String)) {
            it == 1
        }
    }

    fun getAllBookmarkedNews(): LiveData<List<ArticlesItem>> {
        return Transformations.map(newsDao.getAllData()) { list ->
            list.map { it.asBookmarkResponse() }
        }
    }
}