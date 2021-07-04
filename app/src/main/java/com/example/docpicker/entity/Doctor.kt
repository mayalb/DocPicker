package com.example.docpicker.entity

import java.io.Serializable

data class Doctor(val photo:String,
                  val nom:String
                  ,val prenom :String,
                  val phone:String,
                  val specialite :String,
                  val id_doctor:Int,
                  val longitude:String,
                  val latitude:String,
                  val adresse:String
) : Serializable