package com.example.myapplication.comment.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentRetrofitInterface {
	@GET("/posts/{postId}/comments")
	fun tryGetComments(@Path("postId") postId : Int) : Call<Comments>
}