package com.uninder.uninder.getMatches

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.uninder.uninder.R
import kotlinx.android.synthetic.main.card_match.view.*

class MatchListAdapter(private val persons: Map<String, String>, val sendMail: (String) -> Unit, val putImage: (ImageView, String) -> Unit)
    : RecyclerView.Adapter<MatchListAdapter.ViewHolder>() {

    class ViewHolder(val cardView: CardView, val sendMail: (String) -> Unit, val putImage: (ImageView, String) -> Unit)
        : RecyclerView.ViewHolder(cardView) {

        fun bindMatch(entry: Pair<String, String>) {


            itemView.matchTitle.text = entry.first
            putImage(itemView.matchImage, entry.second)
            itemView.matchButton.setOnClickListener {
                if (entry.first != null) {
                    sendMail(entry.second)
                }
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_match, parent, false) as CardView

        return ViewHolder(view, sendMail, putImage)
    }

    override fun getItemCount() = persons.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMatch(persons.toList().get(position))
    }
}
