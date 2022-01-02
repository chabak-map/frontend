package com.example.myapplication.post.detail.comment.write.models

interface WritePostCommentView {
	fun onPostWriteSuccess(response: WritePostCommentResponse)

	fun onPostWriteFailure(message: String)
}