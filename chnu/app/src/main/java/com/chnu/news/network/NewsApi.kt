package com.chnu.news.network

import com.chnu.news.network.response.NewsResponse
import retrofit2.Response

interface NewsApi {

    suspend fun getTopHeadlines() : Response<NewsResponse>

}
