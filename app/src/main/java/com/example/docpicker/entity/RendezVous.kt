package com.example.docpicker.entity

import java.io.Serializable

data class RendezVous (
        val NomMedecin:String,
        val id_medecin:Int,
        val Adresse :String,
        val Date:String,
        val Heure :String,
        val id_patient:Int,
        val Etat:String,
        val id_rendez_vous:Int

) : Serializable