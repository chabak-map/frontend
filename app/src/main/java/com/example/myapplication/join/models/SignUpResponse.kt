package com.example.myapplication.join.models

import com.example.myapplication.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class SignUpResponse(
	@SerializedName("result") val result : ResultSignUp
):BaseResponse()
