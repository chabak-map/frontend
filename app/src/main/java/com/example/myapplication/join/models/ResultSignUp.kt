package com.example.myapplication.join.models

import com.google.gson.annotations.SerializedName

data class ResultSignUp(
	@SerializedName("id") val id: Int,
	@SerializedName("jwt") val jwt: String
)