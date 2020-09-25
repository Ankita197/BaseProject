package com.app.kujacustomerapp.remote.entity.request.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SecurityQuestionRequestUpdate(
@SerializedName("MerchantID")
@Expose
var merchantID: String? = null,

@SerializedName("CustomerID")
@Expose
var customerID: String? = null ,

@SerializedName("SecurityQuestionID")
@Expose
var SecurityQuestionID: String? = null,

@SerializedName("SecurityAnswer")
@Expose
var SecurityAnswer: String? = null
)