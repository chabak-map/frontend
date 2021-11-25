package com.example.myapplication.post.place.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceRetrofitInterface {
	@GET("/places/{placeId}")
	fun getPlace(@Path("placeId") placeId : Int) : Call<place>
}