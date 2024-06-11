package com.example.hospital.UI.patient

import android.util.Log
import com.example.hospital.Data.network.RetrofitService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import java.io.File
import javax.inject.Inject


class PatientRepo @Inject constructor(private val retrofitService: RetrofitService) {

    suspend fun getProfile(): Any? {
        return  try {
            val response= retrofitService.getProfile()
            if(response.isSuccessful){
                return response?.body()!!
            }else{

                return response.errorBody()
            }
        }catch (e:Exception){
            Log.e("TAG", "login2:${e.localizedMessage} ", )
            return e.localizedMessage
        }
    }
suspend fun getAllClinic(): Any? {
    return  try {
        val response= retrofitService.getAllClinic()
        if(response.isSuccessful){
            return response?.body()!!
        }else{
            return response.errorBody()
        }
    }catch (e:Exception){
        return e.localizedMessage
    }
}

suspend fun getWorkingHour(clinicId:Int): Any? {
    try {

        val response=retrofitService.getWorkingHours(clinicId)
        if(response.isSuccessful){
            return response?.body()!!
        }else{
            return response.errorBody()
        }
    }catch (e:Exception){
        return e.localizedMessage
    }
}
suspend fun uploadRecord(name: RequestBody, body: MultipartBody.Part): Boolean{
    try {

        val response=retrofitService.uploadRecord(name,body)
        if(response.isSuccessful){
            return true
        }else{
            return false
        }
    }catch (e:Exception){
        return false
    }
}

    suspend fun getNumberOfQueue(clinicId:Int, workingHourId:Int): Any? {
        try {

            val response=retrofitService.getNumberInQueue(clinicId,workingHourId)
            if(response.isSuccessful){
                return response?.body()!!
            }else{
                return response.errorBody()
            }
        }catch (e:Exception){
            return e.localizedMessage
        }
    }
    suspend fun reserveClinic(clinicId:Int, workingHourId:Int): Any? {
        try {
            val response=retrofitService.reserveClinic(clinicId.toString(),workingHourId.toString())
            if(response.isSuccessful){
                return response?.body()!!
            }else{
                return response.errorBody()
            }
        }catch (e:Exception){
            return e.localizedMessage
        }
    }
    suspend fun getAllHistory(): Any? {
        return  try {
            val response= retrofitService.getHistory()
            if(response.isSuccessful){
                return response?.body()!!
            }else{
                return response.errorBody()
            }
        }catch (e:Exception){
            return e.localizedMessage
        }
    }
    suspend fun deleteReserve(id:Int): Boolean {
        return  try {
            val response= retrofitService.deleteReserve(id)
            if(response.isSuccessful){
                return true
            }else{
                return false
            }
        }catch (e:Exception){
            return false
        }
    }
    suspend fun getAllFiles(): Any? {
        return  try {
            val response= retrofitService.getAllFiles()
            if(response.isSuccessful){
                return response?.body()!!
            }else{
                return response.errorBody()
            }
        }catch (e:Exception){
            return e.localizedMessage
        }
    }
}