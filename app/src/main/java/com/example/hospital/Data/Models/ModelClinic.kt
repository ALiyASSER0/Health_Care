package com.example.hospital.Data.Models

import com.google.gson.annotations.SerializedName

class ModelClinic : ArrayList<ModelClinicItem>()

data class ModelClinicItem(
    val doctor: Doctor,
    val id: Int,
    val name: String,
    val price: Double,
    val status: String
)

data class Doctor(
    val email: String,
    @SerializedName("first_name")  val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val id: Int,

    )