package com.app.kujacustomerapp.remote

import com.app.kujacustomerapp.remote.base.BaseResponse
import com.app.kujacustomerapp.remote.entity.request.account.ChangePasswordRequest
import com.app.kujacustomerapp.remote.entity.request.account.ForgotPasswordRequest
import com.app.kujacustomerapp.remote.entity.request.account.LoginRequest
import com.app.kujacustomerapp.remote.entity.request.account.SignUpRequest
import com.app.kujacustomerapp.remote.entity.response.account.ForgotPasswordResponse
import com.app.kujacustomerapp.remote.entity.response.account.UserData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountApi {
    @POST("api/kujaAPI/Login")
    fun callLogin(@Body loginRequest: LoginRequest?): Call<BaseResponse<UserData?>?>?

    @POST("api/v1/register")
    fun callRegister(@Body signupRequest: SignUpRequest?): Call<BaseResponse<UserData?>?>?

    @POST("api/kujaAPI/ForgetPassword")
    fun callForgotPassword(@Body forgotPasswordRequest: ForgotPasswordRequest?): Call<BaseResponse<ForgotPasswordResponse?>?>?

    @POST("api/kujaAPI/UpdatePassword")
    fun callChangePassword(@Body changePasswordRequest:ChangePasswordRequest?): Call<BaseResponse<ForgotPasswordResponse?>?>?
    //ForgetPassword
}