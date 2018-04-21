package com.uninder.uninder.login

import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

interface LoginPresenter {

    fun getGoogleLoginResult(result: GoogleSignInResult, mAuth: FirebaseAuth)

    fun haveLoggedBefore(mAuth: FirebaseAuth)

}