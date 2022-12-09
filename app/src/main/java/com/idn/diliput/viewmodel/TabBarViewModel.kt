package com.idn.diliput.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idn.diliput.network.tabbar.ApiClient
import com.idn.diliput.response.ArticlesItem
import com.idn.diliput.response.DataResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class TabBarViewModel : ViewModel() {
    val listData = MutableLiveData<List<ArticlesItem>>()

    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()

    fun getDataNews(
        searchHeadLine : String?,
        responHandler: (List<ArticlesItem>) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        ApiClient().getApiService().getTopHeadLines(searchHeadLine, ).subscribeOn(Schedulers.io())
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
}