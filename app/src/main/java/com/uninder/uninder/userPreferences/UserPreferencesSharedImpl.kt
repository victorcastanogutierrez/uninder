package com.uninder.uninder.userPreferences

import android.content.Context
import android.content.SharedPreferences


class UserPreferencesSharedImpl(private val context: Context) : UserPreferences {

    companion object {
        private const val PREFS_FILENAME = "com.uninder.userprefs"
        const val PREFS_ALREADY_SETUP = "setUp"
    }

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    override fun saveBooleanValue(key: String, value: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(key, value)
        editor.commit()
    }

    override fun retrieveBoolean(key: String):Boolean {
        return prefs.getBoolean(key, false)
    }

}