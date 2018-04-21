package com.uninder.uninder.getMatches

import com.uninder.uninder.model.Person

class GetMatchesPresenterImpl : GetMatchesPresenter {


    private val persons = mutableListOf<Person>(
            Person("pepe", "Soy pepe tiooo", "pepe@pepe.es", "pepe", "pepas")
            , Person("jose", "Soy pepe tiooo", "jose@gmail.es", "pepe", "pepas")
            , Person("pepa", "Soy pepe tiooo", "luis@gmail.es", "pepe", "pepas")

    )

    override fun getMatches(): MutableList<Person> {

        return this.persons

    }

}