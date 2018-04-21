package com.uninder.uninder.acountSettings

import android.net.Uri


interface AccountSettingsPresenter {

    fun editNameDialog(newValue: Any?)

    fun editDescDialog(newValue: Any?)

    fun changePicture()

    fun savePicture(contentURI: Uri)

    fun closeSession(listener: AccountSettingsFragment.OnCloseSessionListener?)
}