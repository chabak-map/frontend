package com.example.myapplication.join.verify

import com.google.gson.annotations.SerializedName

data class PostVerifyRequest(
	@SerializedName("phoneNumber") val phoneNumber : String,
	@SerializedName("verifyCode") val verifyCode : String
)
