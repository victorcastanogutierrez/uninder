package com.uninder.uninder.login

import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

interface LoginPresenter {

    /***
     * Get the result of the login to google intent
     */
    fun getGoogleLoginResult(result: GoogleSignInResult, mAuth: FirebaseAuth)

    /***
     * Checks if the user had logged before
     */
    fun haveLoggedBefore(mAuth: FirebaseAuth)

}