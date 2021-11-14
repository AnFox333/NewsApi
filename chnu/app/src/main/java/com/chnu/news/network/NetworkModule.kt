package com.chnu.news.network

import com.chnu.news.base.intercepror.LoggingInterceptor
import com.chnu.news.base.intercepror.LoggingInterceptorImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    //create news api instance
    single { get<Retrofit>().create(NewsApi::class.java) }

    //create retrofit instance
    single { createRetrofit(gson = get(), loggingInterceptor = get()) }

    //create logger for request\response
    single<LoggingInterceptor> {
        LoggingInterceptorImpl().apply {
            level = LoggingInterceptorImpl.Level.BODY
        }
    }

    //create GsonConverterFactory
    single { createGson() }

}

fun createGson(): GsonConverterFactory {
    return GsonConverterFactory.create()
}

fun createRetrofit(
    gson: GsonConverterFactory,
    loggingInterceptor: LoggingInterceptor
): Retrofit {
    val okHttpClient = OkHttpClient.Builder() // TODO проверить и удалить

    return Retrofit.Builder()
        .addConverterFactory(gson)
        .client(OkHttpClient.Builder().apply {
            connectTimeout(NetworkConstants.TIME_OUT_VALUE, TimeUnit.SECONDS)
            readTimeout(NetworkConstants.TIME_OUT_VALUE, TimeUnit.SECONDS)
            writeTimeout(NetworkConstants.TIME_OUT_VALUE, TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
        }.build())
        .baseUrl(NetworkConstants.BASE_URL)
        .build()
}

object NetworkConstants {
    const val BASE_URL = ""
    const val TIME_OUT_VALUE: Long = 60L
}

