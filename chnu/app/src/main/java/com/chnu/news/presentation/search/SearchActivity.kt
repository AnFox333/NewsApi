package com.chnu.news.presentation.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import com.chnu.news.R
import com.chnu.news.presentation.navigation.SearchActivityResultContract
import com.chnu.news.presentation.navigation.SearchBackData

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        findViewById<ImageView>(R.id.arrowBack).setOnClickListener {
            setResult(RESULT_OK, Intent().apply {
                putExtra(
                    SearchActivityResultContract.SEARCH_STRING,
                    SearchBackData(
                        findViewById<EditText>(R.id.searchByTitle).text.toString(),
                        findViewById<EditText>(R.id.searchByContent).text.toString()
                    )
                )
            })
            finish()

        }
    }
}