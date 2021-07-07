package com.example.docpicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import android.graphics.Bitmap;
import android.widget.Toast
import com.example.docpicker.entity.Appointment
import com.example.docpicker.entity.Doctor
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_q_r_scanner.*

class QRScannerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_q_r_scanner)
        val date= intent.getStringExtra("date")
        val heure=intent.getStringExtra("heure")
         val randezVous = ObjRDV(heure.toString(),date.toString())

        Toast.makeText(this@QRScannerActivity, "in scanner", Toast.LENGTH_SHORT).show()

        var gson = Gson()
        val qr_quore=gson.toJson(randezVous)
        val multiFormatWriter = MultiFormatWriter()
        try{
            var bitMatrix = multiFormatWriter.encode(qr_quore,
                BarcodeFormat.QR_CODE,200,200);
            var barcodeEncoder =  BarcodeEncoder();
            var bitmap=barcodeEncoder.createBitmap(bitMatrix)
            QRimage.setImageBitmap(bitmap)

        }catch(e: Exception){
            e.printStackTrace();
        }


    }
}