package com.example.myapplication.post.write.models

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface WritePostRetrofitInterface {
	@POST("/posts")
	fun tryWritePost(@Body param : WritePostRequest) : Call<WritePost>
}