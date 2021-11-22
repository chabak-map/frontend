package com.example.myapplication.login.models

import com.google.gson.annotations.SerializedName

data class ResultLoginUser(
	@SerializedName("userId") val userId: Int,
	@SerializedName("email") val email: String
)
