package com.uninder.uninder.handler

import android.app.*
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import com.uninder.uninder.MainActivity
import com.uninder.uninder.R

internal class NotificationHelper (context: Context) : ContextWrapper(context) {

    companion object {
        val FOLLOWERS_CHANNEL = "follower"
    }

    private val mNotificationManager: NotificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private val smallIcon: Int
        get() = android.R.drawable.stat_notify_chat


    init {
        val followersChannel = NotificationChannel(
                FOLLOWERS_CHANNEL,
                getString(R.string.newmatch),
                NotificationManager.IMPORTANCE_DEFAULT)
        followersChannel.lightColor = Color.GREEN
        followersChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 500, 200, 500)

        mNotificationManager.createNotificationChannel(followersChannel)

    }

    fun getNotificationFollower(title: String, body: String): Notification.Builder {
        return Notification.Builder(applicationContext)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(smallIcon)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
    }


    fun notify(id: Int, notification: Notification.Builder) {
        mNotificationManager.notify(id, notification.build())
    }

    val pendingIntent: PendingIntent
        get() {
            val openMainIntent = Intent(this, MainActivity::class.java)
            val stackBuilder = TaskStackBuilder.create(this)
            stackBuilder.addParentStack(MainActivity::class.java)
            stackBuilder.addNextIntent(openMainIntent)
            return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_ONE_SHOT)
        }
}