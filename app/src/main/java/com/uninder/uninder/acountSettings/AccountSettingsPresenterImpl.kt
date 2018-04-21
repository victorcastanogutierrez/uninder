package com.uninder.uninder.acountSettings

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.uninder.uninder.handler.ImageFirestoreHandler


class AccountSettingsPresenterImpl(private val view: AccountSettingsView) : AccountSettingsPresenter {

    private lateinit var imageFirestoreHandler: ImageFirestoreHandler

    init {
        imageFirestoreHandler = ImageFirestoreHandler()
    }


    override fun editNameDialog() {
    }

    override fun editDescDialog() {
    }

    override fun changePicture() {

        this.view.startPictureIntent()

    }

    override fun closeSession() {

        FirebaseAuth.getInstance().signOut()

    }

    override fun savePicture(contentURI: Uri) {

        this.imageFirestoreHandler.uploadFile(contentURI, {
            this.view.showSuccessOnUploadPic()
        }, {
            this.view.showErrorOnUploadPic()
        })

    }

}