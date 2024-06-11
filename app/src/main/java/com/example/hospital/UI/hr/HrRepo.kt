package com.example.hospital.UI.hr

import ModelHrList
import android.util.Log
import com.example.hospital.Data.Models.ModelCreateRoom
import com.example.hospital.Data.Models.ModelRegisterHr
import com.example.hospital.Data.Models.ResponsePatientRegister
import com.example.hospital.Data.network.RetrofitService
import retrofit2.Response
import javax.inject.Inject

class HrRepo @Inject constructor(private val retrofitService: RetrofitService){


suspend fun getAllUsers(): Any? {
  return  try {
       val response= retrofitService.getAllHr()
        if(response.isSuccessful){
           return response?.body()!!
        }else{
           return response.errorBody()
        }
     }catch (e:Exception){
     return e.localizedMessage
    }
}

suspend fun hrRegister(model: ModelRegisterHr): Response<ResponsePatientRegister> {
     return retrofitService.registerUserHr(model)
    }
 suspend fun createRoom(): Response<ModelCreateRoom> {
        return retrofitService.createRoom()
    }




}