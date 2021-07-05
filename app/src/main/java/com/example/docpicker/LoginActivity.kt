package com.example.docpicker

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
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
       val token = PrefUtil.with(this).getInt(PrefUtil.Keys.idUser, 0)
       if (token !=0 ) {
           val intent=Intent(this@LoginActivity,MainActivity::class.java)
           startActivity(intent)
           finish()
       }
      val prefs = getSharedPreferences("userToken",Context.MODE_PRIVATE)
     //   if (prefs.getString("userToken", "")!=""){

     //   }




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
                            Toast.makeText(this@LoginActivity, "loginResponse.idUser", Toast.LENGTH_SHORT).show()
                            editor.putString("userToken", loginResponse.token)
                            editor.putInt("idUser",loginResponse.id)
                            PrefUtil.with(this@LoginActivity).save(PrefUtil.Keys.idUser,loginResponse.id)
                            PrefUtil.with(this@LoginActivity).save(PrefUtil.Keys.KEY1,loginResponse.token)
                            editor.apply()
                            Toast.makeText(this@LoginActivity, loginResponse.id.toString(), Toast.LENGTH_SHORT).show()
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