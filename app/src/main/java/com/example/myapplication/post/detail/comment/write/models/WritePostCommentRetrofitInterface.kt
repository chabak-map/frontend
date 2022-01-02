package com.example.myapplication.post.detail.comment.write.models

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface WritePostCommentRetrofitInterface {
	@POST("/posts/{postId}/comments")
	fun tryPostComments(@Body params : WritePostCommentRequest, @Path("postId") postId : Int) : Call<WritePostCommentResponse>
}