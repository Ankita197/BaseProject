package com.app.kujacustomerapp.remote.entity.request.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForgotPasswordRequest(
    @SerializedName("LoginType")
    @Expose
    var loginType:Int?=null,
    @SerializedName("MobileNo")
    @Expose
    var mobileNo: String? = null
)