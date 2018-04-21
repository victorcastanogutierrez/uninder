package com.uninder.uninder.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

data class Person(val name: String?, val description: String, val email: String?, val gender: Gender?, val genderLooked: Gender?) {

    companion object {
        private val database:FirebaseDatabase? = FirebaseDatabase.getInstance()
        private val currentUser = FirebaseAuth.getInstance().currentUser

        fun savePersonSettings(description: String, gender:Int, searchGender:Int) {
            val ref = database!!.getReference("")

            ref.child("users").child(currentUser!!.email!!.replace('.', '_'))
                    .setValue(Person(currentUser!!.displayName, description, currentUser!!.email,
                            Gender.get(gender), Gender.get(searchGender)))
        }
    }
}