package com.chnu.news.presentation.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chnu.news.R
import com.chnu.news.presentation.navigation.SearchActivityResultContract
import com.chnu.news.visibilityIf
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { NewsAdapter(listOf()) }

    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initList()
        viewModel = getViewModel()
        viewModel.listLiveData.observe(this, {
            adapter.update(it)
        })

        viewModel.loaderLiveData.observe(this, {
            findViewById<ProgressBar>(R.id.progress_bar).visibilityIf(it)
        })

        viewModel.errorLiveData.observe(this, {
            showErrorDialog(it)
        })

        findViewById<ImageView>(R.id.searchActivity).setOnClickListener { openSearchActivity.launch("Search") }
    }

    @SuppressLint("CutPasteId")
    private fun initList(){
        findViewById<RecyclerView>(R.id.newsList).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.newsList).adapter = adapter
    }

    fun showErrorDialog(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private val openSearchActivity =
        registerForActivityResult(SearchActivityResultContract()) { result ->
            when {
                result != null &&   result != null && result.titleSearch.isNotBlank() &&(result.isSortByDate || result.isSortByPopularity) -> {
                    viewModel.getSearchedSorted(result.titleSearch,result.contentSearch, if (result.isSortByDate) "publishedAt" else "popularity")
                }
                result != null && result.titleSearch.isNotBlank() && result.contentSearch.isNotBlank() -> {
                    viewModel.getSearchedNewsByTitleAndBody(
                        result.titleSearch,
                        result.contentSearch
                    )
                }
                result != null && result.titleSearch.isNotBlank() -> {
                    viewModel.getSearchedNewsByTitle(result.titleSearch)
                }
                result != null && result.contentSearch.isNotBlank() -> {
                    viewModel.getSearchedNewsByTitleAndBody(
                       null,
                        result.contentSearch
                    )
                }
                else -> {
                    viewModel.getHeadlines()
                }
         }
       }

}