package com.uninder.uninder.getMatches

import com.uninder.uninder.model.Person

interface GetMatchesPresenter {


    fun getMatches(): MutableList<Person>


}