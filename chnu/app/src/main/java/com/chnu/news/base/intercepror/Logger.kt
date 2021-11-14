package com.chnu.news.base.intercepror

import android.annotation.SuppressLint
import android.util.Log


object SPLogger {

    @JvmStatic
    fun logMassage(name: String, massage: String?) {
        Log.d("SPMessage------>>>>>>>$name", massage ?: "")
    }

    @JvmStatic
    @SuppressLint("LongLogTag")
    fun logException(name: String, e: Exception) {
        Log.e("SPException------>>>>>>>", name, e)
    }

}