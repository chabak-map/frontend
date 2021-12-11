package com.example.myapplication.map.detail.tagmodels

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TagRetrofitInterface {
	@GET("/places/{placeId}/tags")
	fun getPostTag(@Path("placeId") placeId : Int) : Call<Tag>
}