package com.example.docpicker.entity
import androidx.room.Entity
import java.io.Serializable

data class User(
    val name:String,
    val Prenom:String,
    val userId :Int,
    val phone :String
) : Serializable