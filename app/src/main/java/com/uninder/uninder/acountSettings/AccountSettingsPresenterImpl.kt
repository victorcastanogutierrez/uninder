package com.uninder.uninder.acountSettings

import android.content.Context
import android.net.Uri
import android.util.Log
import com.uninder.uninder.handler.ImageFirestoreHandler
import com.uninder.uninder.userPreferences.UserPreferences
import com.uninder.uninder.userPreferences.UserPreferencesSharedImpl


class AccountSettingsPresenterImpl(private val view: AccountSettingsView, context: Context) : AccountSettingsPresenter {

    private val imageFirestoreHandler: ImageFirestoreHandler

    private val sharedHelper: UserPreferences

    init {
        this.sharedHelper = UserPreferencesSharedImpl(context)

        imageFirestoreHandler = ImageFirestoreHandler()
    }


    override fun editNameDialog(newValue: Any?) {
        Log.v("PREFERENCES_NAME", newValue.toString())
    }

    override fun editDescDialog(newValue: Any?) {
        Log.v("PREFERENCES_DESCRIPTION", newValue.toString())
    }

    override fun changePicture() {

        this.view.startPictureIntent()

    }

    override fun closeSession(listener: AccountSettingsFragment.OnCloseSessionListener?) {

        listener?.closeSession()

    }

    override fun savePicture(contentURI: Uri) {

        this.imageFirestoreHandler.uploadFile(contentURI, {
            this.view.showSuccessOnUploadPic()
        }, {
            this.view.showErrorOnUploadPic()
        })

    }

}