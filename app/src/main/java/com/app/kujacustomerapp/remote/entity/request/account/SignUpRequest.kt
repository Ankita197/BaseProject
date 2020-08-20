package com.app.kujacustomerapp.remote.entity.request.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignUpRequest(
    @SerializedName("EmailAddress")
    @Expose
    var emailAddress: String? = null,
    @SerializedName("CustomerName")
    @Expose
    var customerName: String? = null,
    @SerializedName("CustomerDOB")
    @Expose
    var customerDOB: String? = null,

    @SerializedName("Gender")
    @Expose
    var gender: String? = null,
    @SerializedName("Address")
    @Expose
    var address: String? = null,
    @SerializedName("City")
    @Expose
    var city: String? = null,
    @SerializedName("State")
    @Expose
    var state: String? = null,
    @SerializedName("CountryID")
    @Expose
    var countryID: String? = null,
    @SerializedName("Zipcode")
    @Expose
    var zipcode: String? = null,

    @SerializedName("MobileNo")
    @Expose
    var mobileNo: String? = null,
    @SerializedName("SecurityQuestionID")
    @Expose
    var securityQuestionID: String? = null,
    @SerializedName("SecurityAnswer")
    @Expose
    var securityAnswer: String? = null,

    @SerializedName("CreatedBy")
    @Expose
    var createdBy: Int? = null,
    @SerializedName("NationalID")
    @Expose
    var nationalID: String? = null
)