package com.example.hospital.Data.network

import ModelHrList
import com.example.hospital.Data.Models.ModelAccessToken
import com.example.hospital.Data.Models.ModelRegisterHr
import com.example.hospital.Data.Models.ModelRegisterPatient
import com.example.hospital.Data.Models.ResponsePatientRegister
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {

//@POST("patients/register/")
//suspend fun registerPatient(@Body body: ModelRegisterPatient)
//
//@GET("hr/list/")
//suspend fun getAllHr():ModelHrList

@POST("patients/register/")
suspend fun registerUser(@Body body:ModelRegisterPatient):Response<ResponsePatientRegister>

@GET("users/list/")
   suspend fun getAllHr(): Response<ModelHrList>

@FormUrlEncoded
@POST("login/")
   suspend fun login(@Field("username") phone: String,
            @Field("password") password: String,
           ):Response<ModelAccessToken>


    @POST("hr/create-user/")
    suspend fun registerUserHr(@Body body: ModelRegisterHr):Response<ResponsePatientRegister>

}
