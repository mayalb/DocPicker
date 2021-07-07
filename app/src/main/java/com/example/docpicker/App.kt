package com.example.docpicker

import android.app.Application
import com.example.docpicker.roomdao.RoomService

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        RoomService.context = applicationContext
    }

}
