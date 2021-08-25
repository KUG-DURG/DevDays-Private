package com.devdays.bloodcare.util

import android.content.Context

class SharedPreferenceUtils(private var mContext: Context) {

    private val LOGIN_PREFERENCE_FILE = "login_preference_file"
    private val FIRST_TIME_LAUNCH = "first_time_launch"
    private val LOGIN_TOKEN = "login_token"

    private var mPreferenceEditor =
        mContext.getSharedPreferences(LOGIN_PREFERENCE_FILE, Context.MODE_PRIVATE)

    var mIsAppLaunchedForFirstTime: Boolean
        get() = mPreferenceEditor.getBoolean(FIRST_TIME_LAUNCH, true)
        set(value) = mPreferenceEditor.edit().putBoolean(FIRST_TIME_LAUNCH, value).apply()

    var mLoginToken: String?
        get() = mPreferenceEditor.getString(LOGIN_TOKEN, "")
        set(value) = mPreferenceEditor.edit().putString(LOGIN_TOKEN, value).apply()
}