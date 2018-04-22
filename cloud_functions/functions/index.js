'use strict'

const functions = require('firebase-functions')
const admin = require('firebase-admin')
admin.initializeApp()

exports.matches = functions.database.ref('/users/{email}/likes/{value}')
  .onCreate((snapshot, context) => {
    // snapshot.val() -> true o false (like)
    // snapshot.key -> es el que ha dado like
    const liked = String(snapshot.val())

    if (liked === 'true') {
      // Email que da el like
      const emailLike = snapshot.key
      // Email al que da el like
      const liked = snapshot.ref.parent.parent.key

      // Busca si el like es recíproco
      return admin.database().ref('/users').child(emailLike.toString() + '/likes').once('value').then(snap => {
        snap.forEach(x => {
          if (x.key === liked && String(x.val()) === 'true') {
            admin.database().ref('/users').child(emailLike.toString() + '/matches').push(liked.toString())
            admin.database().ref('/users').child(liked.toString() + '/matches').push(emailLike.toString())
          }
        })
        return false
      })
    }
    return null
  })
