package com.dsm.restaurant.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class PrefStorageImpl(
    private val context: Context
) : PrefStorage {

    companion object {
        private const val PREF_NAME = "PREF"
        private const val PREF_ACCESS_TOKEN = "PREF_ACCESS_TOKEN"
        private const val PREF_REFRESH_TOKEN = "PREF_REFRESH_TOKEN"
    }

    private val pref: SharedPreferences by lazy { context.getSharedPreferences("PREF", MODE_PRIVATE) }

    override fun setAccessToken(accessToken: String) =
        pref.edit().putString(PREF_ACCESS_TOKEN, accessToken).apply()

    override fun getAccessToken(): String =
        pref.getString(PREF_ACCESS_TOKEN, "") ?: ""

    override fun deleteAccessToken() =
        pref.edit().remove(PREF_ACCESS_TOKEN).apply()

    override fun setRefreshToken(refreshToken: String) =
        pref.edit().putString(PREF_REFRESH_TOKEN, refreshToken).apply()

    override fun getRefreshToken(): String =
        pref.getString(PREF_REFRESH_TOKEN, "") ?: ""

    override fun deleteRefreshToken() =
        pref.edit().remove(PREF_REFRESH_TOKEN).apply()


}