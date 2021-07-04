package com.example.docpicker.roomdao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.docpicker.entity.Conseil
import com.example.docpicker.entity.User


@Database(entities = arrayOf(Conseil::class),version = 1)
abstract class AppDataBase:RoomDatabase() {

    abstract fun getConseilDao():ConseilDao

}