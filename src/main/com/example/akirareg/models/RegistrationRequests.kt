package com.example.akirareg.models

import com.google.gson.annotations.SerializedName

data class RegistrationRequests (
    var name:String,
    @SerializedName("phone_number") var phoneNumber:String,
    var email:String,
    var password:String,
    var nationality:String,
    @SerializedName("date_of_birth") var dateOfBirth:String,


    )


