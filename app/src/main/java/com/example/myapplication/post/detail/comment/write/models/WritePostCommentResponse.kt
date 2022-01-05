package com.example.myapplication.post.detail.comment.write.models

import com.example.myapplication.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class WritePostCommentResponse(
	@SerializedName("result") val result: ResultWritePostComment
) : BaseResponse()
