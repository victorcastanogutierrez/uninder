package com.uninder.uninder.handler

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage


class ImageFirestoreHandler {

    private val mStorageRef = FirebaseStorage.getInstance().reference;


    fun uploadFile(imageRoute: Uri, userMail: String) {
        val cloudStorage = mStorageRef.child("$userMail/profilePic")
        cloudStorage.putFile(imageRoute)
                .addOnSuccessListener({ taskSnapshot ->
                    val downloadUrl = taskSnapshot.downloadUrl
                })
                .addOnFailureListener( {
                    // Handle unsuccessful uploads
                    // ...
                })
    }




}