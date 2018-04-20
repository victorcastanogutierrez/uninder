package com.uninder.uninder.acountSettings

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.preference.Preference

import android.support.v7.preference.PreferenceFragmentCompat;
import com.google.firebase.auth.FirebaseAuth
import com.uninder.uninder.R

private const val EDIT_NAME = "preference_name"
private const val EDIT_DESC = "preference_desc"
private const val CLOSE_SESSION = "preference_close_session"
private const val CHANGE_PHOTO = "preference_change_photo"


class AccountSettingsFragment : PreferenceFragmentCompat(), AccountSettingsView {

    private val presenter = AccountSettingsPresenterImpl(this)

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }


    override fun onPreferenceTreeClick(preference: Preference?): Boolean {

        when (preference?.key) {

            EDIT_NAME -> this.presenter.editNameDialog()
            EDIT_DESC -> this.presenter.editDescDialog()
            CLOSE_SESSION -> this.presenter.closeSession()
            CHANGE_PHOTO -> this.presenter.changePicture()
            else -> {
            }
        }

        return super.onPreferenceTreeClick(preference)
    }


}