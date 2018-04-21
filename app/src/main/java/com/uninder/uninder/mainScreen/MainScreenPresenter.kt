package com.uninder.uninder.mainScreen

interface MainScreenPresenter {


    /**
     * Comprueba si el usuario ha realizado guardado sus preferencias
     */
    fun checkSetUp()

    /**
     * Saves the user account's settings
     */
    fun saveConfig(gender:Int, searchGender:Int, description:String)
}