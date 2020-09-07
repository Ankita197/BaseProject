package com.app.kujacustomerapp.remote

import com.app.kujacustomerapp.remote.base.BaseResponse
import com.app.kujacustomerapp.remote.entity.response.dashboard.DashboardData
import com.app.kujacustomerapp.remote.entity.response.dashboard.TransactionData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DashboardApi {
    @GET("users")
    fun getData(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<BaseResponse<DashboardData>>

    @GET("api/kujaAPI/GetCustomerTransactions")
    fun getTransaction(
        @Query("ID") id: Int,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): Call<BaseResponse<ArrayList<TransactionData>>>
}