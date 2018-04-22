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

    /**
     * Refresh the view to offer new user after left swipe or pressing dislike button
     */
    fun onDislike()

    /**
     * Refresh the view to offer new user after right swipe or pressing like button
     */
    fun onLike()

}