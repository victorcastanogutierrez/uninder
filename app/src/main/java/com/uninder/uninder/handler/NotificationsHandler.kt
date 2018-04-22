package com.uninder.uninder.handler

import android.app.*
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.support.v4.app.NotificationCompat
import com.uninder.uninder.MainActivity
import com.uninder.uninder.R
import com.uninder.uninder.findPeople.FindPeopleFragment
import android.app.PendingIntent
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build


private const val NOTIFICATION_CHANNEL ="default"

internal class NotificationHelper(context: Context) : ContextWrapper(context) {


    companion object {
        const val MATCHES_CHANNEL = "default"
    }

    private val mNotificationManager: NotificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }


    fun createNotification(aMessage: String) {
        val NOTIFY_ID = 1002
        val builder: NotificationCompat.Builder
        val importance = NotificationManager.IMPORTANCE_HIGH

        var mChannel = mNotificationManager.getNotificationChannel(NOTIFICATION_CHANNEL)

        if (mChannel == null) {
            createChannel(mChannel, NOTIFICATION_CHANNEL, NOTIFICATION_CHANNEL, importance, NOTIFICATION_CHANNEL)
        }

        builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL)
        val pendingIntent: PendingIntent = createIntent()
        buildNotification(builder, aMessage, pendingIntent)

        val notification = builder.build()
        mNotificationManager.notify(NOTIFY_ID, notification)
    }

    private fun createIntent(): PendingIntent {
        val intent: Intent
        val pendingIntent: PendingIntent
        intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        return pendingIntent
    }

    private fun buildNotification(builder: NotificationCompat.Builder, aMessage: String, pendingIntent: PendingIntent) {
        builder.setContentTitle(aMessage)  // required
                .setSmallIcon(android.R.drawable.ic_popup_reminder) // required
                .setContentText(this.getString(R.string.app_name))  // required
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setTicker(aMessage)
                .setVibrate(longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400))
    }

    private fun createChannel(mChannel: NotificationChannel?, id: String, name: String, importance: Int, description: String) {
        var mChannel1 = mChannel
        mChannel1 = NotificationChannel(id, name, importance)
        mChannel1!!.setDescription(description)
        mChannel1!!.enableVibration(true)
        mChannel1!!.setVibrationPattern(longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400))
        mNotificationManager.createNotificationChannel(mChannel1)
    }
}