package com.chnu.news.presentation.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.chnu.news.R
import com.chnu.news.presentation.navigation.SearchActivityResultContract

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        Log.d("CheckResultFromSearch", "${intent.getStringExtra(SearchActivityResultContract.SEARCH_STRING)}")
    }
}