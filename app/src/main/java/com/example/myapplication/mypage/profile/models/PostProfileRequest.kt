package com.example.myapplication.mypage.profile.models

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class PostProfileRequest(
	@SerializedName("memberImage") val memberImage: MultipartBody.Part
)
