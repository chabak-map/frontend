package com.example.myapplication.post.detail.comment.write.models

import com.google.gson.annotations.SerializedName

data class ResultWritePostComment(
	@SerializedName("id") val id : Int,
	@SerializedName("jwt") val jwt: String
)
