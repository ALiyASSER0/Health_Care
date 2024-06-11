package com.example.hospital.UI.hr.Register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.Data.Models.ModelRegisterHr
import com.example.hospital.UI.hr.HrRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject
@HiltViewModel
class RegisterViewModel @Inject constructor(val repo:HrRepo):ViewModel(){
private val _liveData=MutableLiveData<Boolean>()
    val liveData get() = _liveData
private val _errorBody=MutableLiveData<ResponseBody>()
    val errorBody get() = _errorBody
    fun register(model: ModelRegisterHr){
       viewModelScope.launch {
         val response= repo.hrRegister(model)

           if(response.isSuccessful){
               _liveData.value=true
           }else{
               _errorBody.value =response.errorBody()
           }


       }

    }


}