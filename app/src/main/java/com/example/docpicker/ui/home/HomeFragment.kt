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
import kotlinx.android.synthetic.main.fragment_home.*

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
        doctorsList.layoutManager=LinearLayoutManager(requireActivity())!!
        doctorsList.adapter = DoctorAdapter(requireActivity(),loadData())

    }
    fun loadData():List<Doctor> {
        val data = mutableListOf<Doctor>()
        data.add(Doctor(R.drawable.logo,"Maya","Larbi","083743829","cardiologue",1,  "7.07",
            "9.0"))
        data.add(Doctor(R.drawable.logo,"Safi","Rihani","083743829","generaliste",1,  "7.07",
            "9.0"))
        data.add(Doctor(R.drawable.logo,"fatima","bz","0886789","Dentiste",1,  "7.07",
            "9.0"))
        data.add(Doctor(R.drawable.logo,"Rayane","Larbi","083743829","Dentiste", 2, "7.07",
            "9.0"))
        data.add(Doctor(R.drawable.logo,"Massi","Larbi","92878","cardiologue",1,  "7.07",
            "9.0"))
        data.add(Doctor(R.drawable.logo,"Rima","Amirat","083743829","pediatre",1,  "7.07",
            "9.0"))
        data.add(Doctor(R.drawable.logo,"Youcef","larbi","083743829","pediatre",1,  "7.07",
            "9.0"))
        data.add(Doctor(R.drawable.logo,"Ghada","flissi","0837989","ophtalmologue",1,  "7.07",
            "9.0"))
        data.add(Doctor(R.drawable.logo,"Naima","larbi","083743829","pediatre",1,  "7.07",
            "9.0"))
        data.add(Doctor(R.drawable.logo,"Said","larbi","083743829","dentiste",1,  "7.07",
            "9.0"))

        return data
    }
}