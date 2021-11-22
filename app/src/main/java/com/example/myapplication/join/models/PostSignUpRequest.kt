package com.example.myapplication.join.models

import com.google.gson.annotations.SerializedName

data class PostSignUpRequest(
	@SerializedName("nickname") val nickname : String,
	@SerializedName("email") val email : String,
	@SerializedName("password") val password : String,
	@SerializedName("phoneNumber") val phoneNumber : String
)
