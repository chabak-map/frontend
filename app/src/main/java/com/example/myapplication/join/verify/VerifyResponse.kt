package com.example.myapplication.join.verify

import com.example.myapplication.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class VerifyResponse(
	@SerializedName("result") val result : ResultVerify
): BaseResponse()

