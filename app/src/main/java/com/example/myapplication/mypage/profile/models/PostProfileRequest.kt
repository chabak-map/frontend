package com.example.myapplication.mypage.profile.models

import android.net.Uri
import android.widget.ImageView
import com.google.gson.annotations.SerializedName

data class PostProfileRequest(
	@SerializedName("memberImage") val memberImage: ImageView
)
