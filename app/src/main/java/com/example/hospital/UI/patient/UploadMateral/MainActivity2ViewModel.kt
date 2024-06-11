package com.example.hospital.UI.patient.UploadMateral

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.Data.Models.ModelRetrieveFile
import com.example.hospital.UI.patient.PatientRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject
@HiltViewModel
class MainActivity2ViewModel @Inject constructor(private val repo:PatientRepo) : ViewModel() {
    private val _liveData= MutableLiveData<ModelRetrieveFile>()
    val liveData get() = _liveData
    private val _errorBody= MutableLiveData<String>()
    val errorBody get() = _errorBody

    private var _liveDataUploadFile= MutableLiveData<Boolean>()
    val liveDataUploadFile get() = _liveDataUploadFile


    fun getAllFiles(){
        viewModelScope.launch {
            val data=repo.getAllFiles()
            val model=data as? ModelRetrieveFile
            model?.let {
                _liveData.value=it
            }
            val error=data as? String
            error?.let {
                _errorBody.value=it
            }
        }

    }


  fun uploadFile(name: RequestBody, body: MultipartBody.Part) {
      viewModelScope.launch {
          val data=repo.uploadRecord(name,body)
          if(data){
              liveDataUploadFile.value=true
          }else{
              _liveDataUploadFile.value=false
          }
      }

  }


}