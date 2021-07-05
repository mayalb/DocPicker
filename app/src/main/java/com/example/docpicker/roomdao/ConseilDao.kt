package com.example.docpicker.roomdao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.docpicker.entity.Conseil

@Dao
interface ConseilDao {

    @Query("select * from conseil")
    fun getConseils():List<Conseil>
    @Query("select * from conseil where isSynchronized=0")
    fun getMessagesToSynchronize():List<Conseil>
    @Insert
    fun addConseil(vararg conseil: Conseil)
    @Update
    fun updateMessage(conseil: Conseil)


}