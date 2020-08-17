package com.app.kujacustomerapp.remote.base.interceptor

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.app.kujacustomerapp.BuildConfig
import com.app.kujacustomerapp.remote.base.ErrorResponse
import com.app.kujacustomerapp.remote.base.exception.AppHttpException
import com.app.kujacustomerapp.remote.base.exception.AppHttpException.AppNetWorkException
import com.app.kujacustomerapp.utility.NetworkUtils
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException

class NetworkErrorInterceptor(
    private val context: Context,
    private val noNetworkMessage: String,
    private val gson: Gson
) : Interceptor {
    private fun createPezoremException(
        code: String,
        errorBody: ErrorResponse
    ): AppHttpException {
        return AppHttpException(errorBody)
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtils.hasNetwork(context)) {
            val errorBody = ErrorResponse()
            errorBody.message = noNetworkMessage
            throw AppNetWorkException(errorBody)
        }
        val request = chain.request()
        val response = chain.proceed(request)
        return if (response.isSuccessful) {
            response
        } else {
            val body = response.body
            var finalBodyString = ""
            if (body != null) {
                finalBodyString = body.string()
                val errorBody = ErrorResponse()
                errorBody.code = response.code.toString()
                if (TextUtils.isEmpty(response.message)) errorBody.message =
                    UNKNOWN_ERROR else errorBody.message = response.message
                if (BuildConfig.DEBUG) {
                    Log.e("OkHttp: ", gson.toJson(errorBody))
                }
                throw createPezoremException(response.code.toString(), errorBody)
            }
            response.newBuilder()
                .body(
                    ResponseBody.create(
                        ("application/json").toMediaTypeOrNull(),
                        finalBodyString
                    )
                )
                .build()
        }
    }

    companion object {
        private const val UNKNOWN_ERROR = "UNKNOWN_ERROR"
    }

}