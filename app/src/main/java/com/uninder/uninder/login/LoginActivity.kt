package com.uninder.uninder.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.uninder.uninder.MainActivity
import com.uninder.uninder.R
import kotlinx.android.synthetic.main.card_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


const val REQUEST_CODE_SIGN_IN = 1234

const val TAG = "Login"

const val WEB_CLIENT_ID = "339115481307-63u0qp970ugnec1hu9olkp4suirah0t9.apps.googleusercontent.com"


class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener, LoginContract.View {
    
    lateinit var mAuth: FirebaseAuth

    lateinit var mGoogleApiClient: GoogleApiClient

    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        configureGoogleApi()

        mAuth = FirebaseAuth.getInstance()

        presenter = LoginPresenter(this)

        login.setOnClickListener({ login() })

        supportActionBar?.hide()
    }


    private fun configureGoogleApi() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(WEB_CLIENT_ID)
                .requestEmail()
                .build()

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            this.presenter.getGoogleLoginResult(result, mAuth)
        }
    }


    override fun showError() {
        toast("Ahora cerrariamos la activity")
    }

    override fun goToConfigUser(user : FirebaseUser) {

        startActivity<MainActivity>()
    }


    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.e(TAG, "onConnectionFailed(): ${connectionResult.errorMessage}")
        toast("Fail")
    }

    override fun login() {
        val intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(intent, REQUEST_CODE_SIGN_IN)
    }


}
