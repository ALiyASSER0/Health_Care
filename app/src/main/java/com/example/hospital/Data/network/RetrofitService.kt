package com.example.hospital.Data.network

import ModelHrList
import com.example.hospital.Data.Models.ModelAccessToken
import com.example.hospital.Data.Models.ModelCases
import com.example.hospital.Data.Models.ModelCasesDetials
import com.example.hospital.Data.Models.ModelCasesNurse
import com.example.hospital.Data.Models.ModelClinic
import com.example.hospital.Data.Models.ModelCreateRoom
import com.example.hospital.Data.Models.ModelCurrentUser
import com.example.hospital.Data.Models.ModelHistory
import com.example.hospital.Data.Models.ModelNumberReserve
import com.example.hospital.Data.Models.ModelNurseRooms
import com.example.hospital.Data.Models.ModelRegisterHr
import com.example.hospital.Data.Models.ModelRegisterPatient
import com.example.hospital.Data.Models.ModelRetrieveFile
import com.example.hospital.Data.Models.ModelRoomBeds
import com.example.hospital.Data.Models.ModelWorkingHour
import com.example.hospital.Data.Models.ResponsePatientRegister
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {



@POST("patients/register/")
suspend fun registerUser(@Body body:ModelRegisterPatient):Response<ResponsePatientRegister>

@GET("users/list/")
   suspend fun getAllHr(): Response<ModelHrList>

@GET("doctors/cases/")
    suspend fun getCases(): Response<ModelCases>

@GET("/nurses/Casses/")
    suspend fun getCasesNurse(): Response<ModelCasesNurse>
@GET("doctors/clinics/")
    suspend fun getAllClinic(): Response<ModelClinic>
@GET("nurses/Rooms/GetAll/")
    suspend fun getAllRooms(): Response<ModelNurseRooms>
@GET("nurses/Rooms/{room_id}")
    suspend fun getAllBeds(@Path("room_id") id:String): Response<ModelRoomBeds>
@GET("patients/history/")
    suspend fun getHistory(): Response<ModelHistory>
@GET("doctors/working-hours/{clinicId}")
    suspend fun getWorkingHours(@Path("clinicId") clinicId:Int): Response<ModelWorkingHour>
@DELETE("patients/reservation/{workingHour}")
    suspend fun deleteReserve(@Path("workingHour") workingHour:Int): Response<Unit>
@GET("users/get-current-user/")
    suspend fun getCurrentUser(): Response<ModelCurrentUser>
@GET("patients/reserve-clinic/{clinicId}/{workingHourId}/")
    suspend fun getNumberInQueue(@Path("clinicId") clinicId:Int,@Path("workingHourId") workingHourId:Int): Response<ModelNumberReserve>

@GET("doctors/patient/{Id}")
    suspend fun getCasesDetials(@Path("Id") id:String): Response<ModelCasesDetials>
 @GET("nurses/Casses/Details/{Id}")
    suspend fun getNurseCasesDetials(@Path("Id") id:String): Response<ModelCasesDetials>
@FormUrlEncoded
@POST("login/")
   suspend fun login(@Field("username") phone: String,
            @Field("password") password: String,
           ):Response<ModelAccessToken>
    @FormUrlEncoded
    @POST("users/get-current-user/")
    suspend fun getProfile(
    ):Response<ModelAccessToken>

    @FormUrlEncoded
    @POST("patients/reserve-clinic/")
    suspend fun reserveClinic(@Field("clinic_id") clinicId: String,
                      @Field("working_hour_id") workingHourId: String,
    ):Response<ResponseBody>
    @FormUrlEncoded
    @POST("chat/create-room/")
    suspend fun createRoom(@Field("other_user") otherUser: String="01013455141"):Response<ModelCreateRoom>

    @Multipart
    @POST("patients/upload-record/")
    suspend  fun uploadRecord(@Part("type") type: RequestBody,
                              @Part file:MultipartBody.Part): Response<Unit>
    @GET("patients/upload-record/")
    suspend fun getAllFiles(): Response<ModelRetrieveFile>
    @POST("hr/create-user/")
    suspend fun registerUserHr(@Body body: ModelRegisterHr):Response<ResponsePatientRegister>






}
