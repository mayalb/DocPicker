package com.example.docpicker.retrofit
import com.example.docpicker.baseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitService {

    val endpoint: EndPoint by lazy {
        Retrofit.Builder().baseUrl(baseUrl).
        addConverterFactory(GsonConverterFactory.create()).build()
            .create(EndPoint::class.java)

    }
}