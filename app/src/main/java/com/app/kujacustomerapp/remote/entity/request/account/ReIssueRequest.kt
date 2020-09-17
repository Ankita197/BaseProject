package com.app.kujacustomerapp.remote.entity.request.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReIssueRequest (

    @SerializedName("ReIssueID")
    @Expose
    var reIssueID: String? = null,

    @SerializedName("ReIssueRFIDType")
    @Expose
    var reIssueRFIDType: String? = null,

    @SerializedName("CustomerID")
    @Expose
    var customerID: String? = null,

    @SerializedName("CreatedBy")
    @Expose
    var createdBy: String? = null

)