package com.example.akirareg.api

import com.example.akirareg.models.LoginRequest
import com.example.akirareg.models.LoginResponse
import com.example.akirareg.models.RegistrationRequests
import com.example.akirareg.models.RegistrationResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/students/register")
  suspend  fun registerStudent(@Body registrationRequests: RegistrationRequests): Response<RegistrationResponse>
    @POST("/students/register")

   suspend fun logInStudent(@Body loginRequest: LoginRequest):Response<LoginResponse>

}