package com.example.hospital.UI.Doctors

import android.util.Log
import com.example.hospital.Data.network.RetrofitService
import javax.inject.Inject

class DoctorRepo @Inject constructor(private val retrofitService: RetrofitService) {

    suspend fun getCases(): Any? {
        return try {
            val response = retrofitService.getCases()
            if (response.isSuccessful) {
                return response?.body()!!
            } else {

                return response.errorBody()
            }
        } catch (e: Exception) {
            return e.localizedMessage
        }
    }
    suspend fun getCasesDetials(id:String): Any? {
        return try {
            val response = retrofitService.getCasesDetials(id)
            if (response.isSuccessful) {
                return response?.body()!!
            } else {

                return response.errorBody()
            }
        } catch (e: Exception) {
            return e.localizedMessage
        }
    }
}