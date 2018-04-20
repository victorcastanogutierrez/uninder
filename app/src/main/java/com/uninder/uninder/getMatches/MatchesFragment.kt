package com.uninder.uninder.getMatches

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uninder.uninder.R
import com.uninder.uninder.model.Person
import kotlinx.android.synthetic.main.get_matches.*
import android.content.Intent
import android.net.Uri


class MatchesFragment : Fragment() {


    private val persons = mutableListOf<Person>(
            Person("pepe", "Soy pepe tiooo", "pepe@pepe.es", "pepe", "pepas")
            , Person("jose", "Soy pepe tiooo", "pepe@pepe.es", "pepe", "pepas")
            , Person("pepa", "Soy pepe tiooo", "pepe@pepe.es", "pepe", "pepas")

    )


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.get_matches, container, false)
    }

    override fun onStart() {
        super.onStart()
        intialize()
    }


    private fun intialize() {

        matches_list.layoutManager = LinearLayoutManager(this.activity)
        matches_list.adapter = MatchListAdapter(persons, {view,email->sendMail(view,email) })

    }

    private fun sendMail(view: View, email: String) {

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, mutableListOf<String>(email).toTypedArray())
        intent.putExtra(Intent.EXTRA_SUBJECT, "uninder")
        startActivity(intent)


    }

}