package com.uninder.uninder.getMatches

import android.widget.ImageView

interface GetMatchesView {

    /***
     * Sends mail
     */
    fun sendMail(email: String)

    /***
     * Puts a circular image
     */
    fun putCircularImage(view: ImageView, url: String)

    /***
     *Retrieves matches loaded from firebase
     */
    fun onMatchesDataLoaded(data:MutableList<String>)

    /**
     * Shows an inderterminate loading bar
     */
    fun showIndeterminateLoading()

    /**
     * Hides the indeterminate loading bar
     */
    fun hideIndeterminateLoading()
}