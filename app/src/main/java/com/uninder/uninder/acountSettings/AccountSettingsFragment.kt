package com.uninder.uninder.acountSettings

import android.os.Bundle
import android.support.v7.preference.Preference

import android.support.v7.preference.PreferenceFragmentCompat;
import com.google.firebase.auth.FirebaseAuth
import com.uninder.uninder.R

private const val EDIT_NAME = "preference_name"
private const val EDIT_DESC = "preference_desc"
private const val CLOSE_SESSION = "preference_close_session"


class AccountSettingsFragment : PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }


    override fun onPreferenceTreeClick(preference: Preference?): Boolean {

        when (preference?.key) {

            EDIT_NAME -> editNameDialog()
            EDIT_DESC -> editDescDialog()
            CLOSE_SESSION -> closeSession()
            else -> {
            }
        }

        return super.onPreferenceTreeClick(preference)
    }

    fun editNameDialog() {

    }

    fun editDescDialog() {

    }

    fun closeSession() {

        FirebaseAuth.getInstance().signOut()
    }


}