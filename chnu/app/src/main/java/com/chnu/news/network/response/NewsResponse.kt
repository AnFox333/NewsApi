package com.chnu.news.network.response

data class NewsResponse(
    val status: ResponseStatus, val totalResults: Int, val articles: List<ArticleModel>,
    val code: String? = null,//TODO just for error status
    val message: String? = null //TODO just for error status
)

data class ArticleModel(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String
)

data class Source(val id: String? = null, val name: String)

enum class ResponseStatus{
    ok, error
}