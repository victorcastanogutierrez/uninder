package com.uninder.uninder.acountSettings

import android.net.Uri


interface AccountSettingsPresenter {

    fun editNameDialog()

    fun editDescDialog()

    fun changePicture()

    fun savePicture(contentURI: Uri)

    fun closeSession()
}