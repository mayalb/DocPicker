package com.example.docpicker.retrofit


import com.example.docpicker.entity.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface EndPoint {
    @POST("signin")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
    @GET("doctors")
    fun getdoctors(): Call<List<Doctor>>
    @POST("conseil")
    fun addConseil(@Body conseil: Conseil):Call<String>
    @GET("traitements/{idPatient}")
    fun getCurrentTreaments(@Path(value="idPatient") idPatient:Int):Call<List<Traitement>>

    @GET("appointment/appoint/{idDoctor}")
    fun getAppointment(@Path(value="idDoctor") idDoctor:Int):Call<Appointment>

}