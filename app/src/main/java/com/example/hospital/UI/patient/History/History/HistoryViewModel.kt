package com.example.hospital.UI.patient.History.History

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.Data.Models.ModelHistory
import com.example.hospital.UI.patient.PatientRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HistoryViewModel @Inject constructor(private val repo: PatientRepo) : ViewModel() {
    private val _liveData= MutableLiveData<ModelHistory>()
    val liveData get() = _liveData
    private val _errorBody= MutableLiveData<String>()
    val errorBody get() = _errorBody

    fun getAllHistory(){
        viewModelScope.launch {
            val data=repo.getAllHistory()
            val model=data as? ModelHistory
            model?.let {
                _liveData.value=it
            }
            val error=data as? String
            error.let {
                _errorBody.value=it
            }
        }

    }


}