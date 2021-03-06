package com.uninder.uninder.userPreferences


interface UserPreferences {

    /**
     * Obtains a value from the user preferences
     */
    fun retrieveBoolean(key:String): Boolean

    /**
     * Saves a value to the user preferences
     */
    fun saveBooleanValue(key:String, value:Boolean)

    /**
     * Obtains a string from the user preferences
     * Used in preferences fragment
     *
     */
    fun retrieveString(key:String):String
}