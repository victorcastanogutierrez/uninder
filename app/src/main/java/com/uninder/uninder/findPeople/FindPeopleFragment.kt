package com.uninder.uninder.findPeople

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import com.uninder.uninder.R
import org.jetbrains.anko.toast

class FindPeopleFragment : Fragment() {

    private var listener: OnFindPeopleInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.find_people, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFindPeopleInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " debe implementar OnFirstFragmentInteractionListener")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    private fun addAnimation(view: View, animation: Animation) {
        view.animation = animation
        view.startAnimation(animation)
    }

    interface OnFindPeopleInteractionListener {
        fun onLoadPeople()
    }
}

/*

likeBtn.setOnClickListener { addAnimation(it, AnimationUtils.loadAnimation(this, R.anim.like_button)) }
dislikeBtn.setOnClickListener { addAnimation(it, AnimationUtils.loadAnimation(this, R.anim.dislike_button)) }

private fun addAnimation(view: View, animation: Animation) {
    view.animation = animation
    view.startAnimation(animation)
}
*/