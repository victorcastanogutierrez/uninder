package com.uninder.uninder.getMatches

import android.widget.ImageView

interface GetMatchesView {

    fun sendMail(email: String)

    fun putCircularImage(view: ImageView, url: String)

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