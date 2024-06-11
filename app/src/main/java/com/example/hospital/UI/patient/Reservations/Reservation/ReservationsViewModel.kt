package com.example.hospital.UI.patient.Reservations.Reservation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.Data.Models.ModelClinic
import com.example.hospital.UI.patient.PatientRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ReservationsViewModel @Inject constructor(private val repo:PatientRepo) : ViewModel() {
    private val _liveData= MutableLiveData<ModelClinic>()
    val liveData get() = _liveData
    private val _errorBody= MutableLiveData<String>()
    val errorBody get() = _errorBody
    fun getAllClinic(){
        viewModelScope.launch {
            val data=repo.getAllClinic()
            val model=data as? ModelClinic
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