package com.example.myapplication.map.detail.review.write.models

import com.google.gson.annotations.SerializedName

data class WriteCommentRequest(
	@SerializedName("content") val content : String
)
