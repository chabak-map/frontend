package com.example.myapplication.post.detail.comment.write.models

interface WritePostCommentView {
	fun onPostWriteSuccess(response: ResultWritePostComment)

	fun onPostWriteFailure(message: String)
}