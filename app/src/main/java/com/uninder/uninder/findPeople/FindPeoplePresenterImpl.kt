package com.uninder.uninder.findPeople

import android.content.Context
import android.util.Log
import com.uninder.uninder.model.Person

class FindPeoplePresenterImpl (val context: Context?, val view:FindPeopleView) : FindPeoplePresenter {

    override fun loadData() {
        Person.findAll({ persons ->
            Log.d("asd", "Encuentra $persons.size")
        })
    }

}