package com.app.kujacustomerapp.repository.dashboard

import com.app.kujacustomerapp.interfaces.Enqueue
import com.app.kujacustomerapp.remote.entity.request.account.MakePaymentRequest
import com.app.kujacustomerapp.remote.entity.request.account.ReIssueRequest
import com.app.kujacustomerapp.remote.entity.request.account.SecurityQuestionRequestUpdate
import com.app.kujacustomerapp.remote.entity.response.dashboard.DashboardData
import com.app.kujacustomerapp.remote.entity.response.dashboard.MakePaymentResponse
import com.app.kujacustomerapp.remote.entity.response.dashboard.SecurityQuestionUpdateResponse
import com.app.kujacustomerapp.remote.entity.response.dashboard.TransactionData
import com.app.kujacustomerapp.remote.entity.response.rfid.DeviceData

interface DashboardRepository {
    fun callGetAllStudentData(
        page: Int? = 1,
        size: Int? = 15,
        eneque: Enqueue<DashboardData>?
    )
    fun callGetAllTransactionData(
        id: Int,
        startDate: String,
        endDate:String,
        eneque: Enqueue<ArrayList<TransactionData>>?
    )

    fun callGetDeviceData(
        userId: Int,
        eneque: Enqueue<ArrayList<DeviceData>>?
    )

    fun callReOderingRfid(
        reIssueRequest: ReIssueRequest,
        eneque: Enqueue<Boolean>?
    )

    fun makePayment(makePaymentRequest: MakePaymentRequest,enqueue: Enqueue<MakePaymentResponse>)

    fun getCustomerBalance(id: Int,enqueue: Enqueue<Float>)

    fun callUpdateSecurityQuestion(
        securityQuestionRequestUpdate: SecurityQuestionRequestUpdate,
        eneque: Enqueue<SecurityQuestionUpdateResponse?>?
    )
}