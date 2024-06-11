package com.example.hospital.Data.Models

data class ModelCreateRoom(
    val data: MyData,
    val message: String
)

data class MyData(
    val id: Int,
    val room_name: String,
    val users: List<Int>
)