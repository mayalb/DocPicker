package com.example.docpicker.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.docpicker.DoctorAdapter
import com.example.docpicker.R
import com.example.docpicker.RendezVousAdapter
import com.example.docpicker.TraitementAdapter
import com.example.docpicker.entity.RendezVous
import com.example.docpicker.entity.Traitement
import com.example.docpicker.ui.slideshow.SlideshowViewModel
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_slideshow.*

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
                ViewModelProvider(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        return root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        TraitementList.layoutManager=LinearLayoutManager(requireActivity())!!
        TraitementList.adapter = TraitementAdapter(requireActivity(),loadData())

    }
    fun loadData():List<Traitement> {
        val data = mutableListOf<Traitement>()
        data.add(Traitement("Diabete","2 fois par jour","Deibex","Said","15/09/2020",  3))
        data.add(Traitement("Covid","3 fois par jour","Zomex","Said","15/09/2020",  3))
        return data
    }


}