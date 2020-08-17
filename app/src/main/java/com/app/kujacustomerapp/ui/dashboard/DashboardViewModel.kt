package com.app.kujacustomerapp.ui.dashboard

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.kujacustomerapp.interfaces.Enqueue
import com.app.kujacustomerapp.remote.base.exception.AppHttpException
import com.app.kujacustomerapp.remote.entity.response.dashboard.DashboardData
import com.app.kujacustomerapp.repository.dashboard.DashboardRepository
import com.app.kujacustomerapp.ui.base.event.Event
import retrofit2.Call
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    application: Application,
    private val dashboardRepository: DashboardRepository
) : DashboardVariableViewModel(application) {

    var successLiveData: MutableLiveData<Event<DashboardData?>> =
        MutableLiveData()

    var errorLiveData = MutableLiveData<Event<String?>>()

    var pageNumber = 0

    fun getAllStudent() {
        if (pageNumber > 1) {
            loadMore = true
        } else {
            loadMore = false
            showProgress = true
        }
        dashboardRepository.callGetAllStudentData(pageNumber, 20, object :
            Enqueue<DashboardData> {
            override fun onSuccess(
                call: Call<*>,
                response: DashboardData
            ) {
                pageNumber += response.users?.size!!
                showProgress = false
                loadMore = false
                successLiveData.postValue(Event(response))
            }

            override fun onError(
                call: Call<*>,
                t: Throwable
            ) {
                loadMore = false
                showProgress = false
                if (t is AppHttpException) {
                    errorLiveData.postValue(
                        Event(
                            t.errorResponse.message
                        )
                    )
                } else {
                    errorLiveData.postValue(
                        Event(
                            t!!.localizedMessage
                        )
                    )
                }
            }

        })
    }



}