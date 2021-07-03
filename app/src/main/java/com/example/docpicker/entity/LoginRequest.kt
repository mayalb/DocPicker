package com.example.docpicker.entity

import androidx.room.Entity
import java.io.Serializable
@Entity(tableName = "authuser")
data class LoginRequest (

    var phone: String,
    var password: String
): Serializable