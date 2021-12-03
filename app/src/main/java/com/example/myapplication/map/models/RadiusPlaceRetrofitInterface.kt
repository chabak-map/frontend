package com.example.myapplication.map.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RadiusPlaceRetrofitInterface {
	@GET("/places")
	fun getPlace(
		@Query("lat") lat: Double,
		@Query("lng") lng: Double,
		@Query("r") r: Int
	): Call<RadiusPlace>

	@GET("/places/{placeId}")
	fun getPlaceName(@Path("placeId") placeId : Int) : Call<RadiusPlaceName>
}