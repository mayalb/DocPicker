package com.example.docpicker

import androidx.lifecycle.ViewModel

class ProfilViewModel: ViewModel() {
    var idUser=0
    fun setIdUser(idUser: Int):Int{
        this.idUser=idUser
       return  this.idUser
    }
}