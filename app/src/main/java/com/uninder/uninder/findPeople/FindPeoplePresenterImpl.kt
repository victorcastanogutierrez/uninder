package com.uninder.uninder.findPeople

import android.content.Context
import com.uninder.uninder.handler.PersonsManager
import com.uninder.uninder.model.Person

class FindPeoplePresenterImpl (val context: Context?, val view:FindPeopleView) : FindPeoplePresenter {

    override fun loadData() {
        view.showIndeterminateLoading()
        Person.findAll({ persons ->
            PersonsManager.setPersons(persons)
            view.hideIndeterminateLoading()
        })
    }

}