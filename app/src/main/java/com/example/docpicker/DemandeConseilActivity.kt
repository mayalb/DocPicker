package com.example.docpicker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.example.docpicker.entity.Conseil
import com.example.docpicker.entity.Doctor
import com.example.docpicker.roomdao.RoomService
import kotlinx.android.synthetic.main.activity_demande_conseil.*

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
            val token = PrefUtil.with(this).getInt(PrefUtil.Keys.idUser, 0)
             val message= Conseil(token,iddoctor,messageinput,0)
            RoomService.context=this
            RoomService.appDataBase.getConseilDao().addConseil(message)
            Toast.makeText(this, "${message.message}", Toast.LENGTH_SHORT).show()
            scheduleSycn()
        }
    }
    private fun scheduleSycn() {
        val constraints = Constraints.Builder().
        setRequiredNetworkType(NetworkType.CONNECTED).
            //    setRequiresBatteryNotLow(true).
        build()
        val req= OneTimeWorkRequest.Builder (SyncService::class.java).
        setConstraints(constraints).addTag("id1").
        build()
        val workManager = WorkManager.getInstance(this)
        workManager.enqueueUniqueWork("work", ExistingWorkPolicy.REPLACE,req)

    }
}