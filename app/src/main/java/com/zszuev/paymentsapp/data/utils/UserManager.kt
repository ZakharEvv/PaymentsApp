package com.zszuev.paymentsapp.data.utils

import android.app.Application
import android.content.Context

class UserManager(private val application: Application) {

    private val sharedPreferences =
        application.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN, token).apply()
        sharedPreferences.edit().putBoolean(IS_LOGGED, true).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN, null)
    }

    fun clearToken() {
        sharedPreferences.edit().remove(TOKEN).apply()
        sharedPreferences.edit().putBoolean(IS_LOGGED, false).apply()
    }

    fun isLogged() = getToken() != null

    companion object {
        private const val PREFS = "PaymentsPrefs"
        private const val TOKEN = "token"
        private const val IS_LOGGED = "isLoggedIm"
    }
}