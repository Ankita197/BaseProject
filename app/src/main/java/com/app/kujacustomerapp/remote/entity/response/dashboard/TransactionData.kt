package com.app.kujacustomerapp.remote.entity.response.dashboard

import com.app.kujacustomerapp.remote.entity.response.account.User
import com.google.gson.annotations.SerializedName

class TransactionData {

    @SerializedName("customerTrxID")
    var customerTrxID: Int? = null

    @SerializedName("trxDate")
    var trxDate: String? = null

    @SerializedName("businessName")
    var businessName: String? = null

    @SerializedName("amount")
    var amount: Float? = null

    //merchantID
    @SerializedName("merchantID")
    var merchantID: Int? = null

    @SerializedName("status")
    var status: Int? = null

    @SerializedName("senderPhoneNumber")
    var senderPhoneNumber: String? = null

    @SerializedName("receiverPhoneNumber")
    var receiverPhoneNumber: String? = null

    @SerializedName("createdAt")
    var createdAt: String? = null
}