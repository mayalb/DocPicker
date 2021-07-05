package com.example.docpicker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.docpicker.entity.RendezVous
import com.example.docpicker.entity.Traitement

class TraitementAdapter(val context: Context, var data:List<Traitement>): RecyclerView.Adapter<TraitementViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraitementViewHolder {

        return TraitementViewHolder(LayoutInflater.from(context).inflate(R.layout.traitement_item, parent, false))

    }


    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TraitementViewHolder, position: Int) {
        holder.medecin.text="Cher Docteur "+ data[position].medecin
        holder.description.text="Description : "+data[position].Description
       // holder.date.text="Durée : "+data[position].duré
        holder.medicament.text="Heure : "+data[position].medeciment
        holder.maladie.text="Maladie : "+data[position].maladie

    }

}



class TraitementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val maladie= view.findViewById<TextView>(R.id.maladieTraitement) as TextView
    val date= view.findViewById<TextView>(R.id.DateTraitement) as TextView
    val medicament= view.findViewById<TextView>(R.id.medTraitement) as TextView
    val medecin= view.findViewById<TextView>(R.id.medecinTraitement) as TextView
    val description= view.findViewById<TextView>(R.id.descriptionTraitement) as TextView
    val traitement=view.findViewById<TextView>(R.id.traitement) as TextView


}