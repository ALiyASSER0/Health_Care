package com.example.hospital.UI.nurse.Cases.CasesScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.Data.Models.ModelCasesNurse
import com.example.hospital.UI.nurse.NurseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CasesNurseViewModel @Inject constructor(
    private val repo: NurseRepo
) : ViewModel() {
    private val _liveData = MutableLiveData<ModelCasesNurse>()
    val liveData: LiveData<ModelCasesNurse> get() = _liveData
    private val _errorBody = MutableLiveData<String>()
    val errorBody: LiveData<String> get() = _errorBody

    fun getCases() {
        viewModelScope.launch {
            val data = repo.getCasesNurse()
            val model = data as? ModelCasesNurse
            model?.let {
                _liveData.value = it
            }
            val error = data as? String
            error?.let {
                _errorBody.value = it
            }
        }
    }
}