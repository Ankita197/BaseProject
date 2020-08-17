package com.app.kujacustomerapp.remote.base

import com.app.kujacustomerapp.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseRetrofit<T> {
    private var networkService: T? = null
    private val gson: Gson? = null
    val okHttpClient: OkHttpClient?
        get() {
            val okHttpClientBuilder =
                handleOkHttpBuilder(OkHttpClient.Builder())
            if (BuildConfig.DEBUG) {
                okHttpClientBuilder.addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    )
                )
            }
            return okHttpClientBuilder.build()
        }

    protected fun initNetworkInterface() {
        val retrofitBuilder = Retrofit.Builder().baseUrl(baseUrl)
        val retrofit = retrofitHandler(retrofitBuilder)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttpClient)
            .build()
        networkService = retrofit.create(restClass)
    }

    fun getNetworkService(): T? {
        if (networkService == null) initNetworkInterface()
        return networkService
    }

    protected fun retrofitHandler(builder: Retrofit.Builder): Retrofit.Builder {
        return builder
    }

    protected open fun okHttpClientHandler(builder: OkHttpClient.Builder?): OkHttpClient.Builder? {
        return builder
    }

    protected fun gsonHandler(builder: GsonBuilder): GsonBuilder {
        return builder
    }

    protected fun handleOkHttpBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        return builder
    }

    protected abstract val baseUrl: String?
    protected abstract val restClass: Class<T>
}