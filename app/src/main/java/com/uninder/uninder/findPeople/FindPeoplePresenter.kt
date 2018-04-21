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
     * Loads the proffile picture of a Person
     */
    fun loadNextPersonImage(person:Person?, onFinish: (uri: Uri) -> Unit)
}