package com.example.myapplication.map.detail.review.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MapReviewRetrofitInterface {

	@GET("/places/{placeId}/comments")
	fun getMapReview(@Path("placeId") placeId : Int) : Call<MapReview>
}