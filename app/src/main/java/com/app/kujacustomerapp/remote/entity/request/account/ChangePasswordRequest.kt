package com.app.kujacustomerapp.remote.entity.request.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("MerchantID")
    @Expose
    var merchantID: Int? = null,
    @SerializedName("CustomerID")
    @Expose
    var customerID: Int? = null,
    @SerializedName("Password")
    @Expose
    var password: String? = null,
    @SerializedName("NewPassword")
    @Expose
    var newPassword: String? = null
)