package com.evgeny.testdigitalnomads.di

import com.evgeny.testdigitalnomads.App
import com.evgeny.testdigitalnomads.BASE_URL
import com.evgeny.testdigitalnomads.repository.network.MainApi
import com.evgeny.testdigitalnomads.repository.network.MainApiClient
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.evgeny.testdigitalnomads.repository.network.MainApiHelper
import java.util.concurrent.TimeUnit


val NetworkModule = module {

    // OkHttp
    single {
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(ChuckInterceptor(App.appCtx))
            .build()
    }

    // Retrofit
    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    // MainApi
    single { get<Retrofit>().create(MainApi::class.java) }

    // MainApiClient
    single { MainApiClient(get<MainApiHelper>()) }

    // MainApiHelper
    single { MainApiHelper(get()) }

}