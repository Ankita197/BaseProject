package com.app.kujacustomerapp.remote.entity.response.rfid

import com.google.gson.annotations.SerializedName

class DeviceData {

    @SerializedName("rfidDeviceType")
    var rfidDeviceType: Int? = null

    @SerializedName("rfidNumber")
    var rfidNumber: String? = null

    @SerializedName("status")
    var status: Int? = null
}