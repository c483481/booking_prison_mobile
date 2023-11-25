package com.example.booking_prison.utils

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.booking_prison.response.LoginResponse
import com.example.booking_prison.view.LoginActivity

class LoginUtils(var con: Context) {
    private var pref: SharedPreferences
    private var editor: SharedPreferences.Editor
    private val PRIVATE_MODE: Int = 0

    init {
        pref = con.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun createLoginSession(loginResponse: LoginResponse) {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(ROLE, loginResponse.tagRole)
        editor.putString(KEY_AT, "Bearer ${loginResponse.key.accessToken.token}")
        editor.putString(NAME, loginResponse.username)
        editor.commit()
    }

    fun getAccessToken(): String {
        return pref.getString(KEY_AT, "")!!
    }

    fun getUsername(): String {
        return pref.getString(NAME, "")!!
    }

    fun checkIsNotLogin() {
        if(!this.isLogin()) {
            val i = Intent(con, LoginActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            con.startActivity(i)
        }
    }

    fun logOut() {
        editor.clear()
        editor.commit()
        val i = Intent(con, LoginActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        con.startActivity(i)
    }

    private fun isLogin(): Boolean {
        return pref.getBoolean(IS_LOGIN, false)
    }

    companion object {
        const val PREF_NAME = "LOGIN_SESSION"
        const val IS_LOGIN = "isLoggedIn"
        const val ROLE = "role"
        const val KEY_AT = "uat"
        const val NAME = "name"
    }
}