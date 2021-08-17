package com.example.akirareg.repository

import com.example.akirareg.api.ApiClient
import com.example.akirareg.api.ApiInterface
import com.example.akirareg.models.LoginRequest
import com.example.akirareg.models.LoginResponse
import com.example.akirareg.models.RegistrationRequests
import com.example.akirareg.models.RegistrationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class UserRepository {
    var retrofit=ApiClient.buildApiClient(ApiInterface::class.java)

  suspend  fun registerUser(registrationRequests: RegistrationRequests):Response<RegistrationResponse> =
      withContext(Dispatchers.IO){
          var response = retrofit.registerStudent(registrationRequests)

          return@withContext response

    }


}