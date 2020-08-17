package com.app.kujacustomerapp.interfaces

import retrofit2.Call

interface Enqueue<T> {
//    fun onSuccess(call: Call<T>, response: T?)
//    fun onError(call: Call<T>, t: Throwable?)

    fun onSuccess(call: Call<*>, response: T)
    fun onError(call: Call<*>, t: Throwable)
}