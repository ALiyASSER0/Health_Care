package com.example.hospital.UI.nurse.Cases.DetialsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.Data.Models.ModelCasesDetials
import com.example.hospital.UI.nurse.NurseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CasesDetialsViewModel @Inject constructor(private val repo: NurseRepo) : ViewModel() {
    private val _liveData = MutableLiveData<ModelCasesDetials>()
    val liveData: LiveData<ModelCasesDetials> get() = _liveData
    private val _errorBody = MutableLiveData<String>()
    val errorBody: LiveData<String> get() = _errorBody

    fun getCasesDetials(id:String) {
        viewModelScope.launch {
            val data = repo.getCasesDetials(id)
            val model = data as? ModelCasesDetials
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