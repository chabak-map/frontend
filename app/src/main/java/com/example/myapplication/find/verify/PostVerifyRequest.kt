package com.example.myapplication.join.verify

import com.google.gson.annotations.SerializedName

data class IdPostVerifyRequest(
	@SerializedName("phoneNumber") val phoneNumber : String,
	@SerializedName("verifyCode") val verifyCode : String
)
