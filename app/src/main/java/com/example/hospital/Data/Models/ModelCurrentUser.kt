package com.example.hospital.Data.Models

import com.google.gson.annotations.SerializedName

data class ModelCurrentUser(
    val data: Data,
    val message: String
)



data class Data(
    val address: String?,
    val blood: String?,
    val clinic: Any?,
    @SerializedName("date_joined") val dateJoined: String?,
    @SerializedName("date_of_birth") val dateOfBirth: String?,
    val email: String?,
    @SerializedName("first_name") val firstName: String?,
    val gender: String?,
    val groups: List<Any>?,
    val id: Int?,
    @SerializedName("insurance_number") val insuranceNumber: String?,
    @SerializedName("is_active") val isActive: Boolean?,
    @SerializedName("is_staff") val isStaff: Boolean?,
    @SerializedName("is_superuser") val isSuperuser: Boolean?,
    @SerializedName("last_login") val lastLogin: Any?,
    @SerializedName("last_name") val lastName: String?,
    val password: String?,
    val employee: Employee?,
    val ssn: String?,
    val status: String?,
    @SerializedName("user_permissions") val userPermissions: List<Any>?,
    val username: String?
)

data class Employee(
    @SerializedName("hired_at") val hiredAt: String?,
    val id: Int?,
    val salary: Int?,
    val specialization: String?,
    val type: String?,
    @SerializedName("updated_at") val updatedAt: String?
)
