package com.example.docpicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import android.graphics.Bitmap;
import kotlinx.android.synthetic.main.activity_q_r_scanner.*

class QRScannerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_q_r_scanner)
        val id= intent.getIntExtra("id",0)
        val QRvalue=  id.toString()
        val qrgencoder =  QRGEncoder(QRvalue,null,QRGContents.Type.TEXT,120)
        val qrbitmap = qrgencoder.bitmap
        QRimage.setImageBitmap(qrbitmap)
    }
}