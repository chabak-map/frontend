package com.example.myapplication.join.sms

import com.example.myapplication.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class IdSMSResponse(
	@SerializedName("result") val result : ResultSMS
): BaseResponse()

