package com.chnu.news.network

import com.chnu.news.network.NetworkConstants.API_KEY
import com.chnu.news.network.NetworkConstants.API_KEY_QUERY
import com.chnu.news.network.NetworkConstants.COUNTRY
import com.chnu.news.network.NetworkConstants.COUNTRY_QUERY
import com.chnu.news.network.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query(COUNTRY_QUERY) country: String = COUNTRY): Response<NewsResponse>

    @GET("top-headlines")
    suspend fun getSearchHeadlines(
        @Query("q") searchValue: String,
        @Query(COUNTRY_QUERY) country: String = COUNTRY
    ): Response<NewsResponse>

    @GET("everything")
    suspend fun getSearchEverything(
        @Query("qInTitle") title: String?,
        @Query("q") titleAndBody: String
    ): Response<NewsResponse>

    @GET("everything")
    suspend fun getSearchSortedEverything(
        @Query("q") titleAndBody: String?,
        @Query("sortBy") sortBy: String,
        @Query(API_KEY_QUERY) country: String = API_KEY
    ): Response<NewsResponse>

}
