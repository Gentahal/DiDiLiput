package com.idn.diliput.network.search

import com.idn.diliput.response.ArticlesItem
import com.idn.diliput.response.ResultsItem
import com.idn.diliput.response.SearchDataResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("news?apikey=pub_140045eae9852ac5f3fb4035ce1da28cf65a2&")
    fun getSearchNews(
        @Query("q") Search : String?
    ) : Flowable<SearchDataResponse>

}