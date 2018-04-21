package com.uninder.uninder.getMatches

import com.uninder.uninder.model.Gender
import com.uninder.uninder.model.Person

class GetMatchesPresenterImpl : GetMatchesPresenter {


    private val persons = mutableListOf<Person>(
            Person("pepe", "Soy pepe tiooo", "pepe@pepe.es", Gender.MALE, Gender.FEMALE)
            , Person("jose", "Soy pepe tiooo", "jose@gmail.es", Gender.MALE, Gender.FEMALE)
            , Person("pepa", "Soy pepe tiooo", "luis@gmail.es", Gender.MALE, Gender.FEMALE)

    )

    override fun getMatches(): MutableList<Person> {

        return this.persons

    }

}