package com.app.kujacustomerapp.repository.dashboard

import com.app.kujacustomerapp.interfaces.Enqueue
import com.app.kujacustomerapp.remote.DashboardApi
import com.app.kujacustomerapp.remote.base.BaseResponse
import com.app.kujacustomerapp.remote.base.CommonRetrofit
import com.app.kujacustomerapp.remote.entity.response.dashboard.DashboardData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DashboardDataRepository @Inject constructor() : CommonRetrofit<DashboardApi>(),
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
}