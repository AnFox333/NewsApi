package com.chnu.news.headlines

import com.chnu.news.network.NewsApi
import com.chnu.news.network.response.NewsResponse
import com.chnu.news.network.response.ResponseStatus
import com.chnu.news.network.ui_wrapper.InteractorData
import com.chnu.news.network.ui_wrapper.ResponseData
import com.chnu.news.network.ui_wrapper.ResponseError
import retrofit2.Response
import java.lang.Exception

class HeadlinesInteractorImpl(private val newsApi: NewsApi) : HeadlinesInteractor {

    override suspend fun getTopHeadlines(): InteractorData {
        return try {
            with(newsApi.getTopHeadlines()) {
                validateResponse()
            }
        } catch (e: Exception) {
            ResponseError(code = "${e.message}", message = "Ooops! Smth went wrong")
        }
    }

    override suspend fun getSearchedHeadlines(searched : String): InteractorData {
        return try {
            with(newsApi.getSearchHeadlines(searched)) {
                validateResponse()
            }
        } catch (e: Exception) {
            ResponseError(code = "${e.message}", message = "Ooops! Smth went wrong")
        }
    }

    override suspend fun getSearchEverything(title: String?, body: String): InteractorData {
        return try {
            with(newsApi.getSearchEverything(title, body)) {
                validateResponse()
            }
        } catch (e: Exception) {
            ResponseError(code = "${e.message}", message = "Ooops! Smth went wrong")
        }
    }

    private fun Response<NewsResponse>.validateResponse() =
        when {
            isSuccessful && body() != null && body()!!.status == ResponseStatus.ok -> ResponseData(
                totalResults = body()!!.totalResults,
                articles = body()!!.articles
            )
            isSuccessful && body() != null && body()!!.status == ResponseStatus.error -> ResponseError(
                code = body()!!.code ?: "NetworkError",
                message = body()!!.message ?: "Network Error Message"
            )
            else -> ResponseError(
                code = body()!!.code ?: "NetworkError",
                message = body()!!.message ?: "Idk Error Message"
            )
        }
}

interface HeadlinesInteractor {
    suspend fun getTopHeadlines(): InteractorData
    suspend fun getSearchedHeadlines(searched : String): InteractorData
    suspend fun getSearchEverything(title : String?, body : String) : InteractorData
}