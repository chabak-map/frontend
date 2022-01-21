package com.example.myapplication.post.write.models

import com.google.gson.annotations.SerializedName

data class WritePostRequest(
	@SerializedName("title") val title : String,
	@SerializedName("content") val content : String
)
