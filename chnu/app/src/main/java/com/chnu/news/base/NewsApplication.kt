package com.chnu.news.base

import android.app.Application
import com.chnu.news.network.networkModule
import com.chnu.news.presentation.main.mainViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NewsApplication)
            modules(applicationMode, networkModule, mainViewModelModule)
        }
    }

}

val applicationMode = module {

}

