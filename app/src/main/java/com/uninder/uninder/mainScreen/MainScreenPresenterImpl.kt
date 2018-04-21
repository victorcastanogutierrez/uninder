package com.uninder.uninder.mainScreen

import com.uninder.uninder.userPreferences.UserPreferences
import com.uninder.uninder.userPreferences.UserPreferencesSharedImpl
import java.util.*
import android.content.Context
import com.uninder.uninder.model.Person


class MainScreenPresenterImpl (private val view:MainScreenView, private val context:Context): MainScreenPresenter {

    private val sharedHelper:UserPreferences

    init {
        this.sharedHelper = UserPreferencesSharedImpl(context)
    }

    override fun checkSetUp() {
        if (!Objects.equals(true, sharedHelper.retrieveBoolean(UserPreferencesSharedImpl.PREFS_ALREADY_SETUP))) {
            view.askForPreferences()
        } else {
            view.initialize()
        }
    }

    override fun saveConfig(gender: Int, searchGender: Int, description: String) {
        view.showIndeterminateLoading()
        Person.savePersonSettings(description, gender, searchGender)
        val userPref:UserPreferences = UserPreferencesSharedImpl(context)
        userPref.saveBooleanValue(UserPreferencesSharedImpl.PREFS_ALREADY_SETUP, true)
    }

}