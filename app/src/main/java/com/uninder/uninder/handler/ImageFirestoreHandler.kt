package com.uninder.uninder.handler

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage


class ImageFirestoreHandler {

    private val mStorageRef = FirebaseStorage.getInstance().reference

    private val currentUserMail = FirebaseAuth.getInstance().currentUser!!.email


    fun uploadFile(imageRoute: Uri, success: (String) -> Unit, error: () -> Unit) {
        val cloudStorage = mStorageRef.child("$currentUserMail/profilePic")
        cloudStorage.putFile(imageRoute)
                .addOnSuccessListener({ taskSnapshot ->
                    val downloadUrl = taskSnapshot.downloadUrl
                    success(downloadUrl.toString())
                })
                .addOnFailureListener({
                    error()
                })
    }


}