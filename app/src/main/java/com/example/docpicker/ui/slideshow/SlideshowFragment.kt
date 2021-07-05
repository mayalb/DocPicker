package com.example.docpicker.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.docpicker.DoctorAdapter
import com.example.docpicker.ProfilViewModel
import com.example.docpicker.R
import com.example.docpicker.RendezVousAdapter
import com.example.docpicker.entity.Doctor
import com.example.docpicker.entity.RendezVous
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_slideshow.*

class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel
    lateinit var profilViewModel: ProfilViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
                ViewModelProvider(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        RendezVousList.layoutManager= LinearLayoutManager(requireActivity())!!
       RendezVousList.adapter = RendezVousAdapter(requireActivity(),loadData())


    }
    fun loadData():List<RendezVous> {
        val data = mutableListOf<RendezVous>()
        data.add(RendezVous("Maya",1,"Tizi","17/09/2020","17h6",1,"Terminé",  1))
        data.add(RendezVous("Safi",1,"Batna","17/09/2020","17h6",1,"En attente",  1))

        return data
    }

}