package com.example.hospital.Data.Models

data class ModelCasesNurse(
    val count: Int,
    val data: List<DataNurse>
)

data class DataNurse(
    val id: Int,
    val number_in_qeue: Int,
    val patient: PatientNurse,
    val type: String
)

data class PatientNurse(
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String
)