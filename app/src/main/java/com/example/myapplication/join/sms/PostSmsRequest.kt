package com.example.myapplication.join.sms

import com.google.gson.annotations.SerializedName

data class PostSmsRequest(
	@SerializedName("phoneNumber") val phoneNumber: String
)