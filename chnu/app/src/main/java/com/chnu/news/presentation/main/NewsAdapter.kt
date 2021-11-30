package com.chnu.news.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chnu.news.R
import com.chnu.news.loadImage
import com.chnu.news.network.response.ArticleModel

class NewsAdapter(private var newsList : List<ArticleModel>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder = NewsViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_news,parent,false))

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount() = newsList.size

    fun update(newNewsList: List<ArticleModel>){
        newsList = newNewsList
        notifyDataSetChanged()
    }



    class NewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(articleModel: ArticleModel) {
            with(itemView) {
                articleModel.urlToImage?.let { findViewById<ImageView>(R.id.newsImage).loadImage(it) }
                findViewById<TextView>(R.id.newsTitle).text = articleModel.title
                findViewById<TextView>(R.id.newsContent).text = articleModel.description
            }
        }
    }

}