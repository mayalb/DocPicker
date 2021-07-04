package com.example.docpicker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.docpicker.DoctorAdapter
import com.example.docpicker.R
import com.example.docpicker.entity.Doctor
import com.example.docpicker.retrofit.RetrofitService
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getdoctors()
      //  var doctorsList=findViewById(R.id.doctorsList) as RecyclerView
    //    doctorsList.layoutManager=LinearLayoutManager(requireActivity())!!
      //  doctorsList.adapter = DoctorAdapter(requireActivity(),loadData())

    }
    private fun getdoctors(){
        val call = RetrofitService.endpoint.getdoctors()
        call.enqueue(object: Callback<List<Doctor>> {
            override fun onFailure(call: Call<List<Doctor>>, t: Throwable) {
                Toast.makeText(requireActivity(), "Erreur de connexion", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Doctor>>, response: Response<List<Doctor>>) {
                if(response.isSuccessful){
                    val data=response.body()
                    if(data!=null){
                        doctorsList.layoutManager=LinearLayoutManager(requireActivity())
                        doctorsList.adapter = DoctorAdapter(requireActivity(),data)
                    }else{
                        Toast.makeText(requireActivity(), "Aucun medecin", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(requireActivity(), "Erreur est survenue", Toast.LENGTH_SHORT).show()
                }


            }

        })
    }
}