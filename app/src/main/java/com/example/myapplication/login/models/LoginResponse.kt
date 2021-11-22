package com.example.myapplication.login.models

import com.example.myapplication.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class LoginResponse(
	@SerializedName("result") val result: ResultLogin
) : BaseResponse()
