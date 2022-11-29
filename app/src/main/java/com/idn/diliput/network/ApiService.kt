package com.idn.diliput.network

import com.idn.diliput.response.DataResponseItem
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface ApiService {

    @GET("everything?domains=wsj.com&apiKey=b088175ab52747bf89a7ca5957114a9c")
    fun byWallStreetJournal() : Flowable<DataResponseItem>

    @GET("everything?q=tesla&from=2022-10-29&sortBy=publishedAt&apiKey=b088175ab52747bf89a7ca5957114a9c")
    fun aboutTesla() : Flowable<DataResponseItem>

    @GET("everything?q=apple&from=2022-11-28&to=2022-11-28&sortBy=popularity&apiKey=b088175ab52747bf89a7ca5957114a9c")
    fun aboutApple() : Flowable<DataResponseItem>

}