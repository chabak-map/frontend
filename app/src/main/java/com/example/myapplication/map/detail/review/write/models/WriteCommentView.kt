package com.example.myapplication.map.detail.review.write.models


interface WriteCommentView {

	fun onPostWriteSuccess(response: WriteCommentResponse)

	fun onPostWriteFailure(message: String)
}