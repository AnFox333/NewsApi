package com.chnu.news.network.ui_wrapper

import com.chnu.news.network.response.ArticleModel

sealed interface InteractorData
class ResponseData(val totalResults: Int, val articles: List<ArticleModel>) : InteractorData
class ResponseError(val code: String, val message: String) : InteractorData
