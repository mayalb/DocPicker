package com.example.docpicker.entity

import java.io.Serializable

data class RendezVous (
        val NomMedecin:String
        ,val Adresse :String,
        val Date:String,
        val Heure :String,
        val Etat:String,
        val id_rendez_vous:Int

) : Serializable