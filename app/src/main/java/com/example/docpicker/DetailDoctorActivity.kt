package com.example.docpicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.docpicker.entity.Doctor
import kotlinx.android.synthetic.main.activity_detail_doctor.*
import kotlinx.android.synthetic.main.activity_login.confirmerButton

class DetailDoctorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_doctor)
        val doctor=intent.getSerializableExtra("doctor") as Doctor
        val iddoctor= doctor.id_doctor
        Glide.with(this).load(baseUrl+doctor.photo).into( detailImage)
        detailAdresse.text=doctor.adresse
        detailEmail.text=doctor.phone
        detailSpecialite.text="Chez un "+doctor.specialite

        confirmerButton.setOnClickListener {
            val intent = Intent(this,RendezVousActivity::class.java)
             intent.putExtra("doctor",doctor)
            startActivity( intent)
        }
        annulerButton.setOnClickListener {
            val conseilIntent = Intent(this,DemandeConseilActivity::class.java)
            conseilIntent.putExtra("doctor",doctor)
            startActivity( conseilIntent)
        }
    }
}