package com.example.hospital.UI.hr.Chaty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.Data.Models.ModelCreateRoom
import com.example.hospital.Data.Models.ModelRegisterHr
import com.example.hospital.UI.hr.HrRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

//class ChatyViewModel:ViewModel() {
//
//private val _socketStatus=MutableLiveData(false)
//    val socketStatus get() = _socketStatus
//
//private val _message=MutableLiveData<Pair<Boolean,String>>()
//    val message get() = _message
//    fun setStatus(status:Boolean)=GlobalScope.launch(Dispatchers.Main) {
//        _socketStatus.value=status
//    }
//    fun setMessage(message:Pair<Boolean,String>)=GlobalScope.launch(Dispatchers.Main) {
//            if(_socketStatus.value==true){
//                _message.value=message
//            }
//    }
//
//}
@HiltViewModel
class ChatyViewModel @Inject constructor(val repo: HrRepo):ViewModel(){
    private val _liveData=MutableLiveData<ModelCreateRoom>()
    val liveData get() = _liveData
    private val _errorBody=MutableLiveData<ResponseBody>()
    val errorBody get() = _errorBody
    fun createRoom(){
        viewModelScope.launch {
            val response= repo.createRoom()
            if(response.isSuccessful){
                _liveData.value=response.body()
            }else{
                _errorBody.value =response.errorBody()
            }


        }

    }


}