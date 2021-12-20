package com.chnu.news.presentation.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.widget.doOnTextChanged
import com.chnu.news.R
import com.chnu.news.presentation.navigation.SearchActivityResultContract
import com.chnu.news.presentation.navigation.SearchBackData
import com.chnu.news.visibilityIf

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        findViewById<Switch>(R.id.spinner).setOnCheckedChangeListener { _, isChecked ->
            if(isChecked && findViewById<Switch>(R.id.switchPublish).isChecked){
                findViewById<Switch>(R.id.switchPublish).isChecked = false
            }
        }

        findViewById<EditText>(R.id.searchByTitle).doOnTextChanged { text, start, before, count ->
            findViewById<Switch>(R.id.switchPublish).visibilityIf(text.isNullOrBlank().not())
            findViewById<Switch>(R.id.spinner).visibilityIf(text.isNullOrBlank().not())
        }


        findViewById<Switch>(R.id.switchPublish).setOnCheckedChangeListener { _, isChecked ->
            if(isChecked && findViewById<Switch>(R.id.spinner).isChecked){
                findViewById<Switch>(R.id.spinner).isChecked = false
            }
        }
        findViewById<ImageView>(R.id.arrowBack).setOnClickListener {
            setResult(RESULT_OK, Intent().apply {
                putExtra(
                    SearchActivityResultContract.SEARCH_STRING,
                    SearchBackData(
                        findViewById<EditText>(R.id.searchByTitle).text.toString(),
                        findViewById<EditText>(R.id.searchByContent).text.toString(),
                        findViewById<Switch>(R.id.switchPublish).isChecked,
                        findViewById<Switch>(R.id.spinner).isChecked
                    )
                )
            })
            finish()

        }
    }
}