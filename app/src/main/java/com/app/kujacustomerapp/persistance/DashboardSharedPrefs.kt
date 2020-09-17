package com.app.kujacustomerapp.persistance

import android.content.Context

class DashboardSharedPrefs(context: Context): BaseSharedPrefs(context) {
    override val preferencesGroup: String
        get() = "DashboardSharedPrefs"

    var transactionData: String?
        get() = getString(PreferenceKey.PREF_TRANSACTION_DATA)
        set(transactionData) {
            put(PreferenceKey.PREF_TRANSACTION_DATA, transactionData)
        }
}