package com.uninder.uninder.handler

import com.uninder.uninder.model.Person

object PersonsManager {

    var loaded:Boolean = false
    var persons = mutableListOf<Person>()
}