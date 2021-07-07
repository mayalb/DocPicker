package com.example.docpicker.ui.gallery

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.content.SharedPreferences
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.docpicker.*
import com.example.docpicker.entity.Doctor
import com.example.docpicker.entity.RendezVous
import com.example.docpicker.entity.Traitement
import com.example.docpicker.retrofit.RetrofitService
import com.example.docpicker.ui.slideshow.SlideshowViewModel
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_slideshow.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat.getDateInstance
import java.text.SimpleDateFormat
import java.util.*

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    lateinit var profilViewModel: ProfilViewModel

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

        val currentdate=System.currentTimeMillis().let { Date(it) }
        Toast.makeText(requireActivity(), currentdate.toString(), Toast.LENGTH_SHORT).show()

        val token = PrefUtil.with(requireActivity()).getInt(PrefUtil.Keys.idUser, 0)
        if (token !=0 ) {
            getCurrentTreatments(token,currentdate)
        }else{
            Toast.makeText(requireActivity(), "id user egal à 0", Toast.LENGTH_SHORT).show()
        }




    }
    private fun getCurrentTreatments(idPatient:Int,Currentdate:Date){

        val call = RetrofitService.endpoint.getCurrentTreaments(idPatient)

        call.enqueue(object: Callback<List<Traitement>> {
            override fun onFailure(call: Call<List<Traitement>>, t: Throwable) {
                Toast.makeText(requireActivity(), "Erreur de connexion", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Traitement>>, response: Response<List<Traitement>>) {
                if(response.isSuccessful){
                    val data=response.body()
                    var traitement=mutableListOf<Traitement>()

                    if(data!=null){
                        val i=data.size
                        var j=0
                        var k=0
                        val StringDate = Currentdate.toString()
                        while(j!=i){

                                traitement.add(k,data[j])
                                k++
                             //   Toast.makeText(requireActivity(), traitement[k].Description.toString(), Toast.LENGTH_SHORT).show()

                            j += 1
                        }


                        TraitementList.layoutManager=LinearLayoutManager(requireActivity())
                        TraitementList.adapter = TraitementAdapter(requireActivity(),data)
                    }else{
                        Toast.makeText(requireActivity(), "Aucun Traitement en cours", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(requireActivity(), "Erreur est survenue", Toast.LENGTH_SHORT).show()
                }


            }

        })
    }




}