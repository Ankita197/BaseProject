package com.app.kujacustomerapp.persistance

import android.content.Context

class AccountSharedPrefs(context: Context?) : BaseSharedPrefs(context!!) {

    var userData: String?
        get() = getString(PreferenceKey.PREF_USER_DATA)
        set(userData) {
            put(PreferenceKey.PREF_USER_DATA, userData)
        }

    var isUserLoggedIn: Boolean
        get() = getBoolean(PreferenceKey.PREF_IS_LOGIN)
        set(isLoggedIn) {
            put(PreferenceKey.PREF_IS_LOGIN, isLoggedIn)
        }

    override val preferencesGroup: String
        get() = "AccountSharedPrefs"


}