package com.example.myapplication.login.models

import com.google.gson.annotations.SerializedName

data class ResultLogin(
	@SerializedName("id") val id : Int,
	@SerializedName("jwt") val jwt: String
)
