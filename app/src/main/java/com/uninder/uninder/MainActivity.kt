package com.uninder.uninder

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.card_person.*
import android.support.v4.graphics.drawable.DrawableCompat.clearColorFilter
import android.widget.ImageButton
import android.view.MotionEvent
import android.graphics.PorterDuff
import android.view.View
import android.view.View.OnTouchListener
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        likeBtn.setOnClickListener { addAnimation(it, AnimationUtils.loadAnimation(this, R.anim.like_button)) }
        dislikeBtn.setOnClickListener { addAnimation(it, AnimationUtils.loadAnimation(this, R.anim.dislike_button)) }


    }

    fun addAnimation(view: View, animation: Animation) {

        view.animation = animation
        view.startAnimation(animation)
    }


}
