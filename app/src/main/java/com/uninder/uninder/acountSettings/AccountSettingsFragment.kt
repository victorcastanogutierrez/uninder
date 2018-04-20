package com.uninder.uninder.acountSettings

import android.os.Bundle

import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.uninder.uninder.R

class AccountSettingsFragment : PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.settings_layout, container, false)
    }





}