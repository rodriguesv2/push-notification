package br.com.rubensrodrigues.push_notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.math.log

class FMService: FirebaseMessagingService() {

    private val NOTIFY_TAG = 123


    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        val pendingIntent = PendingIntent.getActivity(
            baseContext, NOTIFY_TAG,
            Intent(applicationContext, Main2Activity::class.java),
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        //Criamos a notificacao e capturamos os dados
        val notification = NotificationCompat.Builder(this, "")
            .setSmallIcon(R.drawable.seu_icone)
            .setContentTitle(getString(R.string.app_name) + " - " + remoteMessage!!.notification!!.title)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(remoteMessage.notification!!.body)
            )
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 2000))
            .setContentText(remoteMessage.notification!!.body)
            .setContentIntent(pendingIntent).build()

        val context = baseContext
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        manager.notify(NOTIFY_TAG, notification)
    }
}