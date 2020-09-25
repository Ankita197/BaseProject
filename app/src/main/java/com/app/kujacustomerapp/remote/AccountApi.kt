package com.app.kujacustomerapp.remote

import com.app.kujacustomerapp.remote.base.BaseResponse
import com.app.kujacustomerapp.remote.entity.request.account.*
import com.app.kujacustomerapp.remote.entity.response.account.ForgotPasswordResponse
import com.app.kujacustomerapp.remote.entity.response.account.SecurityQuestionResponse
import com.app.kujacustomerapp.remote.entity.response.account.UserData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface AccountApi {
    //https://web1.anasource.com/KUJA/api/
    @POST("api/kujaAPI/login")
    fun callLogin(@Body loginRequest: LoginRequest?): Call<BaseResponse<UserData?>?>?

//    @POST("api/kujaAPI/CreateCustomer")
//    fun callRegister(@Body signupRequest: SignUpRequest?): Call<BaseResponse<Int?>?>?

    @Multipart
    @POST("api/kujaAPI/CreateCustomer")
    fun callRegister(@Part("Customer") signupRequest: RequestBody?, @Part images:List<MultipartBody.Part> ): Call<BaseResponse<Int?>?>?
    //    Call<ResponseBody> add(
    //            @Part("name") RequestBody name,
    //            @Part("distribution") RequestBody distribution,
    //            @Part("habitat") RequestBody habitat,
    //            @Part MultipartBody.Part image);

    @POST("api/kujaAPI/ForgetPassword")
    fun callForgotPassword(@Body forgotPasswordRequest: ForgotPasswordRequest?): Call<BaseResponse<ForgotPasswordResponse?>?>?

    @POST("api/kujaAPI/UpdatePassword")
    fun callChangePassword(@Body changePasswordRequest:ChangePasswordRequest?): Call<BaseResponse<ForgotPasswordResponse?>?>?
    //ForgetPassword

    @POST("api/kujaAPI/GetSecurityQuestion")
    fun callGetSecurityQuestion(@Body securityQuestionRequest: SecurityQuestionRequest?): Call<BaseResponse<SecurityQuestionResponse?>?>?
}