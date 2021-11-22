package com.example.myapplication.login.models

import com.google.gson.annotations.SerializedName

data class ResultLogin(
	@SerializedName("email") val email : Int,
	@SerializedName("jwt") val jwt: String
)
