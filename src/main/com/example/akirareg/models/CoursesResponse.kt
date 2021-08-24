package com.example.akirareg.models

import com.google.gson.annotations.SerializedName

data class CoursesResponse(
    @SerializedName("course_name")
    val courseName:String,
    @SerializedName("course_id")
    val courseId:String,
    @SerializedName("course_code")
    val courseCode:String,
    @SerializedName("course_trainer")
    val courseTrainer:String
)