package com.uninder.uninder.acountSettings

import com.google.firebase.auth.FirebaseAuth



class AccountSettingsPresenterImpl(view : AccountSettingsView) : AccountSettingsPresenter {



    override fun editNameDialog() {
    }

    override fun editDescDialog() {
    }

    override fun changePicture() {
    }

    override fun closeSession() {
        FirebaseAuth.getInstance().signOut()
    }
}