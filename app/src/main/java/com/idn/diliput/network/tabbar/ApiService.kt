package com.idn.diliput.network.tabbar

import com.idn.diliput.response.ArticlesItem
import com.idn.diliput.response.DataResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines?country=id")
    fun getTopHeadLines(
       @Query("category") category: String?,
       @Query("apiKey") apiKey: String = "e3363e46424146d48372eaced7cae5f7",
    ): Flowable<DataResponse>
}