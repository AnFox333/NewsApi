package com.chnu.news.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chnu.news.headlines.HeadlinesInteractor
import com.chnu.news.network.response.ArticleModel
import com.chnu.news.network.ui_wrapper.InteractorData
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
                        loaderLiveData.postValue(false)
                    }
                    is ResponseError -> {
                        errorLiveData.postValue(this.message)
                        loaderLiveData.postValue(false)
                    }
                }
            }
        }
    }

    fun getSearchedNewsByTitle(title: String){
        loaderLiveData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            with(headlinesNews.getSearchedHeadlines(title)) {
                when (this) {
                    is ResponseData -> {
                        listLiveData.postValue(this.articles)
                        loaderLiveData.postValue(false)
                    }
                    is ResponseError -> {
                        errorLiveData.postValue(this.message)
                        loaderLiveData.postValue(false)
                    }
                }
            }
        }
    }

    fun getSearchedNewsByTitleAndBody(title: String?, body : String){
        loaderLiveData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            with(headlinesNews.getSearchEverything(title, body)) {
                when (this) {
                    is ResponseData -> {
                        listLiveData.postValue(this.articles)
                        loaderLiveData.postValue(false)
                    }
                    is ResponseError -> {
                        errorLiveData.postValue(this.message)
                        loaderLiveData.postValue(false)
                    }
                }
            }
        }
    }

   fun getHeadlines(){
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

    fun getSearchedSorted(title: String?, body : String?, sortedBy: String){
        loaderLiveData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            with(headlinesNews.getSearchSortedEverything(title, body, sortedBy)) {
                when (this) {
                    is ResponseData -> {
                        listLiveData.postValue(this.articles)
                        loaderLiveData.postValue(false)
                    }
                    is ResponseError -> {
                        errorLiveData.postValue(this.message)
                        loaderLiveData.postValue(false)
                    }
                }
            }
        }
    }
}