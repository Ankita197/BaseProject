package com.app.kujacustomerapp.remote.entity.response.dashboard

import com.google.gson.annotations.SerializedName

class MakePaymentResponse {

    @SerializedName("customerTrxID")
    var customerTrxID: Int? = null

    @SerializedName("amount")
    var amount: Float? = null

    @SerializedName("receiverPhoneNumber")
    var receiverPhoneNumber: String? = null

    @SerializedName("status")
    var status: Int? = null
}