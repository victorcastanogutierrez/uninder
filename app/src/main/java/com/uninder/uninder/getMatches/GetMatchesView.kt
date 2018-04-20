package com.uninder.uninder.getMatches

import android.widget.ImageView

interface GetMatchesView {


    fun sendMail(email: String)

    fun putCircularImage(view: ImageView, url: String)
}