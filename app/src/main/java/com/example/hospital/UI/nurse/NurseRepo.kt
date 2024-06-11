package com.example.hospital.UI.nurse

import android.util.Log
import com.example.hospital.Data.network.RetrofitService
import javax.inject.Inject

class NurseRepo @Inject constructor(private val retrofitService: RetrofitService) {

    suspend fun getCasesNurse(): Any? {
        return try {
            val response = retrofitService.getCasesNurse()
            if (response.isSuccessful) {
                return response?.body()!!
            } else {

                return response.errorBody()
            }
        } catch (e: Exception) {
            return e.localizedMessage
        }
    }

    suspend fun getAllRooms(): Any? {
        return  try {
            val response= retrofitService.getAllRooms()
            if(response.isSuccessful){
                return response?.body()!!
            }else{
                return response.errorBody()
            }
        }catch (e:Exception){
            return e.localizedMessage
        }
    }
    suspend fun getAllBeds(id:String): Any? {
        return  try {
            val response= retrofitService.getAllBeds(id)
            if(response.isSuccessful){
                return response?.body()!!
            }else{
                return response.errorBody()
            }
        }catch (e:Exception){
            return e.localizedMessage
        }
    }
    suspend fun getCasesDetials(id:String): Any? {
        return try {
            val response = retrofitService.getNurseCasesDetials(id)
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