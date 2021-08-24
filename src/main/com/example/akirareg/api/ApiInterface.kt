package com.example.akirareg.api

import com.example.akirareg.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("/students/register")
  suspend  fun registerStudent(@Body registrationRequests: RegistrationRequests): Response<RegistrationResponse>
    @POST("/students/login")

   suspend fun logInStudent(@Body loginRequest: LoginRequest):Response<LoginResponse>
   @POST("/courses")
   suspend fun fetchCourses(@Header("Authorization")token:String):Response<List<CoursesResponse>>
}