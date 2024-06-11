package com.example.hospital.Data.Models

import com.google.gson.annotations.SerializedName

class ModelWorkingHour : ArrayList<ModelWorkingHourItem>()

data class ModelWorkingHourItem(
    val id: Int,
    val day: String,
    @SerializedName("start_time") val startTime: String,
    @SerializedName("end_time") val endTime: String,
    @SerializedName("clinic_id") val clinicId: Int

)