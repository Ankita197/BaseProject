package com.app.kujacustomerapp.repository.account

import android.util.Log
import com.app.kujacustomerapp.interfaces.Enqueue
import com.app.kujacustomerapp.persistance.AccountSharedPrefs
import com.app.kujacustomerapp.remote.AccountApi
import com.app.kujacustomerapp.remote.base.BaseResponse
import com.app.kujacustomerapp.remote.base.CommonRetrofit
import com.app.kujacustomerapp.remote.entity.request.account.*
import com.app.kujacustomerapp.remote.entity.response.account.ForgotPasswordResponse
import com.app.kujacustomerapp.remote.entity.response.account.SecurityQuestionResponse
import com.app.kujacustomerapp.remote.entity.response.account.UserData
import com.app.kujacustomerapp.remote.entity.response.dashboard.SecurityQuestionUpdateResponse
import com.google.gson.Gson
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AccountDataRepository @Inject constructor(var  accountSharedPrefs: AccountSharedPrefs) :
    CommonRetrofit<AccountApi>(), AccountRepository {

    override val restClass: Class<AccountApi>
        get() = AccountApi::class.java

    override fun callLogin(loginRequest: LoginRequest?, eneque: Enqueue<Boolean?>?) {
                getNetworkService()?.callLogin(loginRequest)
            ?.enqueue(object : Callback<BaseResponse<UserData?>?> {
                override fun onResponse(
                    call: Call<BaseResponse<UserData?>?>,
                    response: Response<BaseResponse<UserData?>?>
                ) {
                    eneque?.onSuccess(call, response.body()?.status)
                    val gson = Gson()
                    val data:String=gson.toJson(response.body()?.data)
                    accountSharedPrefs.userData=data
                }

                override fun onFailure(
                    call: Call<BaseResponse<UserData?>?>,
                    t: Throwable
                ) {
                    eneque?.onError(call, t)
                }
            })
    }

    override fun callRegister(
        registerRequest: RequestBody?,
        images: List<MultipartBody.Part>,
        eneque: Enqueue<Boolean?>?
    ) {
        getNetworkService()?.callRegister(registerRequest, images)
            ?.enqueue(object : Callback<BaseResponse<Int?>?> {
                override fun onResponse(
                    call: Call<BaseResponse<Int?>?>,
                    response: Response<BaseResponse<Int?>?>
                ) {
                    eneque?.onSuccess(call, response.body()?.status)
                    Log.d("___@___","status is"+response.body()?.status)

                }

                override fun onFailure(
                    call: Call<BaseResponse<Int?>?>,
                    t: Throwable
                ) {
                    eneque?.onError(call, t)
                }
            })

    }





    override fun callForgotPassword(
        forgotPasswordRequest: ForgotPasswordRequest,
        eneque: Enqueue<Boolean?>?
    ) {
        getNetworkService()?.callForgotPassword(forgotPasswordRequest)?.enqueue(object :Callback<BaseResponse<ForgotPasswordResponse?>?>{
            override fun onFailure(call: Call<BaseResponse<ForgotPasswordResponse?>?>, t: Throwable) {
                eneque?.onError(call, t)
            }

            override fun onResponse(
                call: Call<BaseResponse<ForgotPasswordResponse?>?>,
                response: Response<BaseResponse<ForgotPasswordResponse?>?>
            ) {
                eneque?.onSuccess(call, response.body()?.status)
            }
        })
    }

    override fun callChangePassword(
        changePasswordRequest: ChangePasswordRequest,
        eneque: Enqueue<Boolean?>?
    ) {
        getNetworkService()?.callChangePassword(changePasswordRequest)?.enqueue(object :Callback<BaseResponse<ForgotPasswordResponse?>?>{
            override fun onFailure(
                call: Call<BaseResponse<ForgotPasswordResponse?>?>,
                t: Throwable
            ) {
                eneque?.onError(call,t)
            }

            override fun onResponse(
                call: Call<BaseResponse<ForgotPasswordResponse?>?>,
                response: Response<BaseResponse<ForgotPasswordResponse?>?>
            ) {
                eneque?.onSuccess(call, response.body()?.status)
            }

        })
    }

    override fun callGetSecurityQuestion(
        securityQuestionRequest: SecurityQuestionRequest,
        eneque: Enqueue<SecurityQuestionResponse?>?
    ) {
        getNetworkService()?.callGetSecurityQuestion(securityQuestionRequest)?.enqueue(object :Callback<BaseResponse<SecurityQuestionResponse?>?>{
            override fun onFailure(
                call: Call<BaseResponse<SecurityQuestionResponse?>?>,
                t: Throwable
            ) {
                eneque?.onError(call,t)
            }

            override fun onResponse(
                call: Call<BaseResponse<SecurityQuestionResponse?>?>,
                response: Response<BaseResponse<SecurityQuestionResponse?>?>
            ) {
                eneque?.onSuccess(call,response.body()?.data)
            }
        })
    }



//    override fun callForgotPassword(
//        forgotPasswordRequest: ForgotPasswordRequest,
//        eneque: Enqueue<BaseResponse<UserData?>?>?
//    ) {
//        getNetworkService()?.callForgotPassword(forgotPasswordRequest)
//            ?.enqueue(object : Callback<BaseResponse<UserData?>?> {
//                override fun onResponse(
//                    call: Call<BaseResponse<UserData?>?>,
//                    response: Response<BaseResponse<UserData?>?>
//                ) {
//                    eneque?.onSuccess(call, response.body())
//                }
//
//                override fun onFailure(
//                    call: Call<BaseResponse<UserData?>?>,
//                    t: Throwable
//                ) {
//                    eneque?.onError(call, t)
//                }
//            })
//    }

}