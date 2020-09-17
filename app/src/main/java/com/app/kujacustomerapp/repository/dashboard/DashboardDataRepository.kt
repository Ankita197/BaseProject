package com.app.kujacustomerapp.repository.dashboard

import android.util.Log
import com.app.kujacustomerapp.interfaces.Enqueue
import com.app.kujacustomerapp.persistance.AccountSharedPrefs
import com.app.kujacustomerapp.persistance.DashboardSharedPrefs
import com.app.kujacustomerapp.remote.DashboardApi
import com.app.kujacustomerapp.remote.base.BaseResponse
import com.app.kujacustomerapp.remote.base.CommonRetrofit
import com.app.kujacustomerapp.remote.entity.request.account.ReIssueRequest
import com.app.kujacustomerapp.remote.entity.response.dashboard.DashboardData
import com.app.kujacustomerapp.remote.entity.response.dashboard.TransactionData
import com.app.kujacustomerapp.remote.entity.response.rfid.DeviceData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DashboardDataRepository @Inject constructor(var  dashboardSharedPrefs: DashboardSharedPrefs) : CommonRetrofit<DashboardApi>(),
    DashboardRepository {

    override val restClass: Class<DashboardApi>
        get() {
            return DashboardApi::class.java
        }

    override fun callGetAllStudentData(page: Int?, size: Int?, eneque: Enqueue<DashboardData>?) {
        getNetworkService()?.getData(page!!, size!!)
            ?.enqueue(object : Callback<BaseResponse<DashboardData>> {
                override fun onFailure(call: Call<BaseResponse<DashboardData>>, t: Throwable) {
                    eneque?.onError(call, t)
                }

                override fun onResponse(
                    call: Call<BaseResponse<DashboardData>>,
                    response: Response<BaseResponse<DashboardData>>
                ) {
                    if (response.body() != null) eneque?.onSuccess(
                        call,
                        response.body()?.data as DashboardData
                    )
                }

            })
    }

    override fun callGetAllTransactionData(
        id: Int,
        startDate: String,
        endDate: String,
        eneque: Enqueue<ArrayList<TransactionData>>?
    ) {
        getNetworkService()?.getTransaction(id,startDate,endDate)?.enqueue(object :Callback<BaseResponse<ArrayList<TransactionData>>>
        {
            override fun onFailure(
                call: Call<BaseResponse<ArrayList<TransactionData>>>,
                t: Throwable
            ) {
                eneque?.onError(call, t)
            }

            override fun onResponse(
                call: Call<BaseResponse<ArrayList<TransactionData>>>,
                response: Response<BaseResponse<ArrayList<TransactionData>>>
            ) {
                if (response.body() != null) {
                    val gson = Gson()
                    val data:String=gson.toJson(response.body()?.data)
                    dashboardSharedPrefs.transactionData=data
                    eneque?.onSuccess(call, response.body()?.data as ArrayList<TransactionData>)

                }

            }

        })
    }

    override fun callGetDeviceData(userId: Int, eneque: Enqueue<ArrayList<DeviceData>>?) {
        getNetworkService()?.getDeviceData(userId)?.enqueue(object:Callback<BaseResponse<ArrayList<DeviceData>>>{
            override fun onFailure(call: Call<BaseResponse<ArrayList<DeviceData>>>, t: Throwable) {
                eneque?.onError(call,t)
            }

            override fun onResponse(
                call: Call<BaseResponse<ArrayList<DeviceData>>>,
                response: Response<BaseResponse<ArrayList<DeviceData>>>
            ) {
                response.body()?.data?.let { eneque?.onSuccess(call, it) }
            }
        })
    }

    override fun callReOderingRfid(reIssueRequest: ReIssueRequest, eneque: Enqueue<Boolean>?) {
        getNetworkService()?.setReoderingRfid(reIssueRequest)?.enqueue(object :Callback<BaseResponse<Int>>{
            override fun onFailure(call: Call<BaseResponse<Int>>, t: Throwable) {
                eneque?.onError(call,t)
            }

            override fun onResponse(
                call: Call<BaseResponse<Int>>,
                response: Response<BaseResponse<Int>>
            ) {
                response.body()?.status?.let { eneque?.onSuccess(call, it) }
            }

        })
    }


}