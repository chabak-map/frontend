package com.example.myapplication.map.detail.review.write.models

import com.example.myapplication.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class WriteCommentResponse(
	@SerializedName("result") val result : WriteComment
) : BaseResponse()
