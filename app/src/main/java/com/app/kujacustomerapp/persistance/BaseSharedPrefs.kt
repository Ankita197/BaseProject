package com.app.kujacustomerapp.persistance

import android.content.Context
import android.content.SharedPreferences

abstract class BaseSharedPrefs constructor(context: Context) {
    private var sharedPreferences: SharedPreferences? = null
    protected abstract val preferencesGroup: String
    private fun initPreferences(
        context: Context,
        preferencesGroup: String
    ) {
        sharedPreferences = context.getSharedPreferences(preferencesGroup, 0)
    }

    fun getString(tag: String?): String? {
        return sharedPreferences!!.getString(tag, null)
    }

    fun put(key: String, data: Any?) {
        val editor = sharedPreferences!!.edit()
        if (data is String) {
            editor.putString(key, data as String?)
        } else if (data is Boolean) {
            editor.putBoolean(key, (data as Boolean?)!!)
        } else if (data is Float) {
            editor.putFloat(key, (data as Number).toFloat())
        } else if (data is Double) {
            editor.putFloat(key, (data as Number).toDouble().toFloat())
        } else if (data is Int) {
            editor.putInt(key, (data as Number).toInt())
        } else if (data is Long) {
            editor.putLong(key, (data as Number).toLong())
        }
        editor.apply()
    }

    protected fun getLong(tag: String?): Long {
        return sharedPreferences!!.getLong(tag, -1)
    }

    protected fun getBoolean(tag: String?): Boolean {
        return sharedPreferences!!.getBoolean(tag, false)
    }

    protected fun getInteger(tag: String?): Int {
        return sharedPreferences!!.getInt(tag, -1)
    }

    protected fun getFloat(tag: String?): Float {
        return sharedPreferences!!.getFloat(tag, -1f)
    }

    operator fun contains(preferenceKey: String?): Boolean {
        return sharedPreferences!!.contains(preferenceKey)
    }

    init {
        initPreferences(context, preferencesGroup)
    }
}