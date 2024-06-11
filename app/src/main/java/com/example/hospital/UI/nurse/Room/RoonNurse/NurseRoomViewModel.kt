package com.example.hospital.UI.nurse.Room.RoonNurse

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.Data.Models.ModelNurseRooms
import com.example.hospital.UI.nurse.NurseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NurseRoomViewModel @Inject constructor(private val repo:NurseRepo) : ViewModel() {
    private val _liveData= MutableLiveData<ModelNurseRooms>()
    val liveData get() = _liveData
    private val _errorBody= MutableLiveData<String>()
    val errorBody get() = _errorBody
    fun getAllRooms(){
        viewModelScope.launch {
            val data=repo.getAllRooms()
            val model=data as? ModelNurseRooms
            model.let {
                _liveData.value=it
            }
            val error=data as? String
            error.let {
                _errorBody.value=it
            }
        }

    }


}
