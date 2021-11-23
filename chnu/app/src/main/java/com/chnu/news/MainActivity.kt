package com.chnu.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

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
    }

    private fun initList(){
        findViewById<RecyclerView>(R.id.newsList).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.newsList).adapter = adapter
    }

    fun showErrorDialog(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        //todo add custom dialog
    }

}