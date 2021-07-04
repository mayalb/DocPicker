package com.example.docpicker.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Conseil")
data class Conseil(
    val id_patient:Int,
    val id_doctor:Int,
    val message:String,
    var isSynchronized:Int =0
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_conseil")
    var conseilId:Int?=null
}