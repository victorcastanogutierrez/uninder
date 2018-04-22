package com.uninder.uninder.findPeople

import android.content.Context
import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.uninder.uninder.handler.PersonsManager
import com.uninder.uninder.model.Person

class FindPeoplePresenterImpl (val context: Context?, val view:FindPeopleView) : FindPeoplePresenter {


    override fun loadData(onFinish: () -> Unit) {
        view.showIndeterminateLoading()
        if (PersonsManager.loaded) {
            onFinish()
            view.hideIndeterminateLoading()
        } else {
            Person.findAll(FirebaseAuth.getInstance().currentUser!!.email, { persons ->
                PersonsManager.loaded = true
                PersonsManager.persons = persons
                onFinish()
            })
        }
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

    override fun like(person: Person?) {
        Person.doLike(person, FirebaseAuth.getInstance().currentUser!!.email,true)
    }

    override fun dislike(person: Person?) {
        Person.doLike(person,FirebaseAuth.getInstance().currentUser!!.email,false)

    }
}