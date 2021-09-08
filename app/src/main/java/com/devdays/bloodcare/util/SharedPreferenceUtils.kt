package com.devdays.bloodcare.util

import android.content.Context

class SharedPreferenceUtils(private var mContext: Context) {

    private val LOGIN_PREFERENCE_FILE = "login_preference_file"
    private val LOGIN_TOKEN = "login_token"

    private var mPreferenceEditor =
        mContext.getSharedPreferences(LOGIN_PREFERENCE_FILE, Context.MODE_PRIVATE)

    var mLoginToken: Boolean
        get() = mPreferenceEditor.getBoolean(LOGIN_TOKEN, false)
        set(value) = mPreferenceEditor.edit().putBoolean(LOGIN_TOKEN, value).apply()
}