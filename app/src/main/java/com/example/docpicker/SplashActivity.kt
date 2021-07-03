package com.example.docpicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        SystemClock.sleep(3000)
        val loginIntent = Intent(this,LoginActivity::class.java)
        startActivity(loginIntent)
        finish()
    }
}