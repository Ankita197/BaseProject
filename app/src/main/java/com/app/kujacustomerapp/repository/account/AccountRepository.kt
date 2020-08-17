package com.app.kujacustomerapp.repository.account

import com.app.kujacustomerapp.interfaces.Enqueue
import com.app.kujacustomerapp.remote.base.BaseResponse
import com.app.kujacustomerapp.remote.entity.request.account.ChangePasswordRequest
import com.app.kujacustomerapp.remote.entity.request.account.ForgotPasswordRequest
import com.app.kujacustomerapp.remote.entity.request.account.LoginRequest
import com.app.kujacustomerapp.remote.entity.request.account.SignUpRequest
import com.app.kujacustomerapp.remote.entity.response.account.UserData

interface AccountRepository {
    fun callLogin(loginRequest: LoginRequest?, eneque: Enqueue<Boolean?>?)
    fun callRegister(registerRequest: SignUpRequest?, eneque: Enqueue<BaseResponse<UserData?>?>?)
    fun callForgotPassword(
        forgotPasswordRequest: ForgotPasswordRequest,
        eneque: Enqueue<Boolean?>?
    )

    fun callChangePassword(
        changePasswordRequest: ChangePasswordRequest,
        eneque: Enqueue<Boolean?>?
    )
}