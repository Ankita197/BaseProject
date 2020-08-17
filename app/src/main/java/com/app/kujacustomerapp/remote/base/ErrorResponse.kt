package com.app.kujacustomerapp.remote.base

import java.util.*

class ErrorResponse {
    var code: String? = null
    var message: String? = null
    var errorData: HashMap<String, List<String>>? =
        null

}