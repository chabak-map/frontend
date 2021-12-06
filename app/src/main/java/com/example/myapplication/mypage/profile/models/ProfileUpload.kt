package com.example.myapplication.mypage.profile.models

import com.google.gson.annotations.SerializedName

data class ProfileUpload(
	@SerializedName("id") val id: Int,
	@SerializedName("jwt") val jwt: String
)
