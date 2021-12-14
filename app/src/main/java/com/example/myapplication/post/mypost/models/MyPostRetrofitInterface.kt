package com.example.myapplication.post.mypost.models

import retrofit2.Call
import retrofit2.http.GET

interface MyPostRetrofitInterface {
	@GET("/posts/me")
	fun getMyPost() : Call<MyPost>
}