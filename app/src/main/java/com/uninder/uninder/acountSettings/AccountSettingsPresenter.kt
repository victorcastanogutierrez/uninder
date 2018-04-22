package com.uninder.uninder.acountSettings

import android.net.Uri


interface AccountSettingsPresenter {

    /***
     * Gives the user the oportunity to edit his name in the app
     */
    fun editNameDialog(newValue: Any?)

    /***
     *Gives the user the oportunity to edit his descriptioj in the app
     */
    fun editDescDialog(newValue: Any?)

    /***
     *Gives the user the oportunity to picture his name in the app
     */
    fun changePicture()

    /***
     *Saves the picture to firebase storage
     */
    fun savePicture(contentURI: Uri)

    /***
     * Closes the session in FirebaseAuth
     */
    fun closeSession(listener: AccountSettingsFragment.OnCloseSessionListener?)
}