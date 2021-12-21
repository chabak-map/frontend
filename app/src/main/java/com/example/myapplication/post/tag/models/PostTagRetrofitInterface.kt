package com.example.myapplication.post.tag.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostTagRetrofitInterface {

	@GET("/posts/{postId}/tags")
	fun getPostTag(@Path("postId") postId : Int) : Call<PostTag>
}