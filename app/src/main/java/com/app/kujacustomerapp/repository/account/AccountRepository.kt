package com.app.kujacustomerapp.repository.account

import com.app.kujacustomerapp.interfaces.Enqueue
import com.app.kujacustomerapp.remote.entity.request.account.*
import com.app.kujacustomerapp.remote.entity.response.account.SecurityQuestionResponse
import com.app.kujacustomerapp.remote.entity.response.dashboard.SecurityQuestionUpdateResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AccountRepository {
    fun callLogin(loginRequest: LoginRequest?, eneque: Enqueue<Boolean?>?)
    fun callRegister(
        registerRequest: RequestBody?,
        images: List<MultipartBody.Part>, eneque: Enqueue<Boolean?>?)
    fun callForgotPassword(
        forgotPasswordRequest: ForgotPasswordRequest,
        eneque: Enqueue<Boolean?>?
    )

    fun callChangePassword(
        changePasswordRequest: ChangePasswordRequest,
        eneque: Enqueue<Boolean?>?
    )

    fun callGetSecurityQuestion(
        changePasswordRequest: SecurityQuestionRequest,
        eneque: Enqueue<SecurityQuestionResponse?>?
    )


}