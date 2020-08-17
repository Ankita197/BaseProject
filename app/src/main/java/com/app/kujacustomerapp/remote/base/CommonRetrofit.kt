package com.app.kujacustomerapp.remote.base

import com.app.kujacustomerapp.BuildConfig
import com.app.kujacustomerapp.remote.base.interceptor.NetworkErrorInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject

abstract class CommonRetrofit<T> : BaseRetrofit<T>() {
    @Inject
    lateinit var networkErrorIntercepter: NetworkErrorInterceptor
    override val baseUrl: String
        //To make ApiWork
              protected get() = BuildConfig.API_BASE_URL

    override fun okHttpClientHandler(builder: OkHttpClient.Builder?): OkHttpClient.Builder? {
        super.handleOkHttpBuilder(builder!!)
        builder.connectTimeout(60, TimeUnit.SECONDS)
        builder.readTimeout(60, TimeUnit.SECONDS)
        builder.writeTimeout(60, TimeUnit.SECONDS)
        builder.addInterceptor(networkErrorIntercepter)
        return super.okHttpClientHandler(builder)
    }
}