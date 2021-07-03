package com.example.docpicker.entity

import java.io.Serializable

data class Traitement (
                  val maladie:String
                  ,val Description :String,
                  val medeciment :String,
                  val medecin :String,
                  val duré:String,
                  val id_traitement:Int

) : Serializable