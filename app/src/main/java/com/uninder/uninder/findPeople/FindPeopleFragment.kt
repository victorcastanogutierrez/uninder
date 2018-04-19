package com.uninder.uninder.findPeople

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.uninder.uninder.R
import kotlinx.android.synthetic.main.card_person.*
import org.jetbrains.anko.toast

private const val FIND_PEOPLE_FRAGMENT = "FIND_PEOPLE_FRAGMENT"

class FindPeopleFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.find_people, container, false)
    }

    override fun onStart() {
        super.onStart()
        initialize()
    }

    private fun initialize() {
        likeBtn.setOnClickListener { addAnimation(it, AnimationUtils.loadAnimation(this.activity, R.anim.like_button)) }
        dislikeBtn.setOnClickListener { addAnimation(it, AnimationUtils.loadAnimation(this.activity, R.anim.dislike_button)) }
    }

    private fun addAnimation(view: View, animation: Animation) {
        Log.v(FIND_PEOPLE_FRAGMENT, " Estoy pasando por aqui")
        view.animation = animation
        view.startAnimation(animation)
    }

}

