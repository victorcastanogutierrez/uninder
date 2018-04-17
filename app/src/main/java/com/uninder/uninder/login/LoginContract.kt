package com.uninder.uninder.login

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.firebase.auth.FirebaseAuth


interface LoginContract {


    interface Presenter {


        fun getGoogleLoginResult(result: GoogleSignInResult,mAuth: FirebaseAuth)

        fun loginToFirebase()

    }


    interface View {

        fun showError()

        fun goToConfigUser()

        fun login()


    }


}