package com.example.myapplication.mypage.profile.models

import com.example.myapplication.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
	@SerializedName("result") val result : ProfileUpload
): BaseResponse()
