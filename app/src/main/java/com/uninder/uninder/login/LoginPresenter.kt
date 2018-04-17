package com.uninder.uninder.login

import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import org.jetbrains.anko.toast


class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {


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




    override fun loginToFirebase() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}