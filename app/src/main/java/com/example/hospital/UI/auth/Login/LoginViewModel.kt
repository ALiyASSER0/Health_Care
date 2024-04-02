package com.example.hospital.UI.auth.Login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.Data.Models.ModelAccessToken
import com.example.hospital.Data.network.RetrofitService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val retrofit: RetrofitService) :ViewModel(){

val liveData=MutableLiveData<ModelAccessToken>()
val errorCode=MutableLiveData<ResponseBody>()

fun login(userName:String,password:String){
    Log.e("TAG", "userName:$userName password:$password ", )
    viewModelScope.launch {
       try{
      val response=retrofit.login(userName,password)
      if(response.isSuccessful){
          liveData.value=response.body()
      }else{
          errorCode.value= response.errorBody()
      }

       }catch (e:Exception){
       errorCode.value=e.message?.toResponseBody()
       }

    }
}




}