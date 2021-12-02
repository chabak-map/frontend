package com.example.myapplication.map.detail.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailPostRetrofitInterface {

	@GET("/posts/{postId}/detail")
	fun tryGetDetailPost(@Path("postId") postId : Int) : Call<DetailPost>
}