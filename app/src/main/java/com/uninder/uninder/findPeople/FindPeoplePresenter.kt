package com.uninder.uninder.findPeople

import android.net.Uri
import com.uninder.uninder.model.Person

interface FindPeoplePresenter {

    /**
     * Loads people data
     */
    fun loadData(onFinish: () -> Unit)

    /**
     * Retrieves the next person to like/dislike
     */
    fun getNextPerson(): Person?

    /**
     * Loads the profile picture of a Person
     */
    fun loadNextPersonImage(person: Person?, onFinish: (uri: Uri) -> Unit)

    /**
     * Handles like to a Person
     */
    fun like(person: Person?)

    /**
     * Handles dislinking  a Person
     */
    fun dislike(person: Person?)
}