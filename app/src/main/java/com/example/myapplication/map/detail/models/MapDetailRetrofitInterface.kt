package com.example.myapplication.map.detail.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MapDetailRetrofitInterface {
	@GET("/place/{placeId}")
	fun getMapDetail(@Path("placeId") placeId : Int) : Call<MapDetail>
}