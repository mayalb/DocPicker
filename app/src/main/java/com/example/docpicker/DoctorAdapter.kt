package com.example.docpicker

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.docpicker.entity.Doctor
import com.bumptech.glide.Glide
class DoctorAdapter(val context: Context, var data:List<Doctor>): RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.doctor_item, parent, false))

    }


    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      //  holder.img.setImageResource(data[position].img)
        Glide.with(context).load(baseUrl+data[position].photo).into( holder.photo)
        holder.nom.text=data[position].nom
        holder.prenom.text=data[position].prenom
        holder.num.text=data[position].phone
        holder.spec.text=data[position].specialite


        holder.direction.setOnClickListener { View->
            val longitude=data[position].longitude
            val latitude=data[position].latitude
            Toast.makeText(context, "latitude{$latitude}", Toast.LENGTH_SHORT).show()
           val uri = Uri.parse("http://maps.google.com/maps?daddr="+latitude+","+longitude)
          val geolocation=Uri.parse("geo:$latitude,$longitude")
          val intent= Intent(Intent.ACTION_VIEW,uri)
            if(intent.resolveActivity(context.packageManager)!=null){
               context.startActivity(intent)
            }


        }
        holder.num.setOnClickListener {View->
            val numero= data[position].phone
            val uri = Uri.parse("tel:$numero")
            val intent = Intent(Intent.ACTION_DIAL, uri)
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }}
        holder.itemView.setOnClickListener {  View->
            val intent= Intent(context,DetailDoctorActivity::class.java)
            intent.putExtra("doctor",data[position])
            context.startActivity(intent)
        }


    }

}



class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nom= view.findViewById<TextView>(R.id.textViewnom) as TextView
    val prenom= view.findViewById<TextView>(R.id.textViewprenom) as TextView
    val num= view.findViewById<TextView>(R.id.textViewphone) as TextView
    val spec= view.findViewById<TextView>(R.id.TextviewSpecialite) as TextView
    val photo= view.findViewById<ImageView>(R.id.imageView) as ImageView
    val direction=view.findViewById<ImageView>(R.id.direction) as ImageView
    // val itemview=view.findViewById<MenuView.ItemView>(R.id.itemview) as MenuView.ItemView

}