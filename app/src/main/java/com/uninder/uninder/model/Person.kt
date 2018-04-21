package com.uninder.uninder.model

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

data class Person(val name: String?, val description: String, val email: String?, val gender: Gender?, val genderLooked: Gender?) {

    constructor() : this("", "", "", null, null)

    companion object {
        private val database: FirebaseDatabase? = FirebaseDatabase.getInstance()
        private val currentUser = FirebaseAuth.getInstance().currentUser

        fun savePersonSettings(description: String, gender: Int, searchGender: Int) {
            val ref = database!!.getReference("")

            ref.child("users").child(currentUser!!.email!!.replace('.', '_'))
                    .setValue(Person(currentUser!!.displayName, description, currentUser!!.email,
                            Gender.get(gender), Gender.get(searchGender)))
        }

        fun update(propertyKey :String , newName: String) {

            val ref = database!!.getReference("")

            ref.child("users/${currentUser!!.email!!.replace('.', '_')}").child(propertyKey).setValue(
                    newName
            )

        }


        fun findAll(onFinish: (List<Person>) -> Unit) {
            val personsListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val persons = mutableListOf<Person>()
                    dataSnapshot.children.forEach({
                        val person = it.getValue<Person>(Person::class.java)
                        if (person!!.email.equals(currentUser!!.email)) {
                            persons.add(person)
                        }
                    })
                    onFinish(persons)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.d("asd", "Error tt")
                }
            }

            val ref = database!!.getReference("")
            ref.child("users").addListenerForSingleValueEvent(personsListener)
        }
    }
}
