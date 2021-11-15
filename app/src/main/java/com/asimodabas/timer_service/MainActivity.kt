package com.asimodabas.timer_service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonBasla.setOnClickListener {

            startService(Intent(this@MainActivity,TimerServis::class.java))

        }
        buttonDur.setOnClickListener {

            stopService(Intent(this@MainActivity,TimerServis::class.java))

        }


    }
}