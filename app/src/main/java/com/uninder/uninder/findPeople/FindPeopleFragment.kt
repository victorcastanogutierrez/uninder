package com.uninder.uninder.findPeople


import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.uninder.uninder.R
import com.uninder.uninder.model.Person
import kotlinx.android.synthetic.main.card_person.*
import kotlinx.android.synthetic.main.find_people.*
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.indeterminateProgressDialog
import org.jetbrains.anko.yesButton
import android.view.MotionEvent
import com.uninder.uninder.handler.NotificationHelper
import android.provider.Settings


class FindPeopleFragment : Fragment(), FindPeopleView {

    lateinit var presenterImpl: FindPeoplePresenter

    private var currentPerson: Person? = null

    private lateinit var indeterminateDialog: Dialog

    private lateinit var mNotificationHelper: NotificationHelper

    private var x1 = 0

    private var x2 = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenterImpl = FindPeoplePresenterImpl(this.context, this)
        return inflater.inflate(R.layout.find_people, container, false)
    }

    override fun onStart() {
        super.onStart()
        cardInfoPerson.visibility = View.INVISIBLE
        presenterImpl.loadData({ onDataLoaded() })
    }

    private fun initialize(uri: Uri, person: Person?) {
        mNotificationHelper = NotificationHelper(this.context!!)
        addImage(uri.toString())
        addData(person)
        currentPerson = person
        setUpButtons()
        setUpSwipe()
    }

    private fun setUpSwipe() {

        cardInfoPerson.setOnTouchListener(View.OnTouchListener({ view, event ->
            addSwipe(view, event)
        }))
    }

    private fun addSwipe(view: View?, event: MotionEvent?): Boolean {

        when (event?.getAction()) {
            MotionEvent.ACTION_DOWN -> x1 = event.x.toInt()
            MotionEvent.ACTION_UP -> {
                x2 = event.x.toInt()
                val deltaX = x2 - x1

                if (deltaX < 0) {
                    onDislike()

                }
                if (deltaX > 0) {
                    onLike()
                }
            }
        }
        return true
    }


    override fun onDataLoaded() {

        val person: Person? = presenterImpl.getNextPerson()
        cardInfoPerson.visibility = View.VISIBLE
        if (null == person) {
            noMoreResults()
            cardInfoPerson.setOnTouchListener(null)
            alert(getString(R.string.noMorePeople)) {
                title = getString(R.string.noMorePeopleTitle)
                yesButton { }
            }.show()
            hideIndeterminateLoading()
        } else {
            presenterImpl.loadNextPersonImage(person, { uri: Uri ->
                initialize(uri, person)
            })
        }
    }

    private fun noMoreResults() {

        likeBtn.visibility = View.INVISIBLE
        dislikeBtn.visibility = View.INVISIBLE
        personDescription.visibility = View.INVISIBLE
        personName.visibility = View.INVISIBLE
        Glide.with(this.context!!)
                .load(R.drawable.later)
                .into(personImage)


    }

    private fun setUpButtons() {
        likeBtn.setOnClickListener {
            onLike()
        }
        dislikeBtn.setOnClickListener {
            onDislike()
        }
    }

    private fun addData(person: Person?) {
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

    override fun onLike() {

        mNotificationHelper.createNotification("MATCH con JC")


        /**
        addAnimation(likeBtn, AnimationUtils.loadAnimation(this.activity, R.anim.like_button))
        presenterImpl.like(currentPerson)
        this.onDataLoaded()
         */
    }

    override fun onDislike() {

        val intent = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS)
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, "com.uninder.uninder")
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, "default")
        startActivity(intent)
/*
        addAnimation(dislikeBtn, AnimationUtils.loadAnimation(this.activity, R.anim.dislike_button))
        presenterImpl.dislike(currentPerson)
        this.onDataLoaded()
        */
    }


}

