package com.uninder.uninder.getMatches

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.uninder.uninder.R
import com.uninder.uninder.model.Person
import kotlinx.android.synthetic.main.card_match.view.*

class MatchListAdapter(private val personas: List<Person>, val sendMail: (String) -> Unit, val putImage: (ImageView, String) -> Unit)
    : RecyclerView.Adapter<MatchListAdapter.ViewHolder>() {

    class ViewHolder(val cardView: CardView, val sendMail: (String) -> Unit, val putImage: (ImageView, String) -> Unit)
        : RecyclerView.ViewHolder(cardView) {

        fun bindForecast(person: Person) {

            with(person) {

                itemView.matchTitle.text = name
                putImage(itemView.matchImage, "https://www.infobae.com/new-resizer/4F6iYLIjE7hXne2slDNaA3hKEAU=/600x0/filters:quality(100)/s3.amazonaws.com/arc-wordpress-client-uploads/infobae-wp/wp-content/uploads/2018/02/14180759/PSG-Real-Madrid-festejo-Cristiano-Ronaldo-1.jpg")
                itemView.matchButton.setOnClickListener { sendMail(email) }
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_match, parent, false) as CardView

        return ViewHolder(view, sendMail, putImage)
    }

    override fun getItemCount() = personas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(personas[position])
    }
}
