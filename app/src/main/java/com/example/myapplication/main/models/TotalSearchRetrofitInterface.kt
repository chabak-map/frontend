package com.example.myapplication.main.models

import retrofit2.Call
import retrofit2.http.*

interface TotalSearchRetrofitInterface {
	@GET("/search")
	fun tryTotalSearch(@Query("q") query : String) : Call<TotalSearch>
}