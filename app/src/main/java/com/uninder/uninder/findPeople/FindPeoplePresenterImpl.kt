package com.uninder.uninder.findPeople

import android.content.Context
import android.net.Uri
import com.uninder.uninder.handler.PersonsManager
import com.uninder.uninder.model.Person

class FindPeoplePresenterImpl (val context: Context?, val view:FindPeopleView) : FindPeoplePresenter {

    override fun loadData(onFinish: () -> Unit) {
        view.showIndeterminateLoading()
        Person.findAll({ persons ->
            PersonsManager.persons = persons
            onFinish()
        })
    }

    override fun getNextPerson(): Person? {
        return if (PersonsManager.persons.isEmpty()) null else PersonsManager.persons.removeAt(0)
    }

    override fun loadNextPersonImage(person:Person?, onFinish: (uri: Uri) -> Unit) {
        Person.findPersonImage(person, { uriLoaded:Uri ->
            onFinish(uriLoaded)
            view.hideIndeterminateLoading()
        })
    }

}