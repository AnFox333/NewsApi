package com.chnu.news

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ImageView.loadImage(url: String){
    Glide.with(this).load(url).centerCrop().into(this)
}

fun View.visibilityIf(isVisible : Boolean){
    this.visibility =  if(isVisible){
        View.GONE
    } else {
        View.VISIBLE
    }
}