package com.example.docpicker

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class DeconnectFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        PrefUtil.with(requireActivity()).save(PrefUtil.Keys.idUser,0)
        val intent =  Intent(requireActivity(), LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
        return inflater.inflate(R.layout.fragment_deconnect, container, false)
    }


}