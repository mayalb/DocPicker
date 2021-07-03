package com.example.docpicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail_doctor.*
import kotlinx.android.synthetic.main.activity_login.confirmerButton

class DetailDoctorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_doctor)
        confirmerButton.setOnClickListener {
            val rendezvousIntent = Intent(this,RendezVousActivity::class.java)
            startActivity( rendezvousIntent)
        }
        annulerButton.setOnClickListener {
            val conseilIntent = Intent(this,DemandeConseilActivity::class.java)
            startActivity( conseilIntent)
        }
    }
}