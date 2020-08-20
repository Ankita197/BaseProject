package com.app.kujacustomerapp.remote.entity.response.account

import com.google.gson.annotations.SerializedName

class SecurityQuestionResponse {

    @SerializedName("securityQuestionID")
    var securityQuestionID: Int? = null

    @SerializedName("securityQuestion")
    var securityQuestion: String? = null

    @SerializedName("securityAnswer")
    var securityAnswer: String? = null

    @SerializedName("merchantID")
    var merchantID: Int? = null

    @SerializedName("customerID")
    var customerID: Int? = null
}