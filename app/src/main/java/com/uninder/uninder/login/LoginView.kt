package com.uninder.uninder.login

import com.google.firebase.auth.FirebaseUser

interface LoginView {

    /***
     * Show error if logging is not correct
     */
    fun showError()

    /**
     * Moves to the config menu
     */
    fun goToConfigUser(user: FirebaseUser)

    /***
     * Tries to login
     */
    fun login()
}