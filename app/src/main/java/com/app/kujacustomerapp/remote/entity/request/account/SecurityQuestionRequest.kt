package com.app.kujacustomerapp.remote.entity.request.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SecurityQuestionRequest (

    @SerializedName("MerchantID")
    @Expose
    var merchantID: Int? = null,

    @SerializedName("CustomerID")
    @Expose
    var customerID: Int? = null
)