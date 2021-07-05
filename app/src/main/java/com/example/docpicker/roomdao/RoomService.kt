package com.example.docpicker.roomdao

import android.content.Context
import androidx.room.Room

object RoomService {
    lateinit var context: Context

    val appDataBase: AppDataBase by lazy {
        Room.databaseBuilder(context, AppDataBase::class.java,"localdatabase").allowMainThreadQueries().build()

        //build()
    }
}