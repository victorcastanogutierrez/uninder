package com.uninder.uninder.findPeople


import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
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
import com.uninder.uninder.model.Person
import kotlinx.android.synthetic.main.card_person.*
import org.jetbrains.anko.support.v4.indeterminateProgressDialog

private const val FIND_PEOPLE_FRAGMENT = "FIND_PEOPLE_FRAGMENT"

class FindPeopleFragment : Fragment(), FindPeopleView {

    lateinit var presenterImpl: FindPeoplePresenter

    private var currentPerson: Person? = null

    private lateinit var indeterminateDialog: Dialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenterImpl = FindPeoplePresenterImpl(this.context, this)
        return inflater.inflate(R.layout.find_people, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenterImpl.loadData({ onDataLoaded() })
    }

    private fun initialize(uri: Uri, person:Person?) {
        addImage(uri.toString())
        addData(person)
        currentPerson = person
        setUpButtons()

    }

    override fun onDataLoaded() {

        val person: Person? = presenterImpl.getNextPerson()
        Log.d("Busca", "Busca el siguiente $person")
        presenterImpl.loadNextPersonImage(person, { uri:Uri ->
            initialize(uri, person)
        })
    }

    private fun setUpButtons() {
        likeBtn.setOnClickListener {
            addAnimation(it, AnimationUtils.loadAnimation(this.activity, R.anim.like_button))
            presenterImpl.like(currentPerson)
        }
        dislikeBtn.setOnClickListener {
            addAnimation(it, AnimationUtils.loadAnimation(this.activity, R.anim.dislike_button))
            presenterImpl.dislike(currentPerson)
            this.onDataLoaded()
        }
    }

    private fun addData(person:Person?){
        personName.text = person?.name
        personDescription.text = person?.description
    }

    private fun addImage(imageUrl: String) {
        Glide.with(this.context!!)
            .load(imageUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(personImage)
    }
    

    private fun addAnimation(view: View, animation: Animation) {
        view.animation = animation
        view.startAnimation(animation)
    }

    override fun showIndeterminateLoading() {
        this.indeterminateDialog = indeterminateProgressDialog(getString(R.string.loadingContent))
        this.indeterminateDialog.show()
    }

    override fun hideIndeterminateLoading() {
        indeterminateDialog.dismiss()
    }
}

