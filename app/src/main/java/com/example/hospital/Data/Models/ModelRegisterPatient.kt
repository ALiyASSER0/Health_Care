package com.example.hospital.Data.Models

import com.google.gson.annotations.SerializedName

data class ModelRegisterPatient(
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val email: String,
    val password: String,
    @SerializedName("confirm_password") val confirmPassword: String,
    val phone: String,
)