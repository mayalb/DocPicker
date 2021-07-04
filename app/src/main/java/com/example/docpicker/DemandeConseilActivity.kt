package com.example.docpicker

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.work.*
import com.example.docpicker.entity.Conseil
import com.example.docpicker.entity.Doctor
import com.example.docpicker.retrofit.RetrofitService
import com.example.docpicker.roomdao.RoomService
import kotlinx.android.synthetic.main.activity_demande_conseil.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DemandeConseilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demande_conseil)
        val doctor=intent.getSerializableExtra("doctor") as Doctor
        val iddoctor= doctor.id_doctor
        NomMedecinConseil.text="Docteur "+doctor.nom+" "+doctor.prenom
        sendMessageButton.setOnClickListener { View->
            val messageinput= MessageConseil.text.toString()
            var id=0
            val iduser= getSharedPreferences("userToken", Context.MODE_PRIVATE).getInt("idUser",id)
             val message= Conseil(iduser,iddoctor,messageinput,0)
            RoomService.context=this
            RoomService.appDataBase.getConseilDao().addMessage(message)
            Toast.makeText(this, "Aucunuuuu", Toast.LENGTH_SHORT).show()
            scheduleSycn()
        }
    }
    private fun scheduleSycn() {
        val constraints = Constraints.Builder().
        setRequiredNetworkType(NetworkType.UNMETERED).
            //    setRequiresBatteryNotLow(true).
        build()
        val req= OneTimeWorkRequest.Builder (SyncService::class.java).
        setConstraints(constraints).addTag("id1").
        build()
        val workManager = WorkManager.getInstance(this)
        workManager.enqueueUniqueWork("work", ExistingWorkPolicy.REPLACE,req)

    }
}