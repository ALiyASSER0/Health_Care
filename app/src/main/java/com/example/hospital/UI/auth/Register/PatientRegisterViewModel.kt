package com.example.hospital.UI.auth.Register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.Data.Models.ModelRegisterPatient
import com.example.hospital.Data.network.RetrofitService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

@HiltViewModel
class PatientRegisterViewModel @Inject constructor(private val retrofitService: RetrofitService) : ViewModel() {
private val _liveData=MutableLiveData<Boolean>()
val liveData get() = _liveData
private val _errorBody=MutableLiveData<ResponseBody>()
 val errorBody get() = _errorBody

    fun register(model: ModelRegisterPatient) {
       viewModelScope.launch {
       try {
         val response= retrofitService.registerUser(model)
       if(response.isSuccessful){
           _liveData.value=true
       }else{
         _errorBody.value=response.errorBody()
       }

       }catch (e:Exception){
          _errorBody.value=e.localizedMessage.toResponseBody()
       }

       }

    }
}


