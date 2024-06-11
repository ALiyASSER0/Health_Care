package com.example.hospital.UI.patient.Reservations.Detials

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.Data.Models.ModelClinic
import com.example.hospital.Data.Models.ModelNumberReserve
import com.example.hospital.Data.Models.ModelWorkingHour
import com.example.hospital.UI.patient.PatientRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject
@HiltViewModel
class DetialsViewModel @Inject constructor(private val repo:PatientRepo):ViewModel() {
    private val _liveData= MutableLiveData<ModelWorkingHour>()
    val liveData get() = _liveData
    private val _liveDataNumberReserve= MutableLiveData<ModelNumberReserve>()
    val liveDataNumberReserve get() = _liveDataNumberReserve
    private val _errorBody= MutableLiveData<String>()
    val errorBody get() = _errorBody
    private val _errorBodyNumberReserve= MutableLiveData<String>()
    val errorBodyNumberReserve get() = _errorBody

    private val _liveDataReserveClinic= MutableLiveData<ResponseBody>()
    val liveDataReserveClinic get() = _liveDataReserveClinic
    private val _errorBodyReserveClinic= MutableLiveData<String>()
    val errorBodyReserveClinic get() = _errorBodyReserveClinic
    fun getWorkingHour(clinicId:Int){
        viewModelScope.launch {
            val data=repo.getWorkingHour(clinicId)
            val model=data as? ModelWorkingHour
            model?.let {
                _liveData.value=it
            }
            val error=data as? String
            error.let {
                _errorBody.value=it
            }
        }

    }

    fun getNumberInQueue(clinicId:Int, workingHourId:Int){
        viewModelScope.launch {
            val data=repo.getNumberOfQueue(clinicId,workingHourId)
            val model=data as? ModelNumberReserve
            model.let {
                _liveDataNumberReserve.value=it
            }
            val error=data as? String
            error.let {
                _errorBodyNumberReserve.value=it
            }
        }

    }
    fun reserveClinic(clinicId:Int, workingHourId:Int){
        viewModelScope.launch {
            val data=repo.reserveClinic(clinicId,workingHourId)
            val model=data as? ResponseBody
            model.let {
                _liveDataReserveClinic.value=it
            }
            val error=data as? String
            error.let {
                _errorBodyReserveClinic.value=it
            }
        }

    }


}