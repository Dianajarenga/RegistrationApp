package com.example.akirareg.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.akirareg.models.CoursesResponse
import com.example.akirareg.repository.CoursesRepository

class CoursesViewModel {

        var userRepository= CoursesRepository()
        var coursesResponseLiveData= MutableLiveData<CoursesResponse>()
        var courseErrorLiveData= MutableLiveData<String>()


suspend fun fetchCourses(coursesResponse: CoursesResponse){
            viewModelScope.launch {
                var response=userRepository.fetchCourse(CoursesResponse)
                if (response.isSuccessful){
                    coursesResponseLiveData.postValue(response.body())
                }
                else{
                    courseErrorLiveData.postValue(response.errorBody()?.string())
                }
            }
        }
    }

