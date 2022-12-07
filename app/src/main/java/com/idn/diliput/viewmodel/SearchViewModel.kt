package com.idn.diliput.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idn.diliput.network.search.ApiClient
import com.idn.diliput.response.ArticlesItem
import com.idn.diliput.response.ResultsItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchViewModel : ViewModel() {

    val dataResponse = MutableLiveData<List<ResultsItem>>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()

    fun getSearchNews(
        searchName : String?,
        responseHandlerNews: (List<ResultsItem>) -> Unit,
        error: (Throwable) -> Unit
    ) {


        ApiClient().getApiService().getSearchNews(searchName).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                responseHandlerNews(it.results as List<ResultsItem>)
            }, {
                error(it)
            })
    }


    fun getDataSearch(searchName: String?) {
        isLoading.value = true
        getSearchNews(
            searchName,{
                isLoading.value = false
                dataResponse.value = it
            }, {
                isLoading.value = false
                isError.value = it
            })
    }
}