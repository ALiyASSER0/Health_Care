package com.example.hospital.UI.auth.Login
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.Data.Models.Data
import com.example.hospital.Data.Models.ModelAccessToken
import com.example.hospital.Data.Models.ModelCurrentUser
import com.example.hospital.UI.auth.AuthRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repo:AuthRepository) :ViewModel(){

    private val _accessTokenLiveData= MutableLiveData<ModelAccessToken>()
    val accessTokenLiveData get() = _accessTokenLiveData

    private val _currentUserLiveData= MutableLiveData<Data>()
    val currentUserLiveData get() = _currentUserLiveData

    val accessTokenErrorBody= MutableLiveData<ResponseBody>()
     val currentUserErrorBody= MutableLiveData<String>()
fun login(userName:String,password:String){
    viewModelScope.launch {
        val data=repo.login(userName,password)
        val model=data as? ModelAccessToken
        model?.let {
            _accessTokenLiveData.value=it
        }
        val error=data as? ResponseBody
        error?.let {
            accessTokenErrorBody.value=it
        }

    }
}




    fun getCurrentUser(){
    viewModelScope.launch {
        val data=repo.getCurrentUser()
        val model=data as? ModelCurrentUser
        model?.let {
            _currentUserLiveData.value=it.data
        }
        val error=data as? String
        error?.let {
            currentUserErrorBody.value=it
        }

    }
}


}