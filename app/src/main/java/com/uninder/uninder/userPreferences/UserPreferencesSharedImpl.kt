package com.uninder.uninder.userPreferences

import android.content.Context
import android.content.SharedPreferences


class UserPreferencesSharedImpl(private val context: Context) : UserPreferences {


    companion object {
        const val PREFS_FILENAME = "com.uninder.userprefs"
        const val PREFS_ALREADY_SETUP = "setUp"
        const val EDIT_NAME = "preference_name"
        const val EDIT_DESC = "preference_desc"
    }

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    override fun saveBooleanValue(key: String, value: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(key, value)
    }

    override fun retrieveBoolean(key: String): Boolean {
        return prefs.getBoolean(key, false)
    }

    override fun retrieveString(key: String): String {
        return prefs.getString(key, "")
    }

}