package com.example.myapplication.find.models

import com.google.gson.annotations.SerializedName

data class FindEmailRequest(
	@SerializedName("phoneNumber") val phoneNumber : String
)
