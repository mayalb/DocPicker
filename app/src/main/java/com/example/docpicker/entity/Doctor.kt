package com.example.docpicker.entity

import java.io.Serializable

data class Doctor(val img:Int,
                  val name:String
                  ,val prenom :String,
                  val phone:String,
                  val specialite :String,
                  val id_doctor:Int,
                  val longitude:String,
                  val latitude:String
) : Serializable