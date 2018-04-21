package com.uninder.uninder.mainScreen

interface MainScreenView {

    /**
     * Asks to the user for their preferences
     */
    fun askForPreferences()

    /**
     * Shows an inderterminate loading bar
     */
    fun showIndeterminateLoading()

    /**
     * Inits the main screen view
     */
    fun initialize()
}