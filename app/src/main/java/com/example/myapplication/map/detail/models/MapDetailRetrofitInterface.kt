package com.example.myapplication.map.detail.models

import com.example.myapplication.map.models.RadiusPlaceName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MapDetailRetrofitInterface {
	@GET("/places/{placeId}")
	fun getMapDetail(@Path("placeId") placeId : Int) : Call<MapDetail>

}