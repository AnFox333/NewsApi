package com.chnu.news.network

import com.chnu.news.network.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines() : Response<NewsResponse>

    @GET("top-headlines")
    suspend fun getSearchHeadlines(@Query("q") searchValue : String) : Response<NewsResponse>

}
