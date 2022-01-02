package com.example.myapplication.post.detail.comment.write.models

import com.google.gson.annotations.SerializedName

data class WritePostCommentRequest(
	@SerializedName("content") val content : String
)
