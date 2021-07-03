package com.example.docpicker

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.docpicker.entity.LoginRequest
import com.example.docpicker.entity.LoginResponse
import com.example.docpicker.entity.User
import com.example.docpicker.retrofit.RetrofitService
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

   //  var prefs SharedPreferences = context.getSharedPreferences("userToken"), Context.MODE_PRIVATE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
       val prefs = getSharedPreferences("userToken",Context.MODE_PRIVATE)
        if (prefs.getString("userToken", "")!=""){
            val intent=Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }




        confirmerButton.setOnClickListener {
            val phone= numero.text.toString()
            val mdp=mdp.text.toString()
            val loginrequest= LoginRequest(phone,mdp)
            var call= RetrofitService.endpoint.login(loginrequest)
            call.enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Erreur de connexion", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                    if (response.isSuccessful){
                        val loginResponse = response.body()
                        val editor = prefs.edit()
                        if (loginResponse != null) {
                            editor.putString("userToken", loginResponse.token)
                            editor.apply()
                            val intent=Intent(this@LoginActivity,MainActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this@LoginActivity, "Réponse serveur erronné", Toast.LENGTH_SHORT).show()
                        }

                        //sessionManager.saveAuthToken(loginResponse.authToken)

                    }else{
                        val rep= response.body()?.token

                        Toast.makeText(this@LoginActivity, "Veuillez vérifier vos coordonées", Toast.LENGTH_SHORT).show()
                    }


                }
            })


        }
    }
}