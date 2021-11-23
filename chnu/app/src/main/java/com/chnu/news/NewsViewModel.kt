package com.chnu.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chnu.news.headlines.HeadlinesInteractor
import com.chnu.news.network.response.ArticleModel
import com.chnu.news.network.ui_wrapper.ResponseData
import com.chnu.news.network.ui_wrapper.ResponseError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(private val headlinesNews: HeadlinesInteractor) : ViewModel(){

    val listLiveData = MutableLiveData<List<ArticleModel>>()
    val loaderLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String>()

    init {
        loaderLiveData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            with(headlinesNews.getTopHeadlines()) {
                when (this) {
                    is ResponseData -> {
                        listLiveData.postValue(this.articles)
                        loaderLiveData.postValue(true)
                    }
                    is ResponseError -> {
                        errorLiveData.postValue(this.message)
                        loaderLiveData.postValue(true)
                    }
                }
            }
        }
    }
}