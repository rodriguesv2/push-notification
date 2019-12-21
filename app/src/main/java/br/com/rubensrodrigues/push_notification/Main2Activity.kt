package br.com.rubensrodrigues.push_notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener{task ->
            Log.d("TOKEN", task.result?.token)
        }

        acaoBotao()
    }

    private fun acaoBotao() {
        button.setOnClickListener {

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val channel =
                NotificationChannel("test", "test", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)

            val notification = NotificationCompat.Builder(this, "test")
                .setSmallIcon(R.drawable.seu_icone)
                .setContentTitle("Goiaba")
                .setVibrate(longArrayOf(4000, 4000))
                .setContentText("Suco de goiaba geladinho")
                .build()

            notificationManager
                .notify(123, notification)
        }
    }
}
