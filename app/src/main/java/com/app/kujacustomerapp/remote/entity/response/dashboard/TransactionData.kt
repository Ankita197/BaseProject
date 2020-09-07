package com.app.kujacustomerapp.remote.entity.response.dashboard

import com.app.kujacustomerapp.remote.entity.response.account.User
import com.google.gson.annotations.SerializedName

class TransactionData {

    @SerializedName("trxDate")
    var trxDate: String? = null

    @SerializedName("businessName")
    var businessName: String? = null

    @SerializedName("amount")
    var amount: Float? = null
}