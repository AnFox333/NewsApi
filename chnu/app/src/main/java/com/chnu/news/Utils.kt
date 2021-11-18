package com.chnu.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ViewModel.asyncRequest( execution: suspend CoroutineScope.() -> Unit){
    viewModelScope.launch(Dispatchers.IO) {
        //execution.invoke(1)
    }
}