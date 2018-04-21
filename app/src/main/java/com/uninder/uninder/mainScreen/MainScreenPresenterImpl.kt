package com.uninder.uninder.mainScreen

import com.uninder.uninder.userPreferences.UserPreferences
import com.uninder.uninder.userPreferences.UserPreferencesSharedImpl
import java.util.*
import android.content.Context

class MainScreenPresenterImpl (private val view:MainScreenView, private val context:Context): MainScreenPresenter {

    private val sharedHelper:UserPreferences

    init {
        this.sharedHelper = UserPreferencesSharedImpl(context)
    }

    override fun checkSetUp() {
        if (!Objects.equals(true, sharedHelper.retrieveBoolean(UserPreferencesSharedImpl.PREFS_ALREADY_SETUP))) {
            view.askForPreferences()
        }
    }

    override fun saveConfig(gender: Int, searchGender: Int, description: String) {

    }

}