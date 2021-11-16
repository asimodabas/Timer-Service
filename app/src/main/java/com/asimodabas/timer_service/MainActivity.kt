package com.asimodabas.timer_service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonBasla.setOnClickListener {

            startService(Intent(this@MainActivity, TimerServis::class.java))

            val builder: NotificationCompat.Builder
            val bildirimYonetıcisi =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val intent = Intent(this, MainActivity::class.java)
            val gidilecekIntent =
                PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val kanalId = "kanalId"
                val kanalAd = "kanalAd"
                val kanalTanitim = "kanalTanıtım"
                val kanalOnceligi = NotificationManager.IMPORTANCE_HIGH

                var kanal: NotificationChannel? = bildirimYonetıcisi.getNotificationChannel(kanalId)

                if (kanal == null) {
                    kanal = NotificationChannel(kanalId, kanalAd, kanalOnceligi)
                    kanal.description = kanalTanitim
                    bildirimYonetıcisi.createNotificationChannel(kanal)

                }

                builder = NotificationCompat.Builder(this, kanalId)
                builder.setContentTitle("Title")
                    .setContentText("Contents")
                    .setSmallIcon(R.drawable.ic_baseline_adb_24)
                    .setContentIntent(gidilecekIntent)
                    .setAutoCancel(true)

            } else {

                builder = NotificationCompat.Builder(this)
                builder.setContentTitle("Title")
                    .setContentText("Contents")
                    .setSmallIcon(R.drawable.ic_baseline_adb_24)
                    .setContentIntent(gidilecekIntent)
                    .setAutoCancel(true)
                    .priority = Notification.PRIORITY_HIGH

            }
            bildirimYonetıcisi.notify(1,builder.build())

        }
        buttonDur.setOnClickListener {

            stopService(Intent(this@MainActivity, TimerServis::class.java))

        }


    }
}