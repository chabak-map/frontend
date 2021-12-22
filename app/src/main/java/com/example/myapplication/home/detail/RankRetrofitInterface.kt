package com.example.myapplication.home.detail

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RankRetrofitInterface {
	@GET("/places/rank")
	fun getGetRank() : Call<Rank>
}