package com.example.akirareg.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akirareg.models.LoginRequest
import com.example.akirareg.models.LoginResponse
import com.example.akirareg.models.RegistrationRequests
import com.example.akirareg.models.RegistrationResponse
import com.example.akirareg.repository.StudentRepository
import com.example.akirareg.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    var userRepository=UserRepository()
    var regResponseLiveData= MutableLiveData<RegistrationResponse>()
    var regErrorLiveData= MutableLiveData<String>()

    fun registerStudent(registrationRequests: RegistrationRequests){
        viewModelScope.launch {
            var response=  userRepository.registerUser(registrationRequests)
            if (response.isSuccessful){
                regResponseLiveData.postValue(response.body())
            }
            else{
                regErrorLiveData.postValue(response.errorBody()?.string())
            }

        }
    }


}
