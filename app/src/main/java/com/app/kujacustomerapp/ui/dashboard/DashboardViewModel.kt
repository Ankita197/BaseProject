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



}