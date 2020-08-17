package com.app.kujacustomerapp.remote.base

import com.google.gson.annotations.Expose

class BaseResponse<T> {

    @Expose
    var error: String? = null
        private set

    @Expose
    var data: T? = null
        private set

    @Expose
    var status: Boolean? = null

    fun setData(data: T) {
        this.data = data
    }

}