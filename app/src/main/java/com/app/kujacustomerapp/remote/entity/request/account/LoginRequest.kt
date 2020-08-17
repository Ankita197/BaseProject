package com.app.kujacustomerapp.remote.entity.request.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("MobileNo")
    @Expose
    var mobileNo: Long? = null,
    @SerializedName("Password")
    @Expose
    var password: String? = null,

    @SerializedName("LoginType")
    @Expose
    var loginType: Int? = null
)