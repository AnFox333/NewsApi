package com.chnu.news.network

import com.chnu.news.base.intercepror.LoggingInterceptor
import com.chnu.news.base.intercepror.LoggingInterceptorImpl
import com.chnu.news.headlines.HeadlinesInteractor
import com.chnu.news.headlines.HeadlinesInteractorImpl
import com.chnu.news.network.NetworkConstants.API_KEY
import com.chnu.news.network.NetworkConstants.API_KEY_QUERY
import com.chnu.news.network.NetworkConstants.COUNTRY
import com.chnu.news.network.NetworkConstants.COUNTRY_QUERY
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    //create news api instance
    single<NewsApi> { get<Retrofit>().create(NewsApi::class.java) }

    //create retrofit instance
    single { createRetrofit(gson = get(), loggingInterceptor = get()) }

    //create logger for request\response
    single<LoggingInterceptor> {
        LoggingInterceptorImpl().apply {
            level = LoggingInterceptorImpl.Level.BODY
        }
    }

    single<HeadlinesInteractor> { HeadlinesInteractorImpl(get()) }

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

    return Retrofit.Builder()
        .addConverterFactory(gson)
        .client(OkHttpClient.Builder().apply {
            connectTimeout(NetworkConstants.TIME_OUT_VALUE, TimeUnit.SECONDS)
            readTimeout(NetworkConstants.TIME_OUT_VALUE, TimeUnit.SECONDS)
            writeTimeout(NetworkConstants.TIME_OUT_VALUE, TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
            addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder().url(
                        chain.request().url().newBuilder().apply {
                            addQueryParameter(API_KEY_QUERY, API_KEY)
                        }.build()
                    ).build()
                )
            }
        }.build())
        .baseUrl(NetworkConstants.BASE_URL)
        .build()

}

object NetworkConstants {
    const val BASE_URL = "https://newsapi.org/v2/"
    const val TIME_OUT_VALUE: Long = 60L
    const val API_KEY = "e60697f7f9874f35a82708c0df1618a9"
    const val API_KEY_QUERY = "apiKey"
    const val COUNTRY_QUERY = "country"
    const val COUNTRY = "ua"
}

