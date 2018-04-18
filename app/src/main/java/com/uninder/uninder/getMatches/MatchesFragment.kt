package com.uninder.uninder.getMatches

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uninder.uninder.R
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.toast

class MatchesFragment : Fragment() {

    private var listener: MatchesFragment.OnGetMatchesInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.get_matches, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MatchesFragment.OnGetMatchesInteractionListener) {
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


    interface OnGetMatchesInteractionListener {
        fun onLoadMatches()
    }
}