package com.uninder.uninder.findPeople


import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.uninder.uninder.R
import kotlinx.android.synthetic.main.card_person.*
import org.jetbrains.anko.support.v4.indeterminateProgressDialog

private const val FIND_PEOPLE_FRAGMENT = "FIND_PEOPLE_FRAGMENT"

class FindPeopleFragment : Fragment(), FindPeopleView {

    lateinit var presenterImpl: FindPeoplePresenter

    private lateinit var indeterminateDialog: Dialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenterImpl = FindPeoplePresenterImpl(this.context, this)
        return inflater.inflate(R.layout.find_people, container, false)
    }

    override fun onStart() {
        super.onStart()
        initialize()
    }

    private fun initialize() {
        likeBtn.setOnClickListener { addAnimation(it, AnimationUtils.loadAnimation(this.activity, R.anim.like_button)) }
        dislikeBtn.setOnClickListener { addAnimation(it, AnimationUtils.loadAnimation(this.activity, R.anim.dislike_button)) }
        //addImage("https://firebasestorage.googleapis.com/v0/b/uninder-b943e.appspot.com/o/naferal14%40gmail.com%2FprofilePic?alt=media&token=5b54f2a9-eb14-40de-bfe2-78b81393bee6")
        presenterImpl.loadData()
    }


    private fun addImage(imageUrl: String) {
        Glide.with(this.context!!).load(imageUrl).apply(RequestOptions.circleCropTransform()).into(personImage)
    }

    private fun addAnimation(view: View, animation: Animation) {
        view.animation = animation
        view.startAnimation(animation)
    }

    override fun showIndeterminateLoading() {
        this.indeterminateDialog = indeterminateProgressDialog(getString(R.string.savingConfiguration))
        this.indeterminateDialog.show()
    }

    override fun hideIndeterminateLoading() {
        indeterminateDialog.dismiss()
    }
}

