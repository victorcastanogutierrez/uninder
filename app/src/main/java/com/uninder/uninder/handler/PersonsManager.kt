package com.uninder.uninder.handler

import com.uninder.uninder.model.Person

object PersonsManager {

    private var persons = listOf<Person>()

    fun getPersons():List<Person> {
        return this.persons
    }

    fun setPersons(persons:List<Person>) {
        this.persons = persons
    }
}