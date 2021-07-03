package com.example.docpicker.entity

import java.io.Serializable

data class LoginResponse(
    var statusCode:Int,
    var token: String,
    var idUser: Int
) : Serializable