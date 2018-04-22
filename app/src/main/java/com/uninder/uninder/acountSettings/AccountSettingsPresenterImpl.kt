package com.uninder.uninder.acountSettings

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.uninder.uninder.model.Person
import com.uninder.uninder.userPreferences.UserPreferences
import com.uninder.uninder.userPreferences.UserPreferencesSharedImpl


class AccountSettingsPresenterImpl(private val view: AccountSettingsView, context: Context) : AccountSettingsPresenter {


    private val sharedHelper: UserPreferences

    init {
        this.sharedHelper = UserPreferencesSharedImpl(context)

    }


    override fun editNameDialog(newValue: Any?) {
        Log.v("PREFERENCES_NAME", newValue.toString())
        Person.update("name", newValue.toString())
    }

    override fun editDescDialog(newValue: Any?) {
        Log.v("PREFERENCES_DESCRIPTION", newValue.toString())
        Person.update("description", newValue.toString())

    }

    override fun changePicture() {

        this.view.startPictureIntent()

    }

    override fun closeSession(listener: AccountSettingsFragment.OnCloseSessionListener?) {

        listener?.closeSession()

    }

    override fun savePicture(contentURI: Uri) {

        Person.uploadFileImage(contentURI, FirebaseAuth.getInstance().currentUser?.email!!, {
            this.view.showSuccessOnUploadPic()
        }, {
            this.view.showErrorOnUploadPic()
        })

    }

}