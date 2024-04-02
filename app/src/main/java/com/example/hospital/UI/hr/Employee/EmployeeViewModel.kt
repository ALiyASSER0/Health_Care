package com.example.hospital.UI.hr.Employee
import ModelHrList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.UI.hr.HrRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class EmployeeViewModel @Inject constructor(private val repo: HrRepo):ViewModel()  {
private val _liveData=MutableLiveData<ModelHrList>()
    val liveData get() = _liveData
private val _errorBody=MutableLiveData<String>()
    val errorBody get() = _errorBody
fun getAllEmployee(){
    viewModelScope.launch {
     val data=repo.getAllUsers()
      val model=data as? ModelHrList
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

