package com.example.docpicker.retrofit


import com.example.docpicker.entity.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface EndPoint {
    @POST("signin")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
    @GET("doctors")
    fun getdoctors(): Call<List<Doctor>>
    @POST("conseil")
    fun addMessage(@Body conseil: Conseil):Call<String>

}