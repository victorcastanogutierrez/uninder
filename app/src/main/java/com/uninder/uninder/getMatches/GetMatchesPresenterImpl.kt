package com.uninder.uninder.getMatches

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.uninder.uninder.model.Person

class GetMatchesPresenterImpl(val view:GetMatchesView) : GetMatchesPresenter {

    override fun loadMatches() {
        view.showIndeterminateLoading()
        Person.findMatches(FirebaseAuth.getInstance().currentUser!!.email, {
            view.onMatchesDataLoaded(it)
        })
    }

    override fun loadMatchesImages(dataList:MutableList<String>,
                                   onFinish: (data: MutableMap<String, String>) -> Unit) {

        if (dataList.isEmpty()) {
            view.hideIndeterminateLoading()
            onFinish(mutableMapOf())
        } else {
            val output = mutableMapOf<String, String>()
            val matchesCount = dataList.count()

            dataList.forEach({
                Person.findPersonImage(it, { uri:Uri ->
                    output[it] = uri.toString()
                    if (output.count() == matchesCount) {
                    }
                })
            })
        }
    }

}