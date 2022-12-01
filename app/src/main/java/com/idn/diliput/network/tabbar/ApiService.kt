package com.idn.diliput.network.tabbar

import com.idn.diliput.response.ArticlesItem
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/top-headlines?country=us&category={category}&apiKey=b088175ab52747bf89a7ca5957114a9c")
    fun getNewsArchive(
        @Path("category") category : String?
    ) : Flowable<List<ArticlesItem>>

    @GET("/top-headlines?country=id&apiKey=b088175ab52747bf89a7ca5957114a9c")
    fun getTopHeadLines() : Flowable<List<ArticlesItem>>
}