package com.app.kujacustomerapp.remote.entity.response.account

import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("emailAddress")
    var emailAddress: String? = null

    @SerializedName("createdByName")
    var createdByName: String? = null

    @SerializedName("mobileNumber")
    var mobileNumber: String? = null

    @SerializedName("countryName")
    var countryName: String? = null

    @SerializedName("userName")
    var userName: String? = null

}