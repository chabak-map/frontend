package com.example.myapplication.join.models

import com.google.gson.annotations.SerializedName

data class ResultSignUp(
	@SerializedName("email") val email: Int,
	@SerializedName("jwt") val jwt: String
)