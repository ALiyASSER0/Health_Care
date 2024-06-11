package com.example.hospital.Data.Models

data class ModelRoomBeds(
    val BedDate: List<BedDate>,
    val RoomData: List<RoomData>
)

data class BedDate(
    val descrption: String?,
    val disease: String?,
    val doctors: Doctors?,
    val id: Int?,
    val name: String?,
    val nurses: Nurses?,
    val patients: Patients?,
    val reserved_from: String?,
    val reserved_until: String?,
    val room: Room?,
    val status: String?,
    val treatment: String?
)

data class RoomData(
    val id: Int?,
    val incharge: MyIncharge?,
    val maxNumber_inRoom: Int?,
    val name: String?,
    val number_in_room: Int?,
    val status: String?,
    val type: String?
)

data class Doctors(
    val email: String?,
    val first_name: String?,
    val id: Int?,
    val last_name: String?
)

data class Nurses(
    val email: String?,
    val first_name: String?,
    val id: Int?,
    val last_name: String?
)

data class Patients(
    val email: String?,
    val first_name: String?,
    val id: Int?,
    val last_name: String?
)

data class Room(
    val id: Int?,
    val incharge:Int?,
    val maxNumber_inRoom: Int?,
    val name: String?,
    val number_in_room: Int?,
    val status: String?,
    val type: String?
)

data class MyIncharge(
    val email: String?,
    val first_name: String?,
    val id: Int?,
    val last_name: String?
)