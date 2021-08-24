package com.example.akirareg.repository

import android.provider.SyncStateContract
import com.example.akirareg.api.ApiClient
import com.example.akirareg.api.ApiInterface
import com.example.akirareg.models.Constants
import com.example.akirareg.models.Course
import com.example.akirareg.models.CoursesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CoursesRepository {
    val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun fetchCourse(coursesResponse: CoursesResponse):
            Response<Course> =
        withContext(Dispatchers.IO) {
            var response = retrofit.fetchCourses(Constants.ACCESS_TOKEN)
            return@withContext response
        }
}