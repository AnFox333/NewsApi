package com.chnu.news

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String){
    Glide.with(this).load(url).centerCrop().into(this)
}

fun View.visibilityIf(isVisible : Boolean){
    this.visibility =  if(isVisible){
        View.VISIBLE
    } else {
        View.GONE
    }
}