package com.example.docpicker.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

import java.util.*
@Entity
data class Traitement (
                  val maladie:String
                  ,val Description :String,
                  val medeciment :String,
                  val medecin :String,
                  val idPatient:Int,
                  val treatmentBeginDate :Date,
                  val treatmentEndDate :Date,
                  @PrimaryKey
                  val id_traitement:Int

) : Serializable