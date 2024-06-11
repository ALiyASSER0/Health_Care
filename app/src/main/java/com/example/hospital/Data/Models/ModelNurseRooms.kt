package com.example.hospital.Data.Models

data class ModelNurseRooms(
    val count: Int,
    val data: List<NurseRoomData>
)

data class NurseRoomData(
    val id: Int,
    val incharge: Incharge?,
    val maxNumber_inRoom: Int,
    val name: String,
    val number_in_room: Int,
    val status: String,
    val type: String
)

data class Incharge(
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String
)