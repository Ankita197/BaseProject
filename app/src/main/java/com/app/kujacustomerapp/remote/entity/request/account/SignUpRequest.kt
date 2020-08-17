package com.app.kujacustomerapp.remote.entity.request.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignUpRequest(
    @SerializedName("Email")
    @Expose
    var email: String? = null,
    @SerializedName("Password")
    @Expose
    var password: String? = null,
    @SerializedName("Username")
    @Expose
    var username: String? = null
)