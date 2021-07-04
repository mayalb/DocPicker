package com.example.docpicker.roomdao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.docpicker.entity.Conseil

@Dao
interface ConseilDao {

    @Query("select * from Conseil")
    fun getConseils():List<Conseil>
    @Query("select * from Conseil where isSynchronized=0")
    fun getMessagesToSynchronize():List<Conseil>
    @Insert
    fun addMessage(vararg conseil: Conseil)
    @Update
    fun updateMessage(conseil: Conseil)


}