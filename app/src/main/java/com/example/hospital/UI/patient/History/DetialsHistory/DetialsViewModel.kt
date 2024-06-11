package com.example.hospital.UI.patient.History.DetialsHistory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.Data.Models.ModelHistory
import com.example.hospital.Data.Models.ResponsePatientRegister
import com.example.hospital.UI.patient.PatientRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetialsViewModel @Inject constructor(private val repo: PatientRepo) : ViewModel() {
    private val _liveData= MutableLiveData<Boolean>()
    val liveData get() = _liveData


    fun deleteReserve(id:Int){
        viewModelScope.launch {
            val data=repo.deleteReserve(id)
            _liveData.value = data
        }

    }


}