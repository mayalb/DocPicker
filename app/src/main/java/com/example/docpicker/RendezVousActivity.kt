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

class RendezVousActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rendez_vous)
        val doctor=intent.getSerializableExtra("doctor") as Doctor
        val iddoctor= doctor.id_doctor
        var call= RetrofitService.endpoint.getAppointment(iddoctor)
        call.enqueue(object : Callback<Appointment> {
            override fun onFailure(call: Call<Appointment>, t: Throwable) {
                Toast.makeText(this@RendezVousActivity, "Erreur de connexion", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Appointment>, response: Response<Appointment>) {

                if (response.isSuccessful){
                    val appointment = response.body()

                    if (appointment!= null) {
                        RendezVousMedecin.text=doctor.nom+" "+doctor.prenom
                        dateRendezVous.text=appointment.Date
                        heureRendezVous.text=appointment.Heure
                        adresseRendezVous.text=doctor.adresse

                    }else{
                        Toast.makeText(this@RendezVousActivity, "Réponse serveur erronné", Toast.LENGTH_SHORT).show()
                    }

                    //sessionManager.saveAuthToken(loginResponse.authToken)

                }else{

                    Toast.makeText(this@RendezVousActivity, "", Toast.LENGTH_SHORT).show()
                }


            }
        })


        confirmerRDVButton.setOnClickListener {
            val intent = Intent(this,QRScannerActivity::class.java)
            intent.putExtra("idRDV",1)
            startActivity( intent)
        }
    }
}