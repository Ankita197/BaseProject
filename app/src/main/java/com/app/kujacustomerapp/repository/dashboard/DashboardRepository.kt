package com.app.kujacustomerapp.repository.dashboard

import com.app.kujacustomerapp.interfaces.Enqueue
import com.app.kujacustomerapp.remote.entity.response.dashboard.DashboardData

interface DashboardRepository {
    fun callGetAllStudentData(
        page: Int? = 1,
        size: Int? = 15,
        eneque: Enqueue<DashboardData>?
    )
}