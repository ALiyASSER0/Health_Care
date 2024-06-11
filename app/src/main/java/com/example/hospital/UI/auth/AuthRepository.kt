package com.example.hospital.UI.auth

import android.util.Log
import com.example.hospital.Data.Models.ModelAccessToken
import com.example.hospital.Data.network.RetrofitService
import javax.inject.Inject

class AuthRepository @Inject constructor(private val retrofitService: RetrofitService)  {



    suspend fun login(userName:String,password:String): Any? {
        return  try {
            val response= retrofitService.login(userName,password)
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
 suspend fun getCurrentUser(): Any?{
     return  try {
         val response= retrofitService.getCurrentUser()
         if(response.isSuccessful){
             return response?.body()!!
         }else{
             return response?.errorBody()
         }
     }catch (e:Exception){
         return e.localizedMessage
     }
 }

}