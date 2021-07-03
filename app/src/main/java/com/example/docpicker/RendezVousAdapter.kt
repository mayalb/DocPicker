package com.example.docpicker

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.docpicker.entity.Doctor
import com.example.docpicker.entity.RendezVous

class RendezVousAdapter(val context: Context, var data:List<RendezVous>): RecyclerView.Adapter<RendezVousViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RendezVousViewHolder {

        return RendezVousViewHolder(LayoutInflater.from(context).inflate(R.layout.rendezvous_item, parent, false))

    }


    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RendezVousViewHolder, position: Int) {
        holder.nomMedecin.text="Docteur "+data[position].NomMedecin
        holder.adresse.text="Adresse : "+data[position].Adresse
        holder.date.text="Date : "+data[position].Date
        holder.heure.text="Heure :"+data[position].Heure
        holder.etat.text="Rendez Vous "+data[position].Etat




    }

}



class RendezVousViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nomMedecin= view.findViewById<TextView>(R.id.NomMedRendezVsItem) as TextView
    val adresse= view.findViewById<TextView>(R.id.adresseRendezVousItem) as TextView
    val date= view.findViewById<TextView>(R.id.dateRendezVousItem) as TextView
    val heure= view.findViewById<TextView>(R.id.HeureRendezVousItem) as TextView
    val etat= view.findViewById<TextView>(R.id.NumRendezVous) as TextView


}