package com.example.docpicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.docpicker.entity.Appointment
import com.example.docpicker.entity.Doctor
import com.example.docpicker.entity.LoginResponse
import com.example.docpicker.retrofit.RetrofitService
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_rendez_vous.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RendezVousActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rendez_vous)
        val doctor=intent.getSerializableExtra("doctor") as Doctor
        val iddoctor= doctor.id_doctor
        adresseRendezVous.text= doctor.adresse
        RendezVousMedecin.text=doctor.nom+" "+doctor.prenom
        var call= RetrofitService.endpoint.getAppointment(iddoctor)
        call.enqueue(object : Callback<Appointment> {
            override fun onFailure(call: Call<Appointment>, t: Throwable) {
                Toast.makeText(this@RendezVousActivity, "Erreur de connexion", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Appointment>, response: Response<Appointment>) {

                if (response.isSuccessful){
                    val appointment = response.body()

                    if (appointment!= null) {
                        val date = appointment.appointmentTime
                    //    <>
                        var obj = IntRange(0,9)
                        val year= date.slice(obj)
                        Toast.makeText(this@RendezVousActivity, "yeaar:{$year}", Toast.LENGTH_SHORT).show()
                        dateRendezVous.text= "Date :"+  year
                        var h = IntRange(11,18)
                        val hour= date.slice(h)
                        heureRendezVous.text= "Heure :"+hour



                    }else{
                        Toast.makeText(this@RendezVousActivity, "Réponse serveur erronné", Toast.LENGTH_SHORT).show()
                    }

                }else{

                    Toast.makeText(this@RendezVousActivity, "", Toast.LENGTH_SHORT).show()
                }


            }
        })


        confirmerRDVButton.setOnClickListener {
            val intent = Intent(this,QRScannerActivity::class.java)
            intent.putExtra("idRDV",iddoctor)
            startActivity( intent)
        }
    }
}