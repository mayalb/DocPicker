package com.example.docpicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.docpicker.entity.Appointment
import com.example.docpicker.entity.Doctor
import com.example.docpicker.entity.LoginResponse
import com.example.docpicker.entity.objetREndezVous
import com.example.docpicker.retrofit.RetrofitService
import com.example.docpicker.ui.gallery.GalleryViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_rendez_vous.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RendezVousActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rendez_vous)
        val doctor=intent.getSerializableExtra("doctor") as Doctor
        val iddoctor= doctor.id_doctor

        adresseRendezVous.text = doctor.adresse
        RendezVousMedecin.text = doctor.nom + " " + doctor.prenom
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
                        var obj=IntRange(0,9)
                        val year = date.slice(obj)



                        RendezVousMedecin.text = doctor.nom+" "+doctor.prenom
                        dateRendezVous.text="Date : "+year

                        var h = IntRange(11,18)
                        val hour= date.slice(h)

                        heureRendezVous.text="Heure : "+ hour

                        adresseRendezVous.text=doctor.adresse
                        Toast.makeText(this@RendezVousActivity, "Appoitment", Toast.LENGTH_SHORT).show()


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
         var sliceDate = IntRange(7,16)
         val  date= dateRendezVous.text.toString()
         var sliceHour = IntRange(8,15)
         val heure=  heureRendezVous.text.toString().slice(sliceHour)
         val completeDate = date + " " + heure
         val idPatient= PrefUtil.with(this).getInt(PrefUtil.Keys.idUser, 0)
         val newAppoint = objetREndezVous(idPatient,doctor.id_doctor, completeDate, 30, 1)
         var call= RetrofitService.endpoint.addAppointment(newAppoint)
         call.enqueue(object : Callback<String> {
             override fun onFailure(call: Call<String>, t: Throwable) {
                 Toast.makeText(this@RendezVousActivity, "Erreur de connexion", Toast.LENGTH_SHORT).show()
             }

             override fun onResponse(call: Call<String>, response: Response<String>) {

                 if (response.isSuccessful){
                     val appointment = response.body()

                     if (appointment!= null) {

                         Toast.makeText(this@RendezVousActivity, "Rendez-vous pris", Toast.LENGTH_SHORT).show()


                     }else{
                         Toast.makeText(this@RendezVousActivity, "Réponse serveur erronné", Toast.LENGTH_SHORT).show()
                     }

                     //sessionManager.saveAuthToken(loginResponse.authToken)

                 }else{

                     Toast.makeText(this@RendezVousActivity, "", Toast.LENGTH_SHORT).show()
                 }


             }
         })

         val intent = Intent(this,QRScannerActivity::class.java)
         intent.putExtra("date",date)
         intent.putExtra("heure",heure)
         startActivity( intent)
     }



    }
}