package com.chnu.news.headlines

import com.chnu.news.network.NewsApi
import com.chnu.news.network.response.ResponseStatus
import com.chnu.news.network.ui_wrapper.InteractorData
import com.chnu.news.network.ui_wrapper.ResponseData
import com.chnu.news.network.ui_wrapper.ResponseError
import java.lang.Exception

class HeadlinesInteractor(private val newsApi: NewsApi) {

    suspend fun getTopHeadlines(): InteractorData {
        return try {
            with(newsApi.getTopHeadlines()) {
                when {
                    isSuccessful && body() != null && body()!!.status == ResponseStatus.OK -> ResponseData(
                        totalResults = body()!!.totalResults,
                        articles = body()!!.articles
                    )
                    isSuccessful && body() != null && body()!!.status == ResponseStatus.ERROR -> ResponseError(
                        code = body()!!.code ?: "NetworkError",
                        message = body()!!.message ?: "Network Error Message"
                    )
                    else -> ResponseError(
                        code = body()!!.code ?: "NetworkError",
                        message = body()!!.message ?: "Idk Error Message"
                    )
                }

            }
        } catch (e: Exception) {
            ResponseError(code = "${e.message}", message = "Ooops! Smth went wrong")
        }
    }
}