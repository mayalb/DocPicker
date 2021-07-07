package com.example.docpicker.entity

import android.view.inspector.IntFlagMapping
import java.util.*

data class objetREndezVous(
    val idPatient: Int,
    val idDoctor: Int,
    val timeDebute: String,
    val duration: Int,
    val state: Int
)