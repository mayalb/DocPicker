package com.example.docpicker

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import androidx.work.impl.utils.futures.SettableFuture
import com.example.docpicker.entity.Conseil
import com.example.docpicker.retrofit.RetrofitService
import com.example.docpicker.roomdao.RoomService
import com.google.common.util.concurrent.ListenableFuture
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@SuppressLint("RestrictedApi")
class SyncService(val ctx: Context, val workParamters: WorkerParameters): ListenableWorker(ctx, workParamters){

    lateinit var  future: SettableFuture<Result>

    override fun startWork(): ListenableFuture<Result> {
        future = SettableFuture.create()
        val message= RoomService.appDataBase.getConseilDao().getMessagesToSynchronize()
        Toast.makeText(ctx, message[0].message.toString(), Toast.LENGTH_SHORT).show()
        addMessage(message[0])

        return future
    }

    fun addMessage(conseil:Conseil) {
        val result = RetrofitService.endpoint.addConseil(conseil)
        result.enqueue(object: Callback<String> {

            override fun onFailure(call: Call<String>?, t: Throwable?) {

                future.set(Result.retry())

            }

            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                if(response?.isSuccessful!!) {
                    conseil.isSynchronized=1
                    RoomService.appDataBase.getConseilDao().updateMessage(conseil)
                //    RetrofitService.endpoint.addConseil(conseil)

                    future.set(Result.success())
                    Log.d("push",response.body().toString())
                    Log.d("push",response.code().toString())

                }
                else
                {
                    future.set(Result.retry())
                    Log.d("push",response.body().toString())
                    Log.d("push",response.code().toString())
                }
            }

        })
    }


}
