package com.example.akirareg.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akirareg.models.LoginRequest
import com.example.akirareg.models.LoginResponse
import com.example.akirareg.repository.StudentRepository
import kotlinx.coroutines.launch

class StudentViewModel:ViewModel() {
    var studentRepository= StudentRepository()
    var logResponseLiveData= MutableLiveData<LoginResponse>()
    var logErrorLiveData= MutableLiveData<String>()

    fun logInStudent(loginRequest: LoginRequest) {
        viewModelScope.launch {
            var response = studentRepository.loginStudent(loginRequest)
            if (response.isSuccessful) {
                logResponseLiveData.postValue(response.body())
            } else {
                logErrorLiveData.postValue(response.errorBody()?.string())
            }

        }
    }
}