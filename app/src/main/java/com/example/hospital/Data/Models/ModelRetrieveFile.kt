package com.example.hospital.Data.Models

class ModelRetrieveFile : ArrayList<ModelRetrieveFileItem>()

data class ModelRetrieveFileItem(
    val file: String,
    val type: String
)