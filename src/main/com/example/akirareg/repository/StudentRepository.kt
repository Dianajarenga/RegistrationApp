package com.example.akirareg.repository

import com.example.akirareg.api.ApiClient
import com.example.akirareg.api.ApiInterface
import com.example.akirareg.models.LoginRequest
import com.example.akirareg.models.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class StudentRepository {
    var retrofit= ApiClient.buildApiClient(ApiInterface::class.java)

    suspend  fun loginStudent(loginRequest: LoginRequest): Response<LoginResponse> =
        withContext(Dispatchers.IO){
            var response = retrofit.logInStudent(loginRequest)

            return@withContext response

        }
}