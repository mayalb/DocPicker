package com.example.docpicker.retrofit


import com.example.docpicker.entity.LoginRequest
import com.example.docpicker.entity.LoginResponse
import com.example.docpicker.entity.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface EndPoint {
    @POST("signin")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

}