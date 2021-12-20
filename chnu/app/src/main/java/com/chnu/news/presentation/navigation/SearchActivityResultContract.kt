package com.chnu.news.presentation.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.chnu.news.presentation.search.SearchActivity
import java.io.Serializable

class SearchActivityResultContract : ActivityResultContract<String, SearchBackData?>() {
    override fun createIntent(context: Context, input: String?): Intent {
        return Intent(context, SearchActivity::class.java).apply {
            putExtra(SEARCH_STRING, input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): SearchBackData? {
        val data = intent?.getSerializableExtra(SEARCH_STRING) as SearchBackData
        return if(resultCode == Activity.RESULT_OK && data != null) data else null
    }

    companion object{
        const val SEARCH_STRING = "SEARCH_STRING"
    }
}

data class SearchBackData(val titleSearch: String, val contentSearch : String, val isSortByPopularity: Boolean, val isSortByDate : Boolean ) : Serializable