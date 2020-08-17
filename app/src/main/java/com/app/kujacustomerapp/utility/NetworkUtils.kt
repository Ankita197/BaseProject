package com.app.kujacustomerapp.utility

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {
    fun hasNetwork(context: Context): Boolean {
        val cm = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm != null) {
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnected
        }
        return false
    }
}