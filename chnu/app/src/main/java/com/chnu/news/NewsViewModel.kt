package com.chnu.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chnu.news.headlines.HeadlinesInteractor
import com.chnu.news.network.ui_wrapper.InteractorData
import com.chnu.news.network.ui_wrapper.ResponseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(private val headlinesNews: HeadlinesInteractor) : ViewModel(){

    init {
        Log.d("CheckViewModel","Init")
        viewModelScope.launch(Dispatchers.IO) {
            with(headlinesNews.getTopHeadlines()) {
                when (this) {
                    is ResponseData -> Log.d("CheckViewModel", "${this.articles}")

                    else -> Log.d("CheckViewModel", "")
                }
            }
        }
    }
}