package com.uninder.uninder.model

import android.app.Notification
import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage

data class Person(val name: String?, val description: String, val email: String?, val gender: Gender?, val genderLooked: Gender?, val likes: Map<String, Boolean>?) {

    constructor() : this("", "", "", null, null, null)

    companion object {

        private val database: FirebaseDatabase? = FirebaseDatabase.getInstance()
        private val currentUser = FirebaseAuth.getInstance().currentUser
        private const val PLACEHOLDER = "https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_1280.png"

        fun savePersonSettings(description: String, gender: Int, searchGender: Int) {
            val ref = database!!.getReference("")

            ref.child("users").child(currentUser!!.email!!.replace('.', '_'))
                    .setValue(Person(currentUser.displayName, description, currentUser.email,
                            Gender.get(gender), Gender.get(searchGender), null))
        }

        fun update(propertyKey: String, newName: String) {

            val ref = database!!.getReference("")

            ref.child("users/${currentUser!!.email!!.replace('.', '_')}").child(propertyKey).setValue(
                    newName
            )
        }



        fun findAll(userEmail: String?, onFinish: (MutableList<Person>) -> Unit) {

            val ref = database!!.getReference("")

            //Loads all persons to like/dislike
            val personsListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val persons = mutableListOf<Person>()
                    dataSnapshot.children.forEach({
                        val person = it.getValue<Person>(Person::class.java)

                        if (!person!!.email.equals(currentUser!!.email)) {
                            val denormalizedMail = currentUser!!.email.toString().replace('.', '_')
                            if (null == person!!.likes) {
                                persons.add(person)

                            } else if (!person!!.likes!!.containsKey(denormalizedMail)) {

                                persons.add(person)
                            }

                        }
                    })
                    onFinish(persons)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.d("asd", "Error tt")
                }
            }

            //Loads current person data
            val currentListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val currentUser = dataSnapshot.children.first().getValue<Person>(Person::class.java)
                    ref.child("users").orderByChild("gender").equalTo(currentUser!!.genderLooked.toString())
                            .addListenerForSingleValueEvent(personsListener)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.d("asd", "Error tt")
                }
            }

            ref.child("users").orderByChild("email").equalTo(userEmail)
                    .addListenerForSingleValueEvent(currentListener)

        }

        fun findPersonImage(email: String?, onFinish: (Uri) -> Unit) {
            val storage = FirebaseStorage.getInstance().reference

            storage.child(email!!.replace('.', '_') + "/profilePic").downloadUrl.addOnFailureListener({
                onFinish(Uri.parse(PLACEHOLDER))
            })

            storage.child(email!!.replace('.', '_') + "/profilePic").downloadUrl.addOnSuccessListener({
                onFinish(it)
            })
        }

        fun doLike(personLiked: Person?, email: String?, isLike: Boolean) {
            val ref = database!!.getReference("users")
            ref.child("${personLiked?.email!!.replace('.', '_')}").child("likes").child("${email!!.replace('.', '_')}").setValue(isLike)
        }

        fun uploadFileImage(imageRoute: Uri, currentUserMail: String, success: (String) -> Unit, error: () -> Unit) {

            val storage = FirebaseStorage.getInstance().reference

            val cloudStorage = storage.child("${currentUserMail!!.replace('.', '_')}/profilePic")
            cloudStorage.putFile(imageRoute)
                    .addOnSuccessListener({ taskSnapshot ->
                        val downloadUrl = taskSnapshot.downloadUrl
                        success(downloadUrl.toString())
                    })
                    .addOnFailureListener({
                        error()
                    })
        }

        fun findMatches(email: String?, onFinish: (MutableList<String>) -> Unit) {
            val ref = database!!.getReference("")
            val matchesListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val result = mutableListOf<String>()
                    dataSnapshot.children.forEach({
                        result.add(it.value.toString())
                    })
                    onFinish(result)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.d("asd", "Error tt")
                }
            }

            ref.child("users").child(email!!.replace('.', '_') + "/matches")
                    .addListenerForSingleValueEvent(matchesListener)
        }

    }
}
