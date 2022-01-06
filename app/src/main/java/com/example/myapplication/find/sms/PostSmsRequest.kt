package com.example.myapplication.join.sms

import com.google.gson.annotations.SerializedName

data class IdPostSmsRequest(
	@SerializedName("phoneNumber") val phoneNumber: String
)