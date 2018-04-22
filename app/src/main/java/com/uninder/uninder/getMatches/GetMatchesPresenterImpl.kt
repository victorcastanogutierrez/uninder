package com.uninder.uninder.getMatches

import com.google.firebase.auth.FirebaseAuth
import com.uninder.uninder.model.Gender
import com.uninder.uninder.model.Person

class GetMatchesPresenterImpl : GetMatchesPresenter {


    private val persons = mutableListOf<Person>(
            Person("pepe", "Soy pepe tiooo", "pepe@pepe.es", Gender.MALE, Gender.FEMALE, mapOf())
            , Person("jose", "Soy pepe tiooo", "jose@gmail.es", Gender.MALE, Gender.FEMALE, mapOf())
            , Person("pepa", "Soy pepe tiooo", "luis@gmail.es", Gender.MALE, Gender.FEMALE, mapOf())

    )

    override fun loadMatches() {
        Person.findMatches(FirebaseAuth.getInstance().currentUser!!.email, {
            
        })
    }

}