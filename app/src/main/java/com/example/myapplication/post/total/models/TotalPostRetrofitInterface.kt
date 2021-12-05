package com.example.myapplication.post.total.models

import retrofit2.Call
import retrofit2.http.GET

interface TotalPostRetrofitInterface {

	@GET("/posts")
	fun tryTotalPost() : Call<TotalPost>
}