package com.uninder.uninder.findPeople

interface FindPeopleView {

    /**
     * Shows an inderterminate loading bar
     */
    fun showIndeterminateLoading()

    /**
     * Hides the indeterminate loading bar
     */
    fun hideIndeterminateLoading()

    /**
     * Callback called when data is loaded
     */
    fun onDataLoaded()
}