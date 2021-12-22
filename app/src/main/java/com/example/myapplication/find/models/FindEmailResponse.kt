package com.example.myapplication.find.models

import com.example.myapplication.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class FindEmailResponse(
	@SerializedName("result") val result : FindEmail
) : BaseResponse()
