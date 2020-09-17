package com.app.kujacustomerapp.remote

import com.app.kujacustomerapp.remote.base.BaseResponse
import com.app.kujacustomerapp.remote.entity.request.account.LoginRequest
import com.app.kujacustomerapp.remote.entity.request.account.ReIssueRequest
import com.app.kujacustomerapp.remote.entity.response.dashboard.DashboardData
import com.app.kujacustomerapp.remote.entity.response.dashboard.TransactionData
import com.app.kujacustomerapp.remote.entity.response.rfid.DeviceData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DashboardApi {
    @GET("users")
    fun getData(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<BaseResponse<DashboardData>>

    @GET("api/kujaAPI/GetDeviceHistoryByUserID")
    fun getDeviceData(
        @Query("UserID") userID: Int
    ): Call<BaseResponse<ArrayList<DeviceData>>>

    @GET("api/kujaAPI/GetCustomerTransactions")
    fun getTransaction(
        @Query("ID") id: Int,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): Call<BaseResponse<ArrayList<TransactionData>>>

    @POST("api/kujaAPI/ReIssue")
    fun setReoderingRfid(@Body reIssueRequest: ReIssueRequest?): Call<BaseResponse<Int>>
}