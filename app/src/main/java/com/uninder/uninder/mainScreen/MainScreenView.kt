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
     * Hides the indeterminate loading bar
     */
    fun hideIndeterminateLoading()

    /**
     * Inits the main screen view
     */
    fun initialize()

    /**
     * Shows the welcome message to the user before init
     */
    fun showWelcomeMessage()
}