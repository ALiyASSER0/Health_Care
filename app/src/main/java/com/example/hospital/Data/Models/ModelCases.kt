package com.example.hospital.Data.Models

data class ModelCases(
    val count: Int,
    val data: List<Cases>
)

data class Cases(
    val id: Int,
    val patient: Patient,
    val status: String,
    val type: String
)

data class Patient(
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String
)