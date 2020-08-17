package com.app.kujacustomerapp.remote.base.exception

import com.app.kujacustomerapp.remote.base.ErrorResponse
import java.io.IOException

open class AppHttpException(//    public static final String CODE_BAD_REQUEST = "400";
    //    public static final String CODE_NOT_FOUND = "404";
    //    public static final String CODE_UNAUTHORIZED = "401";
    //    public static final String CODE_FORBIDDEN = "403";
    //    public static final String CODE_UNPROCESSABLE = "422";
    //    public static final String CODE_SERVER_ERROR = "500";
    var errorResponse: ErrorResponse
) : IOException() {

    class AppNetWorkException(errorResponse: ErrorResponse) :
        AppHttpException(errorResponse)

    class AppApiException(errorResponse: ErrorResponse) :
        AppHttpException(errorResponse)

}