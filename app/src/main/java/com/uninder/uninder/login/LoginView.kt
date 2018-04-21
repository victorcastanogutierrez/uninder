package com.uninder.uninder.login

import com.google.firebase.auth.FirebaseUser

interface LoginView {

    fun showError()

    fun goToConfigUser(user: FirebaseUser)

    fun login()
}