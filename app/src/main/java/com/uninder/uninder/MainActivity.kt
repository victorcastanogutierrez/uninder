package com.uninder.uninder

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast


const val REQUEST_CODE_SIGN_IN = 1234

const val TAG = "Login"

const val WEB_CLIENT_ID = "339115481307-63u0qp970ugnec1hu9olkp4suirah0t9.apps.googleusercontent.com"


class MainActivity : AppCompatActivity(),  GoogleApiClient.OnConnectionFailedListener {

    private var mAuth: FirebaseAuth? = null

    private var mGoogleApiClient: GoogleApiClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(WEB_CLIENT_ID)
                .requestEmail()
                .build()

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        mAuth = FirebaseAuth.getInstance()

        //login.setOnClickListener({signIn()})
        //logout.setOnClickListener({signOut()})
    }

    override fun onStart() {
        super.onStart()

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        updateUI(currentUser)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent();
        if (requestCode == REQUEST_CODE_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)

            if (result.isSuccess) {
                // successful -> authenticate with Firebase
                val account = result.signInAccount
                firebaseAuthWithGoogle(account!!)
            } else {
                // failed -> update UI
                updateUI(null)
                toast("failed")
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.e(TAG, "firebaseAuthWithGoogle():" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth!!.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success
                        Log.e(TAG, "signInWithCredential: Success!")
                        val user = mAuth!!.currentUser
                        updateUI(user)
                    } else {
                        // Sign in fails
                        Log.w(TAG, "signInWithCredential: Failed!", task.exception)
                        toast("failed")
                        updateUI(null)
                    }
                }
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.e(TAG, "onConnectionFailed():" + connectionResult);
        toast("Fail")
    }

    private fun signIn() {
        val intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(intent, REQUEST_CODE_SIGN_IN)
    }

    public fun signOut() {
        // sign out Firebase
        mAuth!!.signOut()

        // sign out Google
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback { updateUI(null) }
    }

    private fun revokeAccess() {
        // sign out Firebase
        mAuth!!.signOut()

        // revoke access Google
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback { updateUI(null) }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            toast("Sesion inciada ${user.displayName} " )
            Log.e(TAG,user.toString());
        } else {
            toast("Sesion no inciada, puede que cerrada")
        }
    }
}
