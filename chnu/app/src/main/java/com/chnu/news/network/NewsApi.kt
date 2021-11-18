package com.chnu.news.network

import com.chnu.news.network.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines() : Response<NewsResponse>

}
