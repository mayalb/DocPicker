package com.example.docpicker.entity

import java.io.Serializable

data class LoginResponse(
    var phone:String,
    var token: String,
    var id: Int
) : Serializable