package com.uninder.uninder.getMatches

import com.google.firebase.auth.FirebaseAuth
import com.uninder.uninder.model.Person

class GetMatchesPresenterImpl(val view:GetMatchesView) : GetMatchesPresenter {

    override fun loadMatches() {
        Person.findMatches(FirebaseAuth.getInstance().currentUser!!.email, {
            view.onMatchesDataLoaded(it)
        })
    }

}