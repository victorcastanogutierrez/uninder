package com.uninder.uninder.getMatches

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uninder.uninder.R
import com.uninder.uninder.model.Person
import kotlinx.android.synthetic.main.card_match.view.*


class MatchListAdapter(private val personas: List<Person>, val sendMail: (String) -> Unit)
    : RecyclerView.Adapter<MatchListAdapter.ViewHolder>() {

    class ViewHolder(val cardView: CardView, val sendMail: (String) -> Unit)
        : RecyclerView.ViewHolder(cardView) {

        fun bindForecast(person: Person) {

            with(person) {

                itemView.matchTitle.text = name
                itemView.matchDescription.text = description
                itemView.matchButton.setOnClickListener { sendMail(email) }
            }
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_match, parent, false) as CardView

        return ViewHolder(view, sendMail)
    }

    override fun getItemCount() = personas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(personas[position])
    }
}
