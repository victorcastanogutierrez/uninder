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
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }





}
