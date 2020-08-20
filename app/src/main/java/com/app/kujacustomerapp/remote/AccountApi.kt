package com.app.kujacustomerapp.remote

import com.app.kujacustomerapp.remote.base.BaseResponse
import com.app.kujacustomerapp.remote.entity.request.account.*
import com.app.kujacustomerapp.remote.entity.response.account.ForgotPasswordResponse
import com.app.kujacustomerapp.remote.entity.response.account.SecurityQuestionResponse
import com.app.kujacustomerapp.remote.entity.response.account.UserData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountApi {
    @POST("api/kujaAPI/Login")
    fun callLogin(@Body loginRequest: LoginRequest?): Call<BaseResponse<UserData?>?>?

    @POST("api/kujaAPI/CreateCustomer")
    fun callRegister(@Body signupRequest: SignUpRequest?): Call<BaseResponse<Int?>?>?

    @POST("api/kujaAPI/ForgetPassword")
    fun callForgotPassword(@Body forgotPasswordRequest: ForgotPasswordRequest?): Call<BaseResponse<ForgotPasswordResponse?>?>?

    @POST("api/kujaAPI/UpdatePassword")
    fun callChangePassword(@Body changePasswordRequest:ChangePasswordRequest?): Call<BaseResponse<ForgotPasswordResponse?>?>?
    //ForgetPassword

    @POST("api/kujaAPI/GetSecurityQuestion")
    fun callGetSecurityQuestion(@Body securityQuestionRequest: SecurityQuestionRequest?): Call<BaseResponse<SecurityQuestionResponse?>?>?
}