package com.example.docpicker.entity

data class Appointment (
    val idAppointment:Int,
    val idPatient:Int,
    val idDoctor:Int,
    val state:Int,
    val Date :String,
    val Heure:String,
    val duration:Int
)