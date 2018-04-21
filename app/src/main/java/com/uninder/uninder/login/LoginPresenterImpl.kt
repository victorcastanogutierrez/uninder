package com.uninder.uninder.login

import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class LoginPresenterImpl(val view: LoginView) : LoginPresenter{


    override fun haveLoggedBefore(mAuth: FirebaseAuth) {

        val user = mAuth.currentUser
        if (user != null) {
            Log.v("damn",user.toString())
            this.view.goToConfigUser(user)
        }

    }


    override fun getGoogleLoginResult(result: GoogleSignInResult, mAuth: FirebaseAuth) {

        if (result.isSuccess) {
            val account = result.signInAccount
            firebaseAuthWithGoogle(account!!, mAuth)
        } else {

            this.view.showError()
        }


    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount, mAuth: FirebaseAuth) {
        Log.e(TAG, "firebaseAuthWithGoogle():" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.e(TAG, "signInWithCredential: Success!")
                        val user = mAuth.currentUser
                        this.view.goToConfigUser(user!!)
                    } else {
                        // Sign in fails
                        Log.w(TAG, "signInWithCredential: Failed!", task.exception)
                        this.view.showError()
                    }
                }
    }


}