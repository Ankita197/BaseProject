package com.app.kujacustomerapp.remote

import com.app.kujacustomerapp.remote.base.BaseResponse
import com.app.kujacustomerapp.remote.entity.response.dashboard.DashboardData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DashboardApi {
    @GET("users")
    fun getData(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<BaseResponse<DashboardData>>
}