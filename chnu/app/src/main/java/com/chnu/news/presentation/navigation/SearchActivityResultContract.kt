package com.chnu.news.presentation.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.chnu.news.presentation.search.SearchActivity

class SearchActivityResultContract : ActivityResultContract<String, String?>() {
    override fun createIntent(context: Context, input: String?): Intent {
        return Intent(context, SearchActivity::class.java).apply {
            putExtra(SEARCH_STRING, input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        val data = intent?.getStringExtra(SEARCH_VALUE)
        return if(resultCode == Activity.RESULT_OK && data != null) data else null
    }

    companion object{
        const val SEARCH_STRING = "SEARCH_STRING"
        const val SEARCH_VALUE = "SEARCH_VALUE"
    }
}