package com.example.hospital.UI.nurse.Room.DetialsRoom

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.Data.Models.ModelRoomBeds
import com.example.hospital.UI.nurse.NurseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomDetialsViewModel @Inject constructor(private val repo: NurseRepo) : ViewModel() {
    private val _liveData= MutableLiveData<ModelRoomBeds>()
    val liveData get() = _liveData
    private val _errorBody= MutableLiveData<String>()
    val errorBody get() = _errorBody
    fun getAllBeds(id:String){
        viewModelScope.launch {
            val data=repo.getAllBeds(id)
            val model=data as? ModelRoomBeds
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